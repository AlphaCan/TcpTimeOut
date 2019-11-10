package com.neowang.time.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.neowang.socket.control.task;
import com.neowang.socket.server.serverutil;
import com.neowang.socket.server.tcpserver;


//import com.neowang.time.delay.delay;
//import com.neowang.time.delay.time;

public class Main {	
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("开始测试");
		
		ServerSocket server = new ServerSocket(12345);
		
		while (true) {
			Socket client = server.accept();
			tcpserver tcp = new tcpserver(client); 
			serverutil serverthread = new serverutil(tcp);
			new Thread(serverthread,"test server").start();;
			
			new task(tcp).start();
		}
		
//		byte[] a = {'r',1,2,3};
//		StringBuilder string = new StringBuilder();
//		for (byte b : a) {
//			string.append(b);
//		}
//		String str = string.toString();
//		System.out.println(new String(a));
//		
		
	}	

	
	
	
//	public static void main(String[] args) {
//		int i = 0;
//		time t = new time();//给时间变量开辟空间  用完 java 应该会自动回收，所以可以不手动删除
//		
//		delay.getInstance().set_time_out(t,5000); //设置等待时间 5000毫秒
//		
//		
//		do {//	执行任务，直到时间溢出
//			
//			i++;
//			System.out.println(i);
//			
//			delay.getInstance().sleep(1000);//延时1000毫秒	
//			
//		} while (delay.getInstance().time_is_out(t) == false);
//	}			

	
	
}
