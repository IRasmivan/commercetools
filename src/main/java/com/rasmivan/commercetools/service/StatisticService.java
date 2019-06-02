package com.rasmivan.commercetools.service;

import com.rasmivan.commercetools.dto.StatisticsDto;

/**
 * The Interface StatisticService.
 */
public interface StatisticService {
	
	/**
	 * Gets the statistics.
	 *
	 * @param time the time
	 * @return the statistics
	 */
	StatisticsDto getStatistics(String time);
	
}