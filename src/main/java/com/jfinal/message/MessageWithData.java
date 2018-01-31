package com.jfinal.message;

public class MessageWithData extends Message{

	private Object data;

	public MessageWithData(){
		
	}
	
	public MessageWithData(Status status,String message,Object data){
		super(status, message);
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
