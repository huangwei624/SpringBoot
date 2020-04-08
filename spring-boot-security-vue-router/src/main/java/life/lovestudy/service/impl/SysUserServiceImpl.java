package life.lovestudy.service.impl;

import life.lovestudy.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	/**
	 * 通过用户名, 获取当前用户的所有信息，包括用户名、密码、权限
	 * @param s
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return null;
	}
}
