package life.lovestudy.mapper;

import life.lovestudy.entity.Menu;
import life.lovestudy.vo.MenuVO;

import java.util.List;

public interface MenuMapper {
	/**
	 * 保存一个路由信息
	 * @param menu
	 * @return
	 */
	int saveOne(Menu menu);
	
	/**
	 * 获取所有的Menu
	 * @return
	 */
	List<MenuVO> getAllMenu();
	
	/**
	 * 获取所有的父菜单
	 * @return
	 */
	List<Menu> getParentMenu();
	
	/**
	 * 修改
	 * @param menu
	 * @return
	 */
	int updateMenu(Menu menu);
	
	int deleteById(int id);
	
	/**
	 * 通过角色id获取menu
	 * @return
	 */
	List<MenuVO> getAllMenuByRoleId(int roleId);
	
	Menu getById(int id);
	
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
