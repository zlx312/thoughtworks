package com.thoughtworks;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {
	//用一个存储订单信息的集合，将每一条订单记录拼接成一个字符串
	private List<String> orderList=new ArrayList<String>();
	//该订单所属的场地
	private String siteName;
	//收入总和
	private int total;
	
	/**
	 * 构造方法，传入场地名
	 */
	public Order(String siteName){
		this.siteName=siteName;
	}
	
	/**
	 * 生成订单String，并加入orderList中
	 * 生成的订单记录可能是预定，也可能是取消
	 * @throws ParseException 
	 */
	public void generateOrder(String date,String time,String action) throws ParseException{
		Fee fee=new Fee();
		int payment=0;
		int penalty=0;
		String str="";

		//如果预定，则计算费用，并将日期、时间、费用信息加入orders中
		//如果取消预定，则计算本来的费用和违约金，并将日期、时间、费用信息加入orders中，注明违约金
		if(action.equals("book")){
			payment=fee.payment(date, time);
			int discountFee=fee.getDiscountFee();
			total+=payment;
			str=date+" "+time+" "+payment+"元";
			if(discountFee!=0){
				str+=" 已优惠："+discountFee+"元";
			}
		}else if(action.equals("cancle")){
			payment=fee.payment(date, time);
			penalty=fee.penalty(date, time);
			total-=payment;
			total+=penalty;
			str=date+" "+time+" 违约金 "+penalty+"元";
			orderList.add(str);
		}
		
		orderList.add(str);
	}
	
	
	/**
	 * 打印本场地的订单列表及收入信息汇总
	 */
	public void printSummary(){
		System.out.println("场地："+siteName);
		Iterator<String> it = orderList.iterator();
		while(it.hasNext()){
		  System.out.println(it.next());
		}
		System.out.println("小计："+total+"元");
	}

	/**
	 * getters 和setters方法
	 */
	public List<String> getOrders() {
		return orderList;
	}

	public void setOrders(List<String> orders) {
		this.orderList = orders;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
