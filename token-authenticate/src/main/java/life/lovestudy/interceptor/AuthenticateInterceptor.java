package life.lovestudy.interceptor;

import com.alibaba.fastjson.JSONObject;
import life.lovestudy.utils.AuthenticateTokenUtil;
import life.lovestudy.utils.ResponseResultUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticateInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod))   return true;   // 不是方法映射,直接放行
		String token = request.getHeader("token");
		if(token != null && AuthenticateTokenUtil.verifyToken(token)){
			return true;
		}
		
		// 响应错误信息
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(ResponseResultUtil.nonAuthenticate());
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	
	}
}
