package life.lovestudy.mapper;

import life.lovestudy.entity.User;

import java.util.List;

public interface UserMapper {
	
	/**
	 * 根据用户id 获取用户信息
	 * @param id
	 * @return
	 */
	User findById(Integer id);
	
	
	List<User> getAllUsers();
	
	int saveUser(User user);
	
	User loadUserByUsername(String username);
}
