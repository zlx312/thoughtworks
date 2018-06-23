package com.thoughtworks;

public class Encoder {
	//命令
	private String command;  
	//用户
	private String user;
	//日期
	private String date;
	//时间段
	private String time;
	//预定或取消场馆
	private String site;
	//行为：预定、取消
	private String action;
	//提示信息
	private String msg="";
	
	/**
	 * 构造函数传入命令
	 * @param command
	 */
	public Encoder(String command){
		this.command=command;
		this.encode();
	}
	
	/**
	 * 对命令进行解析，判断格式是否正确
	 * @return 
	 */
	public void encode(){
		//对命令进行拆分
		String[] str=command.split(" ");
		
		//如果输入空格则打印收入信息
		if(command.equals("")){
			action="summary";
			return;
		}
			
		//进行简单的规则判断，如果长度不为4或5则非法
		if(str.length!=5&&str.length!=4){
			msg= "the booking is invalid!";
			return;
		}
		
		//如果长度为5但是最后一个字母不为C则非法
		if(str.length==5&&!str[str.length-1].equals("C")){
			msg= "the booking is invalid!";
			return;
		}
		
		//如果时间段两个时间相同则非法
		String time=str[2];
		String sub_time[]=time.split("~");
		if(sub_time[0].equals(sub_time[1])){
			msg= "the booking is invalid!";
			return;
		}
		
		//如果时间不为整点则非法
		if(!sub_time[0].substring(sub_time[0].indexOf(':')).equals(":00")||
		   !sub_time[1].substring(sub_time[1].indexOf(':')).equals(":00")){
			msg= "the booking is invalid!";
			return;
		}
		
		//到了这一步语法没有什么问题了，就对命令进行格式化，得到user、date、time、action属性值方便后续处理
		format(str);
	}
	
	/**
	 * 对命令进行格式化，获取user、date、time、action信息，方便后续处理
	 * @param str
	 */
	public void format(String[] str){
		user = str[0];
		date = str[1];
		time = str[2];
		site = str[3];
		
		if(str.length==4){
			setAction("book");
		}else{
			setAction("cancle");
		}
	}
	
	/**
	 * getters and setters方法
	 */
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
