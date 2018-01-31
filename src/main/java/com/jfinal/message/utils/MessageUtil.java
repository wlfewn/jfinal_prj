package com.jfinal.message.utils;

import com.jfinal.message.Message;
import com.jfinal.message.Status;

public class MessageUtil {
	
	private MessageUtil(){}
	
	public static Message createSuccessMsg(String msg){
		return new Message(Status.success, msg);
	}
	
	public static Message createErrorMsg(String msg){
		return new Message(Status.danger, msg);
	}
	
	public static boolean matchSuccess(Message message){
		return message.getStatus().equals(Status.success);
	}
	
	public static boolean matchError(Message message){
		return message.getStatus().equals(Status.danger);
	}
}
