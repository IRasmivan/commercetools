package com.rasmivan.commercetools.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rasmivan.commercetools.dto.StatisticsDto;
import com.rasmivan.commercetools.dto.TopAvailableProductDto;
import com.rasmivan.commercetools.dto.TopSellingProductDto;
import com.rasmivan.commercetools.exception.InvalidStatisticTimeException;
import com.rasmivan.commercetools.helper.DateUtils;
import com.rasmivan.commercetools.repository.InvoiceRepository;
import com.rasmivan.commercetools.repository.ProductRepository;
import com.rasmivan.commercetools.repository.StockRepository;
import com.rasmivan.commercetools.constants.GeneralConstantsUtils;
import com.rasmivan.commercetools.constants.MessageConstantsUtils;

/**
 * The Class StatisticServiceImp.
 */
@Service
@CacheConfig(cacheNames={"statistic"})
public class StatisticServiceImp implements StatisticService {
	
	/** The product repository. */
	@Autowired
	ProductRepository productRepository;
	
	/** The stock repository. */
	@Autowired
	StockRepository stockRepository;
	
	/** The invoice repository. */
	@Autowired
	InvoiceRepository invoiceRepository;
	
	/** The date utils. */
	@Autowired
	DateUtils dateUtils;
	
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StatisticServiceImp.class);

	/**
	 * ************************************
	 * ************************************
	 * Get Statistics for the provided time [today, lastMonth]
	 * ************************************
	 * 
	 * **** Failure Scenario Coverage *****
	 * *** The below condition are checked before adding the stock into the database.
	 * ** 1) Check if the User have given valid time (today or lastMonth). If its invalid time, then respond user as InvalidStatisticTimeException with message as 'Invalid time for statistics, Please provide a valid value'.
	 * 
	 * ** ~~ Special Scenario ~~
	 * ** 1) If there are no stock available for the given time, then I have populated an attribute called TopAvailableProductMessage which has message as 'There are no product that was available for provided <<time>>'.
	 * ** 2) If there are stock available but less than 3 for the given time, then I have populated an attribute called TopAvailableProductMessage which has message as 'There are only <<count of available stock>> product that had sales for <<time>>'.
	 * ** 3) If there are no sales available for the given time, then I have populated an attribute called TopSellingProductsMessage which has message as 'There are no product that was available for provided <<time>>'.
	 * ** 4) If there are stock sales but less than 3 for the given time, then I have populated an attribute called TopSellingProductsMessage which has message as 'There are only <<count of sales>> product that had sales for <<time>>'.
	 * 
	 * ************************************
	 * ************************************
	 *
	 * @param time the time
	 * @return the statistics
	 */
	@Override
	public StatisticsDto getStatistics(String time) {
		Instant startDateTime = null;
		Instant endDateTime = null;
		if(time.equalsIgnoreCase(GeneralConstantsUtils.TODAY)) {
			startDateTime = dateUtils.getStartOfDay(Date.from(LocalDate.now(ZoneOffset.UTC).atStartOfDay(ZoneOffset.UTC).toInstant()));
			endDateTime = dateUtils.getEndOfDay(Date.from(LocalDate.now(ZoneOffset.UTC).atStartOfDay(ZoneOffset.UTC).toInstant()));
		} else if(time.equalsIgnoreCase(GeneralConstantsUtils.LAST_MONTH)) {
			startDateTime = dateUtils.getFirstDayofMonth();
			endDateTime = dateUtils.getLastDayofMonth();
		} else {
			LOGGER.info("Invalid Time");
			throw new InvalidStatisticTimeException(MessageConstantsUtils.STATISTIC_INVALID_TIME);
		}
		return populateStatistics(time, startDateTime, endDateTime);
	}

	/**
	 * Populate statistics.
	 *
	 * @param time the time
	 * @param startDateTime the start date time
	 * @param endDateTime the end date time
	 * @return the statistics dto
	 */
	private StatisticsDto populateStatistics(String time, Instant startDateTime, Instant endDateTime) {
		StatisticsDto statisticsDto = new StatisticsDto();
		statisticsDto.setRange(time);
		statisticsDto.setRequestTimestamp(Instant.now().toString());
		setTopAvailableProducts(time, statisticsDto, startDateTime, endDateTime);	
		setTopSellingProducts(time, statisticsDto, startDateTime, endDateTime);
		return statisticsDto;
	}

	/**
	 * Sets the top selling products.
	 *
	 * @param time the time
	 * @param statisticsDto the statistics dto
	 * @param startDateTime the start date time
	 * @param endDateTime the end date time
	 */
	private void setTopSellingProducts(String time, StatisticsDto statisticsDto, Instant startDateTime,
			Instant endDateTime) {
		Pageable pageable = PageRequest.of(GeneralConstantsUtils.PAGE_NO, GeneralConstantsUtils.PAGE_SIZE_STATISTIC);
		
		List<TopSellingProductDto> topSellingProductDto = invoiceRepository.getSalesforTimestamp(startDateTime, endDateTime,pageable);
		if(topSellingProductDto != null && !topSellingProductDto.isEmpty()) {
			statisticsDto.setTopSellingProducts(topSellingProductDto);
		}
		
		if(topSellingProductDto == null || topSellingProductDto.isEmpty()) {
			statisticsDto.setTopSellingProductsMessage(GeneralConstantsUtils.NO_SALES + time);
		} else {
			statisticsDto.setTopSellingProductsMessage((topSellingProductDto.size() < 3) ?  GeneralConstantsUtils.PREFIX_TXT + topSellingProductDto.size() + GeneralConstantsUtils.PRODUCT_SALES + time : null);
		}
	}

	/**
	 * Sets the top available products.
	 *
	 * @param time the time
	 * @param statisticsDto the statistics dto
	 * @param startDateTime the start date time
	 * @param endDateTime the end date time
	 */
	private void setTopAvailableProducts(String time, StatisticsDto statisticsDto, Instant startDateTime,
			Instant endDateTime) {
		Pageable pageable = PageRequest.of(GeneralConstantsUtils.PAGE_NO, GeneralConstantsUtils.PAGE_SIZE_STATISTIC);
		
		List<TopAvailableProductDto> topAvailableProductDtos = stockRepository.getStockforTimestamp(startDateTime, endDateTime,pageable);
		if(topAvailableProductDtos != null && !topAvailableProductDtos.isEmpty()) {
			statisticsDto.setTopAvailableProducts(topAvailableProductDtos);
		}
		
		if(topAvailableProductDtos == null || topAvailableProductDtos.isEmpty()) {
			statisticsDto.setTopAvailableProductMessage(GeneralConstantsUtils.NO_AVAILABLE + time);
		} else {
			statisticsDto.setTopAvailableProductMessage(
					(topAvailableProductDtos.size() < 3) ? GeneralConstantsUtils.PREFIX_TXT + topAvailableProductDtos.size() + GeneralConstantsUtils.PRODUCT_AVAILABLE + time : null);
		}
	}

}