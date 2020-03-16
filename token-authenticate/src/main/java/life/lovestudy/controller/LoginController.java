package life.lovestudy.controller;

import life.lovestudy.common.Constants;
import life.lovestudy.pojo.LoginInfo;
import life.lovestudy.utils.AuthenticateTokenUtil;
import life.lovestudy.utils.ResponseResultUtil;
import life.lovestudy.utils.VerifyCodeUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
	private final String username = "123";
	private final String password="123";
	
	@PostMapping("login")
	public Object login(@RequestBody LoginInfo loginInfo, HttpServletRequest request){
		if(!loginInfo.getUsername().equals(username)||!loginInfo.getPassword().equals(password)){
			return ResponseResultUtil.fail("用户名和密码不正确！");
		}
		HttpSession session = request.getSession();
		String verifyCode = (String) session.getAttribute(Constants.SESSION_VERIFY_CODE);
		if (verifyCode == null){return ResponseResultUtil.fail("验证码存在异常，请重新获取！");}
		if (!verifyCode.equalsIgnoreCase(loginInfo.getVerifyCode())){return ResponseResultUtil.fail("验证码输入有误，请重新输入！");}
		return ResponseResultUtil.success(AuthenticateTokenUtil.getToken(loginInfo.getUsername(), loginInfo.getPassword()));
	}
	
	// 获取验证码
	@GetMapping("verifyCode")
	public Object verifyCode(HttpServletRequest request){
		String verifyCode = VerifyCodeUtil.getRandomString();
		// 将验证码放在session 中
		request.getSession().setAttribute(Constants.SESSION_VERIFY_CODE, verifyCode);
		return ResponseResultUtil.success(verifyCode);
	}
	
}
