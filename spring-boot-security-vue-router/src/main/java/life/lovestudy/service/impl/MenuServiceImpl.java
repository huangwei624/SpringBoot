package life.lovestudy.service.impl;

import life.lovestudy.entity.Menu;
import life.lovestudy.mapper.MenuMapper;
import life.lovestudy.service.MenuService;
import life.lovestudy.utils.TreeMenu;
import life.lovestudy.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<MenuVO> getAllMenu() {
		return menuMapper.getAllMenu();
	}
	
	/**
	 * 获取 该角色对应的所有 权限（菜单）
	 * @param roleId
	 * @return
	 */
	@Override
	public List<MenuVO> getAllMenu(int roleId) {
		return menuMapper.getAllMenuByRoleId(roleId);
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
	
	/**
	 * 获取层级菜单
	 * @return
	 */
	@Override
	public List<TreeMenu> treeMenu() {
		// 获取所有的菜单
		List<MenuVO> allMenu = getAllMenu();
		return doTreeMenu(allMenu);
	}
	
	/**
	 * 根据 角色 获取层级菜单
	 * @param roleId
	 * @return
	 */
	@Override
	public List<TreeMenu> treeMenu(int roleId) {
		List<MenuVO> allMenu = getAllMenu(roleId);
		return doTreeMenu(allMenu);
	}
	
	@Override
	public Menu getById(int id) {
		return menuMapper.getById(id);
	}
	
	private List<TreeMenu> doTreeMenu(List<MenuVO> allMenu){
		ArrayList<TreeMenu> treeMenus = new ArrayList<>();
		// 迭代找出所有父菜单
		for (MenuVO menuvo: allMenu) {
			if (menuvo.getParentId() == 0) {      // 父菜单
				TreeMenu treeMenu = new TreeMenu();
				treeMenu.setId(menuvo.getId());
				treeMenu.setName(menuvo.getName());
				treeMenu.setPath(menuvo.getPath());
				treeMenu.setIconClz(menuvo.getIconClz());
				ArrayList<TreeMenu> children = new ArrayList<>();
				for (MenuVO menuvo2 : allMenu) {      // 给父菜单找所有的子菜单
					if (menuvo2.getId() != menuvo.getId()) {
						if (menuvo2.getParentId() == menuvo.getId()) {
							TreeMenu treeMenu2 = new TreeMenu();
							treeMenu2.setId(menuvo2.getId());
							treeMenu2.setName(menuvo2.getName());
							treeMenu2.setPath(menuvo2.getPath());
							treeMenu2.setIconClz(menuvo2.getIconClz());
							children.add(treeMenu2);
						}
					}
				}
				// 子菜单找完后，给当前treeMenu 设置 children
				treeMenu.setChildren(children);
				// 将这个父亲菜单及其子菜单放入到 TreeMenu 集合中
				treeMenus.add(treeMenu);
			}
		}
		return treeMenus;
	}
	
	
}
