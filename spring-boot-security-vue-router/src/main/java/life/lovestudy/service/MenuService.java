package life.lovestudy.service;

import life.lovestudy.entity.Menu;
import life.lovestudy.vo.MenuVO;

import java.util.List;

public interface MenuService {
	
	List<MenuVO> getAllMenu();
	
	int saveOne(Menu menu);
	
	/**
	 * 获取所有的父菜单
	 * @return
	 */
	List<Menu> getParentMenu();
	
	int updateMenu(Menu menu);
	
	int deleteById(int id);
}
