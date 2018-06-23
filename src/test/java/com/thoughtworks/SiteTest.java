package com.thoughtworks;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class SiteTest {

	@Test
	public void should_return_true_if_site_is_Avalible() {
		Site site=new Site("A");
		assertTrue(site.isAvailable("2017-08-01", 17,19));	
	}
	
	@Test
	public void should_return_accept_if_book_success() throws ParseException {
		Site site=new Site("A");
		site.book("U001","2017-08-01","17:00~19:00");
		assertEquals("the booking is accepted!",site.getMsg());	
		site.book("U001","2017-08-01","17:00~19:00");
		assertEquals("the booking conflicts with existing bookings!",site.getMsg());	
	}
	
	@Test
	public void should_return_accept_if_cancle_success() throws ParseException {
		Site site=new Site("A");
		site.cancle("U001","2017-08-01","17:00~19:00");
		assertEquals("the booking being cancelled doesn't exist!",site.getMsg());
		
		site.book("U001","2017-08-01","17:00~19:00");
		assertEquals("the booking is accepted!",site.getMsg());	
	}

}
