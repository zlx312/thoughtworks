package task;

import java.text.ParseException;

public class Stadium {
	private int money; // ’»Î 

	public void addSite(MySite site) {
		// TODO Auto-generated method stub
		
	}

	public void addFeeStandard(DayType workday, String string, int i) {
		// TODO Auto-generated method stub
		
	}

	public String book(String userId, String date, String time, String place) throws ParseException {
		MySite site=new MySite(place);
		if(site.isAvailable(date, time)){
			if(site.setTimeArr(userId,date,time)){
				return "the booking is accepted!";
			}
		}
		return "the booking conflicts with existing bokings!";
	}
	
	public String cancel(String userId, String date, String time, String place) throws ParseException {
		MySite site=new MySite(place);
		if(!site.isAvailable(date, time)){
			if(site.cancleTimeArr(userId,date,time)){
				return "the booking is accepted!";
			}
		}else
		  return "the booking being cancelled doesn't exist!";
		return "cancel faied!";
	}
	

}
