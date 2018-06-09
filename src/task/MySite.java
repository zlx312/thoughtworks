package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MySite {
	private static Map<String,String[]> bookedInfo=new HashMap<String,String[]>();
	private static String[] timeAvalible={"","","","","","","","","","","","",""};
	
	public MySite(String siteName) {
		// TODO Auto-generated constructor stub
	}

	public static boolean isAvailable(String date, String time) throws ParseException {
		       if(bookedInfo.get(date)==null){
		    	   bookedInfo.put(date, timeAvalible);
		       }
			   String[] sub_time=time.split("~");
			   int start = stringToTime(sub_time[0]);
			   int end = stringToTime(sub_time[1]);
			   for(int j=start;j<end;j++){
				  if(bookedInfo.get(date)[j-9]!=""){
					  return false;
				  }
			   }
		return true;
	}
	
	public void setBookedInfo(String date, String[] timeAvalible){
		bookedInfo.put(date, timeAvalible);
	}
	
	public boolean setTimeArr(String userId,String date,String time) throws ParseException{
		 String[] sub_time=time.split("~");
		 int start = stringToTime(sub_time[0]);
		 int end = stringToTime(sub_time[1]);
		 for(int i=start-9;i<end-9;i++){
			 bookedInfo.get(date)[i]=userId;
			 return true;
		 }
		 return false;
	}
	
	public boolean cancleTimeArr(String userId,String date,String time) throws ParseException{
		 String[] sub_time=time.split("~");
		 int start = stringToTime(sub_time[0]);
		 int end = stringToTime(sub_time[1]);
		 for(int i=start-9;i<end-9;i++){
			 if(!userId.equals(bookedInfo.get(date)[i])){
				 return false;
			 }else{
			   bookedInfo.get(date)[i]="";
			   return true;
			 }
		 }
		 return false;
	}
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(stringToTime("3:30")) ;
	}
	//字符串转时间格式
	public static int stringToTime(String time) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("HH:MM");
		return format.parse(time).getHours();
	}
}
