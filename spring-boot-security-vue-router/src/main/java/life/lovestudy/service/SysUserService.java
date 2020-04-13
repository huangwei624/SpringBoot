package life.lovestudy.service;

import life.lovestudy.entity.User;
import life.lovestudy.utils.UserSaveForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SysUserService extends UserDetailsService {
	List<User> getAllUsers();
	
	int saveUser(UserSaveForm userSaveForm);
}
