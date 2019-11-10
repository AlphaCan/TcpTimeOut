package com.neowang.socket.control;

public class variable {
	
	//IO
	public static String cmd_scanner_open  						= "ctlo00\r\n";
	public static String cmd_scanner_close 						= "ctlo01\r\n";
	public static String cmd_buzzer_open   						= "ctlo30\r\n";
	public static String cmd_buzzer_close  						= "ctlo31\r\n";
	public static String cmd_run_light_open 					= "ctlo40\r\n";
	public static String cmd_run_light_close 					= "ctlo41\r\n";
	public static String cmd_fail_light_open					= "ctlo50\r\n";
	public static String cmd_fail_light_close					= "ctlo51\r\n";
	public static String cmd_pass_light_open					= "ctlo60\r\n";
	public static String cmd_pass_light_close					= "ctlo61\r\n";
	public static String cmd_Side_MB_Cyclinder_open				= "ctlo70\r\n";
	public static String cmd_Side_MB_Cyclinder_close			= "ctlo71\r\n";
	public static String cmd_Control_Push_MB_Cylinder_IN		= "ctlo80\r\n";
	public static String cmd_Control_Push_MB_Cylinder_OUT		= "ctlo90\r\n";
	public static String cmd_Control_Press_MB_Cylinder_Down		= "ctloa0\r\n";
	public static String cmd_Control_Press_MB_Cylinder_Up		= "ctlob0\r\n";
	public static String cmd_MB_Power_ON						= "ctlod0\r\n";
	public static String cmd_MB_Baterry_Connect_open			= "ctloe0\r\n";
	public static String cmd_MB_Baterry_Connect_close			= "ctloe1\r\n";
	public static String cmd_MB_Adapter_Board_Connect_open		= "ctlof0\r\n";
	public static String cmd_MB_Adapter_Board_Connect_close		= "ctlof1\r\n";
	
	//TEST
	public static String cmd_communication_with_MB				= "ctlc\r\n";
	public static String cmd_require_start_to_test				= "ctla\r\n";
	public static String cmd_ask_MB_put_ok						= "ctlib0\r\n";
	public static String cmd_ask_MB_remove_ok					= "ctlib1\r\n";
	public static String cmd_ask_MB_DC_JACK_electricity			= "ctlb2\r\n";
	public static String cmd_ask_MB_battery_electricity			= "ctlb3\r\n";
	public static String cmd_ask_MB_DC_JACK_voltage				= "ctlv0\r\n";
	public static String cmd_ask_MB_battery_voltage				= "ctlv1\r\n";
	public static String cmd_ask_MB_P5VOS_HDD_voltage			= "ctlv4\r\n";
	public static String cmd_ask_MB_LED_PWRSTBY_voltage			= "ctlv5\r\n";
	public static String cmd_ask_MB_RTC_battery_voltage			= "ctlv6\r\n";
	public static String cmd_ask_MB_HDDLED_voltage				= "ctlv7\r\n";
	public static String cmd_delay								= "ctlt\r\n";
	public static String cmd_test_over							= "ctle\r\n";
	public static String cmd_ask_control_mode					= "mode\r\n";
		
	//CONTROL
	public static byte retry_number = 10;
	public static int WAITE_TIME_OUT = 2000;
	
}
