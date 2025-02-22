package org.kwok.util;

/**
 * @Description: 返回结果的封装类
 * @date: 2018年12月25日
 * @author Kwok
 */
public class CommonResult<T> {

	public enum ResultCode {
		
		ok(0), err(-1), info(1);

		public int code;
		
		ResultCode(int code) {
			this.code = code;
		}
		
	}
	
	private int status = ResultCode.ok.code;
	private String message = "";
	private String extraMessage = "";
	private T data;
	
	protected CommonResult(){}

	protected CommonResult(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> CommonResult<T> CreateInstance(){
		return new CommonResult<T>();
	}

	public static <T> CommonResult<T> ok(T data){
		return ok(null, data);
	}

	public static <T> CommonResult<T> ok(String message, T data){
		return new CommonResult<T>(ResultCode.ok.code, message, data);
	}

	public static <T> CommonResult<T> err(String message){
		return err(message, null);
	}

	public static <T> CommonResult<T> err(String message, T data){
		return new CommonResult<T>(ResultCode.err.code, message, data);
	}

	public static <T> CommonResult<T> info(String message, T data){
		return new CommonResult<T>(ResultCode.info.code, message, data);
	}

	public void setStatus(ResultCode resultCode){
		this.status = resultCode.code; 
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getExtraMessage() {
		return extraMessage;
	}

	public void setExtraMessage(String extraMessage) {
		this.extraMessage = extraMessage;
	}
}
