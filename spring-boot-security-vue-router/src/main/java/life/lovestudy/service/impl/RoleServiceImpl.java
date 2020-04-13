package life.lovestudy.service.impl;

import life.lovestudy.entity.Menu;
import life.lovestudy.entity.Role;
import life.lovestudy.entity.RoleMenu;
import life.lovestudy.entity.UserRole;
import life.lovestudy.mapper.MenuMapper;
import life.lovestudy.mapper.RoleMapper;
import life.lovestudy.service.MenuService;
import life.lovestudy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public int saveOne(Role role) {
		return roleMapper.save(role);
	}
	
	/**
	 * 获取所有的角色
	 * @return
	 */
	@Override
	public List<Role> all() {
		return roleMapper.getAll();
	}
	
	@Override
	public int deleteById(int id) {
		return roleMapper.deleteById(id);
	}
	
	/**
	 * 更新某个角色对应的权限（菜单）
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	@Override
	@Transactional
	public int updateRoleToMenu(int roleId, int[] menuIds) {
		// 为什么这里会创建两个menuList1， 下面会迭代menuList1，并修改menuList1，如果在一个操作会抛出ConcurrentModificationException 异常
		List<Integer> menuList1 = Arrays.stream(menuIds).boxed().collect(Collectors.toList());
		List<Integer> menuList2 = Arrays.stream(menuIds).boxed().collect(Collectors.toList());
		// 先删除之前的关系
		roleMapper.deleteRoleMenuByRoleId(roleId);
		// 重新建立 Role 和 Menu 关系， 添加中间数据
		ArrayList<RoleMenu> roleMenus = new ArrayList<>();
		for(Integer item : menuList1){
			Menu menu = menuMapper.getById(item);
			// 由于前端传过来的 menuIds 只包含 子菜单的id ,所以这里面还要加上父菜单的id
			if (menu.getParentId() != 0 && !menuList2.contains(menu.getParentId())){
				menuList2.add(menu.getParentId());
				RoleMenu p_roleMenu = new RoleMenu(roleId, menu.getParentId());
				roleMenus.add(p_roleMenu);
			}
			RoleMenu roleMenu = new RoleMenu(roleId, item);
			roleMenus.add(roleMenu);
		}
		// 批量插入 roleMenu
		return roleMapper.addRoleMenus(roleMenus);
	}
	
	/**
	 * 根据 角色 获取菜单id
	 * @param roleId
	 * @return
	 */
	@Override
	public int[] getMenuIdByRoleId(int roleId) {
		return roleMapper.getMenuIdByRoleId(roleId);
	}
	
	/**
	 * 增加user role 关系
	 * @param userRole
	 * @return
	 */
	@Override
	public int updateUserRole(UserRole userRole) {
		return roleMapper.saveUserRole(userRole);
	}
	
}
