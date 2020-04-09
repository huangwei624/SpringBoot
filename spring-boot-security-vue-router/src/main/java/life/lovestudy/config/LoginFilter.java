package life.lovestudy.config;

import life.lovestudy.exception.VerifyCodeErrorException;
import life.lovestudy.utils.ResponseCodeEnum;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		// 如果请求不是post方式，直接抛出异常
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}
		String session_verify_code  = (String)(request.getSession().getAttribute("verify_code"));
		String verifyCode = request.getParameter("verifyCode");
		// 验证码不正确
		if(!session_verify_code.equalsIgnoreCase(verifyCode)){
			throw new VerifyCodeErrorException(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode(),
					ResponseCodeEnum.VERIFY_CODE_ERROR.getDescription());
		}
		return super.attemptAuthentication(request, response);
	}
}
