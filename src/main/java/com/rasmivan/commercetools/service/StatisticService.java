package com.rasmivan.commercetools.service;

import com.rasmivan.commercetools.dto.StatisticsDto;

public interface StatisticService {
	
	StatisticsDto getStatistics(String time);
	
}