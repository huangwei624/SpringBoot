package life.lovestudy.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

import javax.naming.AuthenticationException;

// 验证码错误异常
public class VerifyCodeErrorException extends AuthenticationServiceException {
	
	private int code;
	private String msg;
	
	public VerifyCodeErrorException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
