package com.thoughtworks;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {

	@Test
	public void should_return_correct_day_type_after_input_date() {
		String date="2018-06-23";
		assertEquals(Util.DayType.WEEKEND,Util.dateToWeek(date));
		
		date="2018-06-24";
		assertEquals(Util.DayType.WEEKEND,Util.dateToWeek(date));
		
		date="2018-06-25";
		assertEquals(Util.DayType.WEEKDAY,Util.dateToWeek(date));
		
		date="2018-06-29";
		assertEquals(Util.DayType.WEEKDAY,Util.dateToWeek(date));
	}

}
