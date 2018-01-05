package cn.itcast.exception;

public class MyException extends Exception {
	private String message;
	public String myexception(){
		
		return message;
	}
	public MyException() {
		super();
		
	}
	//自定义异常
	public MyException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
