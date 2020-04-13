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
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
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
					resultVO.setStatus(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode());
					resultVO.setMessage("验证码不正确");
				}else if (e instanceof LockedException) {   // 账号被锁定
					resultVO.setStatus(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode());
					resultVO.setMessage("账号被锁定，请联系管理员");
				} else if (e instanceof AccountExpiredException) {  // 账户已过期
					resultVO.setStatus(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode());
					resultVO.setMessage("账户已过期，请联系管理员");
				}else if (e instanceof BadCredentialsException) {  // 用户名密码不正确
					resultVO.setStatus(ResponseCodeEnum.VERIFY_CODE_ERROR.getCode());
					resultVO.setMessage("用户名密码不正确");
				}else {
					resultVO.setStatus(ResponseCodeEnum.NON_ERROR.getCode());
					resultVO.setMessage(e.getMessage());
				}
				setSingleCors(request, response);
				// 向客服端写登录失败数据
				writeJson(response, resultVO);
			}
		});
		// 认证成功处理
		loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request,
			                                    HttpServletResponse response,
			                                    Authentication authentication) throws IOException, ServletException {
				
				// 设置跨域，这里不知道为什么统一设置的跨域没有起到作用
				setSingleCors(request, response);
				ResultVO resultVO = new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "登录成功", authentication);
				// 向客服端写登录成功数据
				writeJson(response, resultVO);
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
//		auth.inMemoryAuthentication().withUser("admin")
//				.password(passwordEncoder().encode("123456")).roles();
		auth.userDetailsService(sysUserService);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**","/index.html","/img/**","/fonts/**","/favicon.ico","/verifyCode");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 注销逻辑
		http.logout().logoutUrl("/doLogout").logoutSuccessHandler(new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			                            Authentication authentication) throws IOException, ServletException {
				// 设置跨域，这里不知道为什么统一设置的跨域没有起到作用
				setSingleCors(request, response);
				ResultVO resultVO = new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "注销成功", null);
				// 向客服端写登录成功数据
				writeJson(response, resultVO);
			}
		})
				.and().csrf().disable();
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
	
	// 单独为某个请求设置跨域访问
	private void setSingleCors(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("application/json;charset=utf-8");
		String origin = request.getHeader("Origin");
		if (origin != null) {
			response.setHeader("Access-Control-Allow-Origin", origin);
		}
		response.setHeader("Access-Control-Allow-Credentials", "true");
	}
	
	// 向客服端响应json数据
	private void writeJson(HttpServletResponse response, Object data){
		try {
			PrintWriter writer = response.getWriter();
			writer.write(new ObjectMapper().writeValueAsString(data));
			writer.flush();
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
