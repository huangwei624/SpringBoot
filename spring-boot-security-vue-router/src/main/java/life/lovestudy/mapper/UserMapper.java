package life.lovestudy.mapper;

import life.lovestudy.entity.User;

public interface UserMapper {
	
	/**
	 * 根据用户id 获取用户信息
	 * @param id
	 * @return
	 */
	User findById(Integer id);
	

}
