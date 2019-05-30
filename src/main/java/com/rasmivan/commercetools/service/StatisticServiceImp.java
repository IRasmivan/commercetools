package com.rasmivan.commercetools.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


@Service
public class StatisticServiceImp implements StatisticService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	DateUtils dateUtils;
	
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StatisticServiceImp.class);

	@Override
	public StatisticsDto getStatistics(String time) {
		Timestamp startDateTime = null;
		Timestamp endDateTime = null;
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

	private StatisticsDto populateStatistics(String time, Timestamp startDateTime, Timestamp endDateTime) {
		StatisticsDto statisticsDto = new StatisticsDto();
		statisticsDto.setRange(time);
		statisticsDto.setRequestTimestamp(Instant.now().toString());
		setTopAvailableProducts(time, statisticsDto, startDateTime, endDateTime);	
		setTopSellingProducts(time, statisticsDto, startDateTime, endDateTime);
		return statisticsDto;
	}

	private void setTopSellingProducts(String time, StatisticsDto statisticsDto, Timestamp startDateTime,
			Timestamp endDateTime) {
		Pageable pageable = PageRequest.of(GeneralConstantsUtils.PAGE_NO, GeneralConstantsUtils.PAGE_SIZE);
		
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

	private void setTopAvailableProducts(String time, StatisticsDto statisticsDto, Timestamp startDateTime,
			Timestamp endDateTime) {
		Pageable pageable = PageRequest.of(GeneralConstantsUtils.PAGE_NO, GeneralConstantsUtils.PAGE_SIZE);
		
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
