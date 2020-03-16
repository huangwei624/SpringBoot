package life.lovestudy.enums;

import lombok.Data;

// 状态码枚举
public enum ResponseCodeEnum {
	SUCCESS(1, "成功"),
	FAIL(0, "失败"),
	
	NO_AUTHENTICATION(100, "没有认证错误")
	;
	
	private int code;
	private String message;
	
	ResponseCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getcode() {
		return code;
	}
	
	public void setcode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
