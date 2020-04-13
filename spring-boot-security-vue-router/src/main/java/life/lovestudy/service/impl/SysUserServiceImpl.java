package life.lovestudy.service.impl;

import life.lovestudy.entity.User;
import life.lovestudy.entity.UserRole;
import life.lovestudy.mapper.UserMapper;
import life.lovestudy.service.MenuService;
import life.lovestudy.service.RoleService;
import life.lovestudy.service.SysUserService;
import life.lovestudy.utils.UserSaveForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 通过用户名, 获取当前用户的所有信息，包括用户名、密码、权限
	 * @param s
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userMapper.loadUserByUsername(s);
		if(user == null) throw new UsernameNotFoundException("用户不存在");
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}
	
	/**
	 * 保存用户
	 * @param userSaveForm
	 * @return
	 */
	@Override
	@Transactional
	public int saveUser(UserSaveForm userSaveForm) {
		String password = passwordEncoder.encode(userSaveForm.getPassword());
		User user = new User();
		user.setUsername(userSaveForm.getUsername());
		user.setPassword(password);
		int count = userMapper.saveUser(user);      // 保存user, 并得到id属性值
		if(count <= 0) return count;    // 说明插入失败
		int roleId = userSaveForm.getRoleId();
		UserRole userRole = new UserRole(user.getId(), roleId);
		return roleService.updateUserRole(userRole);
	}
}
