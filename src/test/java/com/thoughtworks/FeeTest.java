package com.thoughtworks;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class FeeTest {

	@Test
	public void should_return_payment_after_input_date_and_time() throws ParseException {
		String date="2018-07-07";
		String time="9:00~12:00";
		Fee fee=new Fee();
		assertEquals(120,fee.payment(date, time));
		
		date="2018-07-06";
		time="17:00~20:00";
		assertEquals(210,fee.payment(date, time));
	}
	
	@Test
	public void should_return_penalty_fee_after_input_date_and_time() throws ParseException {
		String date="2018-07-08";
		String time="9:00~12:00";
		Fee fee=new Fee();
		assertEquals(30,fee.penalty(date, time));
		
		date="2018-07-12";
		time="17:00~20:00";
		assertEquals(105,fee.penalty(date, time));
	}
	
	@Test
	public void should_return_discount_after_discount_calculate() throws ParseException{
		Fee fee=new Fee();
		fee.discountCalculate("2018-06-05");
		//第三个参数为误差值
		assertEquals(0.60,fee.getDiscount(),0.01);
		
		fee.discountCalculate("2018-07-13");
		assertEquals(1,fee.getDiscount(),0.01);
	}

}
