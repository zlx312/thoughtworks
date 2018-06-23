package com.thoughtworks;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Site {
	//场馆名
	private String name;

	//key记录日期，value时一个长度为13的String数组，记录各个时间段的userID，没被预定则为""
	private Map<String,String[]> date_time=new HashMap<String,String[]>();
	//提示信息
	private String msg;
	
	public Site(String name){
		this.name=name;
	}
	
	/**
	 * 判断场馆在制定日期的指定时间段内是否可用
	 * @param date
	 * @param time
	 * @return
	 */
	public boolean isAvailable(String date,int start,int end){
		//如果查询的日期没有需要先new一个，否则get时会报空指针错误
		if(date_time.get(date)==null){
			//string数组对应从9点开始到22点的13个时间段
			String[] str={"","","","","","","","","","","","",""};
			date_time.put(date,str);
			return true;
		}
		
	    for(int j=start;j<end;j++){
		   if(!date_time.get(date)[j-9].equals("")){
			   return false;
		   }
	    }
		    return true;
	}
	
	/**
	 * 预定
	 * 先判断是否可用，不可用设置msg并且返回
	 * 可用就将遇到信息加入到date_time中，返回成功信息
	 * @throws ParseException 
	 */
	public void book(String user,String date,String time) throws ParseException{
		//对传入的时间参数进行处理
		String[] sub_time=time.split("~");
		int start = Util.stringToTime(sub_time[0]);
		int end =  Util.stringToTime(sub_time[1]);
		
		//如果不可用，说明选择时段中有的已被预订了
		if(!isAvailable(date,start,end)){
		    msg="the booking conflicts with existing bookings!";
		    return;
		}
		
		//如果可用，就对date_time进行操作,并且将预订信息传入订单类中
		 for(int i=start-9;i<end-9;i++){
			 date_time.get(date)[i]=user; 
		 }
		 msg="the booking is accepted!";
	}
	
	/**
	 * 取消
	 * 先判断场地是否可用，如果可用，说明要取消的订单不存在，返回
	 * 如果不可用，并且取消与预定的信息一致，则返回成功信息
	 * @throws ParseException 
	 */
	public void cancle(String user,String date,String time) throws ParseException{
		//对传入的时间参数进行处理
		String[] sub_time=time.split("~");
		int start = Util.stringToTime(sub_time[0]);
		int end =  Util.stringToTime(sub_time[1]);
		
		//如果可用，说明没有被人预定，提示要取消的预定不存在
		if(isAvailable(date,start,end)){
			msg="the booking being cancelled doesn't exist!";
			return;
		}
		
		//否则执行取消，对date_time进行操作
		 for(int i=start-9;i<end-9;i++){
			 //如果出现某个时段预订者和取消者不一致，则提示取消失败
			 if(!user.equals(date_time.get(date)[i])){
				 msg="user is not same,cancle failed!";
				 return;
			 }else{
			   date_time.get(date)[i]="";
			 }
		 }
		 msg="the booking is accepted!";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String[]> getDate_time() {
		return date_time;
	}

	public void setDate_time(Map<String, String[]> date_time) {
		this.date_time = date_time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
