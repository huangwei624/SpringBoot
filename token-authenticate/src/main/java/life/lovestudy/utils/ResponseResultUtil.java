package life.lovestudy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import life.lovestudy.enums.ResponseCodeEnum;
import life.lovestudy.pojo.ResponseResult;

// 响应工具类
public class ResponseResultUtil {
	
	// 成功
	public static String success(Object object){
		ResponseResult responseResult = new ResponseResult(ResponseCodeEnum.SUCCESS.getcode(), object);
		return JSONObject.toJSONString(responseResult);
	}
	
	// 失败
	public static String fail(Object object){
		ResponseResult responseResult = new ResponseResult(ResponseCodeEnum.FAIL.getcode(), object);
		return JSONObject.toJSONString(responseResult);
	}
	
	// 没有认证
	public static String nonAuthenticate(){
		ResponseResult responseResult = new ResponseResult(ResponseCodeEnum.NO_AUTHENTICATION.getcode(), ResponseCodeEnum.NO_AUTHENTICATION.getMessage());
		return JSONObject.toJSONString(responseResult);
	}
}
