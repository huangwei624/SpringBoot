package life.lovestudy.utils;

public enum  ResponseCodeEnum {
	FAIL(0, "失败"),
	SUCCESS(1, "成功"),
	
	VERIFY_CODE_ERROR(100, "验证码错误"),
	ACCOUNT_LOCKED(101, "账户被锁定"),
	ACCOUNT_EXPIRE(102, "账户已过期"),
	NON_ERROR(150, "未知错误"),
	;
	
	private int code;
	private String description;
	
	ResponseCodeEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
