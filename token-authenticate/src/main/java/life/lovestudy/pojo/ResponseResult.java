package life.lovestudy.pojo;

import life.lovestudy.enums.ResponseCodeEnum;
import lombok.Data;

// 响应结果
@Data
public class ResponseResult {
	private int status; // 响应状态
	private Object message; // 响应内容
	
	public ResponseResult(int status, Object message) {
		this.status = status;
		this.message = message;
	}
}
