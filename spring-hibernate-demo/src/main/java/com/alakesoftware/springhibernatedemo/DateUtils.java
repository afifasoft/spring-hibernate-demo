package com.alakesoftware.springhibernatedemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public  Date parseDate(String dateStr) throws ParseException{
		Date theDate = formatter.parse(dateStr);
		
		return theDate;
		
	}
	
	public  String formatDate(Date theDate) {
		String result = null;
		
		if (theDate != null) {
			result = formatter.format(theDate);
		}
		
		return result;
	}
	
	
}
