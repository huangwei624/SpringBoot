package life.lovestudy.vo;

public class ResultVO {
	private int responseCode;    // 响应码
	private String message;     // 相应信息，比如成功，失败
	private Object data;    // 相应成功的数据
	
	public ResultVO() {
	}
	
	public ResultVO(int responseCode, String message, Object data) {
		this.responseCode = responseCode;
		this.message = message;
		this.data = data;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
}
