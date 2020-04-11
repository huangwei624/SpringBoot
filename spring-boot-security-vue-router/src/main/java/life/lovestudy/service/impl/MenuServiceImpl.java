package life.lovestudy.service.impl;

import life.lovestudy.entity.Menu;
import life.lovestudy.mapper.MenuMapper;
import life.lovestudy.service.MenuService;
import life.lovestudy.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<MenuVO> getAllMenu() {
		return menuMapper.getAllMenu();
	}
	
	@Override
	public int saveOne(Menu menu) {
		return menuMapper.saveOne(menu);
	}
	
	/**
	 * 获取所有的父菜单
	 * @return
	 */
	@Override
	public List<Menu> getParentMenu() {
		return menuMapper.getParentMenu();
	}
	
	@Override
	public int updateMenu(Menu menu) {
		return menuMapper.updateMenu(menu);
	}
	
	@Override
	public int deleteById(int id) {
		return menuMapper.deleteById(id);
	}
}
