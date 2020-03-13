package life.lovestudy.exception;

import life.lovestudy.common.ResponseCode;
import life.lovestudy.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class MyControllerAdvice {
	
	/**
	 * 捕获ServiceException 异常
	 * @param se
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(ServiceException.class)
	public ServerResponse serviceExceptionHandler(ServiceException se) {
		return ServerResponse.error(se.getMsg());
	}
	
	/**
	 * 捕获系统异常
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ServerResponse exceptionHandler(Exception e) {
		log.error("Exception: ", e);
		return ServerResponse.error(ResponseCode.SERVER_ERROR.getMsg());
	}
	
}
