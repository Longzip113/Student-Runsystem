package com.runsystem.student.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConvertDate {
	
	public static LocalDate toLocalDate( Date date) {
		return date.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
	}
	
	public static Date toDate( LocalDate date) {
		return java.util.Date.from(date.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
	}

}
