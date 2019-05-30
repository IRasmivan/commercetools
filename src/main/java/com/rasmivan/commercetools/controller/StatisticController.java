package com.rasmivan.commercetools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasmivan.commercetools.dto.StatisticsDto;
import com.rasmivan.commercetools.service.StatisticService;

/**
 * The Class StatisticController.
 */
@RestController
@CrossOrigin
@RequestMapping("/commercetools/api/")
public class StatisticController {
	
	/** The statistic service. */
	@Autowired
	StatisticService statisticService;
	
	/**
	 * Gets the product.
	 *
	 * @param time the time
	 * @return the product
	 */
	@CrossOrigin
	@GetMapping(value = "v1/statistics")
	public ResponseEntity<StatisticsDto> getStatistics(@RequestParam String time){
		return new ResponseEntity<>(statisticService.getStatistics(time), HttpStatus.OK);
	}
	
}
