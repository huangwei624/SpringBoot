package life.lovestudy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import life.lovestudy.exception.VerifyCodeErrorException;
import life.lovestudy.service.SysUserService;
import life.lovestudy.utils.ResponseCodeEnum;
import life.lovestudy.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Bean
	public LoginFilter loginFilter() throws Exception {
		LoginFilter loginFilter = new LoginFilter();
		// 认证失败处理
		loginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			                                    AuthenticationException e) throws IOException, ServletException {
				ResultVO resultVO = new ResultVO();
				if(e instanceof VerifyCodeErrorException){  // 验证码不正确
					resultVO.setResponseCode(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode());
					resultVO.setMessage("验证码不正确");
				}else if (e instanceof LockedException) {   // 账号被锁定
					resultVO.setResponseCode(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode());
					resultVO.setMessage("账号被锁定，请联系管理员");
				} else if (e instanceof AccountExpiredException) {  // 账户已过期
					resultVO.setResponseCode(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode());
					resultVO.setMessage("账户已过期，请联系管理员");
				}else if (e instanceof BadCredentialsException) {  // 用户名密码不正确
					resultVO.setResponseCode(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode());
					resultVO.setMessage("用户名密码不正确");
				}
				response.setContentType("application/json;charset=utf-8");
				String origin = request.getHeader("Origin");
				if (origin != null) {
					response.setHeader("Access-Control-Allow-Origin", origin);
				}
				response.setHeader("Access-Control-Allow-Credentials", "true");
				try {
					PrintWriter writer = response.getWriter();
					writer.write(new ObjectMapper().writeValueAsString(resultVO));
					writer.flush();
					writer.close();
				} catch (IOException ioe) {
					e.printStackTrace();
				}
			}
		});
		// 认证成功处理
		loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
			                                    HttpServletResponse httpServletResponse,
			                                    Authentication authentication) throws IOException, ServletException {
			}
		});
		loginFilter.setAuthenticationManager(authenticationManagerBean());
		loginFilter.setFilterProcessesUrl("/login");
		return loginFilter;
	}
	
	/**
	 * 该方法可以在内存中设置用户的认证密码和用户名，也可以通过UserDetailService辅助认证管理器进行认证
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(sysUserService);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**","/index.html","/img/**","/fonts/**","/favicon.ico","/verifyCode");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Value("${vue-serve-url}")
	private String vueServeUrl;
	
	/**
	 * 跨域访问
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowCredentials(true)
				.allowedHeaders("*")
				.allowedMethods("*")
				.allowedOrigins(vueServeUrl);
	}
}
