package life.lovestudy.service;

import life.lovestudy.entity.Menu;
import life.lovestudy.utils.TreeMenu;
import life.lovestudy.vo.MenuVO;

import java.util.List;

public interface MenuService {
	
	List<MenuVO> getAllMenu();
	
	/**
	 * 获取 该角色对应的所有 权限（菜单）
	 * @param roleId
	 * @return
	 */
	List<MenuVO> getAllMenu(int roleId);
	
	int saveOne(Menu menu);
	
	/**
	 * 获取所有的父菜单
	 * @return
	 */
	List<Menu> getParentMenu();
	
	int updateMenu(Menu menu);
	
	int deleteById(int id);
	
	/**
	 * 获取层级菜单
	 * @return
	 */
	List<TreeMenu> treeMenu();
	
	/**
	 * 根据 角色 获取层级菜单
	 * @param roleId
	 * @return
	 */
	List<TreeMenu> treeMenu(int roleId);
	
	Menu getById(int id);
	
	
}
