package com.thoughtworks;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class OrderTest {

	@Test
	public void should_return_generate_order_string_if_book_or_cancle() throws ParseException {
		String date="2018-07-07";
		String time="9:00~12:00";
		String action="book";
		Order order=new Order("A");
		order.generateOrder(date,time,action);
		assertEquals("2018-07-07 9:00~12:00 120元",order.getOrders().get(0));

		date="2018-07-07";
		time="9:00~12:00";
		action="cancle";
		order.generateOrder(date,time,action);
		assertEquals("2018-07-07 9:00~12:00 违约金 30元",order.getOrders().get(1));
	}
	
	@Test
	public void should_return_correct_total_fee_if_book_or_cancle() throws ParseException {
		String date="2018-07-07";
		String time="9:00~12:00";
		String action="book";
		Order order=new Order("A");
		order.generateOrder(date,time,action);
		assertEquals(120,order.getTotal());
		
		date="2018-07-08";
		time="9:00~12:00";
		action="book";
		order.generateOrder(date,time,action);
		assertEquals(240,order.getTotal());
		
		date="2018-07-07";
		time="9:00~12:00";
		action="cancle";
		order.generateOrder(date,time,action);
		assertEquals(150,order.getTotal());
	}
	
	@Test
	public void should_return_discount_info_if_discount() throws ParseException {
		String date="2018-06-30";
		String time="9:00~12:00";
		String action="book";
		Order order=new Order("A");
		order.generateOrder(date, time, action);
		assertEquals("2018-06-30 9:00~12:00 72元 已优惠：48元",order.getOrders().get(0));
	}

}
