package com.rasmivan.commercetools.helper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateUtils {
	
	public Timestamp getStartOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
	    return Timestamp.valueOf(startOfDay);
	}

	public Timestamp getEndOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
	    return Timestamp.valueOf(endOfDay);
	}
	
	public Timestamp getFirstDayofMonth() {
		LocalDate convertedDate =  LocalDate.now(ZoneOffset.UTC).minusMonths(1);
		convertedDate = convertedDate.withDayOfMonth(1);
		return getStartOfDay(Date.from(convertedDate.atStartOfDay(ZoneOffset.UTC).toInstant()));
	}
	
	public Timestamp getLastDayofMonth() {
		LocalDate convertedDate =  LocalDate.now(ZoneOffset.UTC).minusMonths(1);
		convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
		return getEndOfDay(Date.from(convertedDate.atStartOfDay(ZoneOffset.UTC).toInstant()));
	}

	private LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(),ZoneOffset.UTC);
	}

}
