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
		
		System.out.println("��ʼ����");
		
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
//		time t = new time();//��ʱ��������ٿռ�  ���� java Ӧ�û��Զ����գ����Կ��Բ��ֶ�ɾ��
//		
//		delay.getInstance().set_time_out(t,5000); //���õȴ�ʱ�� 5000����
//		
//		
//		do {//	ִ������ֱ��ʱ�����
//			
//			i++;
//			System.out.println(i);
//			
//			delay.getInstance().sleep(1000);//��ʱ1000����	
//			
//		} while (delay.getInstance().time_is_out(t) == false);
//	}			

	
	
}
