package life.lovestudy.mapper;

import life.lovestudy.entity.Menu;

import java.util.List;

public interface MenuMapper {
	/**
	 * 保存一个路由信息
	 * @param menu
	 * @return
	 */
	int saveOne(Menu menu);
	
	/**
	 * 根据角色id获取Menu信息
	 * @param roleId
	 * @return
	 */
	// List<Menu> findMenuByRoleId(int roleId);
	
	/**
	 * 根据用户id获取Menu信息
	 * @param userId
	 * @return
	 */
	// List<Menu> findMenuByUserId(int userId);
}
