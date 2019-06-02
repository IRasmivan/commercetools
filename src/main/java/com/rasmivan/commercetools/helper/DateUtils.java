package com.rasmivan.commercetools.helper;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.rasmivan.commercetools.constants.GeneralConstantsUtils;

/**
 * The Class DateUtils.
 */
@Service
public class DateUtils {
	
	/**
	 * Gets the start of day.
	 *
	 * @param date the date
	 * @return the start of day
	 */
	public Instant getStartOfDay(Date date) {
		Instant startDate = date.toInstant();
	    return startDate.truncatedTo(ChronoUnit.DAYS);
	}

	/**
	 * Gets the end of day.
	 *
	 * @param date the date
	 * @return the end of day
	 */
	public Instant getEndOfDay(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(GeneralConstantsUtils.DATE_FORMAT); 
		return Instant.parse(dateFormat.format(date) + GeneralConstantsUtils.LAST_TIME);
	}
	
	/**
	 * Gets the first dayof month.
	 *
	 * @return the first dayof month
	 */
	public Instant getFirstDayofMonth() {
		LocalDate convertedDate =  LocalDate.now(ZoneOffset.UTC).minusMonths(1);
		convertedDate = convertedDate.withDayOfMonth(1);
		return getStartOfDay(Date.from(convertedDate.atStartOfDay(ZoneOffset.UTC).toInstant()));
	}
	
	/**
	 * Gets the last dayof month.
	 *
	 * @return the last dayof month
	 */
	public Instant getLastDayofMonth() {
		LocalDate convertedDate =  LocalDate.now(ZoneOffset.UTC).minusMonths(1);
		convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
		return getEndOfDay(Date.from(convertedDate.atStartOfDay(ZoneOffset.UTC).toInstant()));
	}

}
