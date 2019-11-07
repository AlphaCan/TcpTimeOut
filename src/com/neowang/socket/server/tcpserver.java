package com.neowang.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class tcpserver{
	
	private int tcp_max_buffer_size = 1024;
	private byte[] tcp_receive_buffer = new byte[tcp_max_buffer_size];
	private int status = 0;
	
	Socket server;
	InputStream in;
	OutputStream out;
	
	public tcpserver(Socket socket) {		
		this.server = socket;	
	}	
	
	public void get_tcp_data() {
		int temp = 0;
		
		try {
			in = server.getInputStream();
			while(true) {
				
				temp = in.read();
				if(temp == -1) return;
				
				if((status&0x8000) == 0) {
					if((status&0x4000) == 1) {
						if(temp != 0x0a) status = 0;
						else 
							status |= 0x8000;
					}
				}
				else {
					if (temp == 0x0d) status |= 0x4000;
					else {
						tcp_receive_buffer[status&0x3fff] = (byte)temp;
						status++;
						if((status&0x3fff) > tcp_max_buffer_size -1) status = 0;
					}
				}								
				
			}
						
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("tcp接收数据出错");
		}		
	}
	
	
	public void send_tcp_data(String string,int length) {
		try {
			
			byte[] message = string.getBytes();
						
			out = server.getOutputStream();
			
			out.write(message, 0, length);
			
			out.flush();
			
		} catch (IOException e) {
			System.out.println("tcp发送数据时出错");
			e.printStackTrace();
		}
	}
	
	public void tcp_socket_close() {
		try {
			out.close();
			in.close();
			server.close();
		} catch (IOException e) {
			System.out.println("socket关闭时出错");
			e.printStackTrace();
		}
		
	}
	
	
	public byte[] get_tcp_receive_buffer() {
		return tcp_receive_buffer;
	}
	
	public int get_receive_status() {
		return status;
	}
	
	public void set_receive_status(int sta) {
		status = sta;
	}
	
	
}
