package task;

import java.util.HashMap;
import java.util.Map;

import List;

public class site {
	int[][] fee={{30,50,80,60},{40,50,60,60}};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		read("U001 2016-06-02 22:00~22:00 A");
	}
	
	public static String read(String command) {
		// TODO Auto-generated method stub
		
		//String pattern="U[0-9]{3}\s+[0-9]{4}-[0-9]{2}-[0-9]{2}\s"
		
		String str[]=command.split(" ");
		if(str.length!=4&&str.length!=5){
			return "the booking is invalid!";
		}
		
		if(str.length==5&&str[str.length-1]!="C"){
			return "the booking is invalid!";
		}
		
		String time=str[2];
		String sub_time[]=time.split("~");
		if(sub_time[0].equals(sub_time[1])){
			return "the booking is invalid!";
		}
		
		if(!sub_time[0].substring(sub_time[0].indexOf(':')).equals(":00")||
		   !sub_time[1].substring(sub_time[1].indexOf(':')).equals(":00")){
			return "the booking is invalid!";
		}
		return " ";
	}

}
