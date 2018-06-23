package com.thoughtworks;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncoderTest {

	@Test
	public void should_return_invalid_when_booking_command_has_wrong_format() {
		Encoder encoder = new Encoder("abcdefghilmnopq12345678");
		encoder.encode();
		assertEquals("the booking is invalid!", encoder.getMsg());
	}
	@Test
	public void should_return_invalid_when_booking_time_is_same(){
		Encoder encoder = new Encoder("U001 2016-06-02 22:00~22:00 A");
		encoder.encode();
		assertEquals("the booking is invalid!", encoder.getMsg());
	}
	
	@Test
	public void should_return_invalid_when_book_time_is_not_integral(){
		Encoder encoder = new Encoder("U001 2016-06-02 20:15~22:00 A");
		encoder.encode();
		assertEquals("the booking is invalid!", encoder.getMsg());
	}
	
	@Test
	public void should_return_invalid_when_unbooking_time_is_same(){
		Encoder encoder = new Encoder("U001 2016-06-02 22:00~22:00 A C");
		encoder.encode();
		assertEquals("the booking is invalid!", encoder.getMsg());
	}
	
	@Test
	public void should_return_invalid_when_unbook_time_is_not_integral(){
		Encoder encoder = new Encoder("U001 2016-06-02 20:15~22:00 A C");
		encoder.encode();
		assertEquals("the booking is invalid!", encoder.getMsg());
	}
	
	@Test
	public void should_return_invalid_when_cancle_flag_is_not_C(){
		Encoder encoder = new Encoder("U001 2016-06-02 20:00~22:00 A G");
		encoder.encode();
		assertEquals("the booking is invalid!", encoder.getMsg());
	}
	
	@Test
	public void should_return_format_string_array_after_format(){
		Encoder encoder = new Encoder("U001 2016-06-02 20:00~22:00 D C");
		encoder.encode();
		assertEquals("cancle", encoder.getAction());
		assertEquals("2016-06-02", encoder.getDate());
		assertEquals("20:00~22:00", encoder.getTime());
		assertEquals("D", encoder.getSite());
		assertEquals("U001", encoder.getUser());
	}
	
	@Test
	public void should_make_action_equals_summary_if_command_is_empty(){
		String command="";
		Encoder encoder=new Encoder(command);
		encoder.encode();
		assertEquals("summary", encoder.getAction());
	}
}
