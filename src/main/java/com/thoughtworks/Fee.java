package com.thoughtworks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.Util.DayType;

public class Fee {
	//周内各时段价格
	private static int[] weekday_fee={30,30,30,50,50,50,50,50,50,80,80,60,60};
	//周末各时段价格
	private static int[] weekend_fee={40,40,40,50,50,50,50,50,50,60,60,60,60};
	//预定或取消产生的费用
	private int fee;
	//折扣
	private double discount=1.0;
	//优惠金额
	private int discountFee=0;
	//从文本资源中读取的优惠信息
	private List<Map<String,String>> discountInfo=new ArrayList<Map<String,String>>();
	
	/**
	 * 构造方法，自动获取优惠信息
	 */
	public Fee(){
		this.getDiscountInfo();
	}
	
	/**
	 * 发生预定请求时计算金额
	 * @return
	 * @throws ParseException 
	 */
	public int payment(String date,String time) throws ParseException{
		//计算优惠信息，设置discount;
		discountCalculate(date);
		
		fee=0;
				
		//将日期转为周内或周末
		DayType dayType=Util.dateToWeek(date);
		
        //对传入的时间参数进行处理
		String[] sub_time=time.split("~");
		int start = Util.stringToTime(sub_time[0]);
		int end =  Util.stringToTime(sub_time[1]);
		
		//根据周末或周内的计费标准进行计费
		if(dayType.name().equals("WEEKDAY")){
			for(int i=start-9;i<end-9;i++){
				fee+=weekday_fee[i];
			}
		}else if(dayType.name().equals("WEEKEND")){
			for(int i=start-9;i<end-9;i++){
				fee+=weekend_fee[i];
			}
		}
		discountFee=(int)(fee*(1-discount));
		return (int) (fee*discount);
	}

	/**
	 * 发生取消请求时计算违约金
	 * @return
	 * @throws ParseException 
	 */
	public int penalty(String date,String time) throws ParseException{				
		fee=0;
		
		//将日期转为周内或周末
		DayType dayType=Util.dateToWeek(date);
		
        //对传入的时间参数进行处理
		String[] sub_time=time.split("~");
		int start = Util.stringToTime(sub_time[0]);
		int end =  Util.stringToTime(sub_time[1]);
		
		//根据周末或周内的计费标准进行计费
		if(dayType.name().equals("WEEKDAY")){
			for(int i=start-9;i<end-9;i++){
				fee+=weekday_fee[i];
			}
			return fee/2;
		}else if(dayType.name().equals("WEEKEND")){
			for(int i=start-9;i<end-9;i++){
				fee+=weekend_fee[i];
			}
			return fee/4;
		}
		return 0;
		
	}
	
	/**
	 * 从资源文件中读取优惠信息
	 */
	public void getDiscountInfo(){
		File file = new File("src/main/resources/discount");
		BufferedReader reader = null;
		String temp = null;
		int line = 1;
		//按行读取
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((temp = reader.readLine()) != null) {
				//分隔后加入优惠信息Map中
				Map<String,String> map=new HashMap<String,String>();
				String[] str=temp.split(" ");
				map.put("start",str[0]);
				map.put("end",str[1]);
				map.put("discount",str[2]);
				discountInfo.add(map);
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	/**
	 * 计算优惠信息
	 * @throws ParseException 
	 */
	public void discountCalculate(String date) throws ParseException{
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 Date d=df.parse(date);
		 for(int i=0;i<discountInfo.size();i++){
			 Date start=df.parse(discountInfo.get(i).get("start"));
			 Date end=df.parse(discountInfo.get(i).get("end"));
			 if(d.getTime()>start.getTime()&&d.getTime()<end.getTime()){
				 discount=Double.parseDouble(discountInfo.get(i).get("discount"))/10;
				 return;
			 }else
				 discount=1;
		 }
	}
	
	/**
	 * getters and setters method
	 */
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public int getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(int discountFee) {
		this.discountFee = discountFee;
	}

}
