package life.lovestudy.controller;

import life.lovestudy.entity.User;
import life.lovestudy.service.MenuService;
import life.lovestudy.service.RoleService;
import life.lovestudy.service.SysUserService;
import life.lovestudy.utils.ResponseCodeEnum;
import life.lovestudy.utils.UserSaveForm;
import life.lovestudy.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/all")
	public ResultVO all(){
		List<life.lovestudy.entity.User> user = sysUserService.getAllUsers();
		return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "成功", user);
	}
	
	@PostMapping("/save")
	public ResultVO all(@RequestBody UserSaveForm userSaveForm){
		if (userSaveForm == null) return new ResultVO(ResponseCodeEnum.FAIL.getCode(), "失败", null);
		int count = sysUserService.saveUser(userSaveForm);
		if(count > 0){
			return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "成功", null);
		}else{
			return new ResultVO(ResponseCodeEnum.FAIL.getCode(), "失败", null);
		}
	}
	
}
