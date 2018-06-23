package com.thoughtworks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 启动类
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException, IOException
    {
       //初始化四个场馆
       Map<String,Site> sites=new HashMap<String,Site>();
       sites.put("A", new Site("A"));
       sites.put("B", new Site("B"));
       sites.put("C", new Site("C"));
       sites.put("D", new Site("D"));
       
       //初始化四个订单
       Map<String,Order> orders=new HashMap<String,Order>();
       orders.put("A", new Order("A"));
       orders.put("B", new Order("B"));
       orders.put("C", new Order("C"));
       orders.put("D", new Order("D"));
      
		while (true) {
			//输入命令
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));// 建立从控制台输入的类
			String command = stdin.readLine();
			
			// 进入编码器，构造方法中运行encode()
			Encoder encoder=new Encoder(command);
			
			//如果提示信息不为"",说明遇到了错误，打印提示信息，退出当前循环
			if(!encoder.getMsg().equals("")){
				System.out.println(encoder.getMsg());
				continue;
			}
			
			//命令符合要求，进行后续处理
			String action = encoder.getAction();
			String user = encoder.getUser();
			String date = encoder.getDate();
			String time = encoder.getTime();
			Site site = sites.get(encoder.getSite());
			Order order = orders.get(encoder.getSite());

        if(action.equals("book")){
        	//进行对对应场馆的预定
 			site.book(user, date, time);
 			//如果预定成功创建一条订单
 			if(site.getMsg().equals("the booking is accepted!")){
 				//计算价格，是否有优惠价格
 				order.generateOrder(date, time, action);
 			}
 			System.out.println(site.getMsg());
 		}else if(action.equals("cancle")){
 			site.cancle(user, date, time);
 			if(site.getMsg().equals("the booking is accepted!")){
 				order.generateOrder(date, time, action);
 			}
 			System.out.println(site.getMsg());
 		}else if(action.equals("summary")){
 			System.out.println("收入汇总");
 			System.out.println("---");
 			orders.get("A").printSummary();
 			System.out.println("");
 			orders.get("B").printSummary();
 			System.out.println("");
 			orders.get("C").printSummary();
 			System.out.println("");
 			orders.get("D").printSummary();
 			System.out.println("---");
 			System.out.println("总计："+(Integer.valueOf(orders.get("A").getTotal())+
 					Integer.valueOf(orders.get("B").getTotal())+
 					Integer.valueOf(orders.get("C").getTotal())+
 					Integer.valueOf(orders.get("D").getTotal()))+"元");
 		}
     }
  }
}
