package life.lovestudy.vo;

public class ResultVO {
	private int status;    // 响应码
	private String message;     // 相应信息，比如成功，失败
	private Object data;    // 相应成功的数据
	
	public ResultVO() {
	}
	
	public ResultVO(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
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
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
}
