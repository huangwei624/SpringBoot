package life.lovestudy.service;

import life.lovestudy.entity.Role;
import life.lovestudy.entity.UserRole;

import java.util.List;

public interface RoleService {
	
	int saveOne(Role role);
	
	/**
	 * 获取所有的角色
	 * @return
	 */
	List<Role> all();
	
	int deleteById(int id);
	
	/**
	 * 更新某个角色对应的权限（菜单）
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int updateRoleToMenu(int roleId, int[] menuIds);
	
	/**
	 * 根据 角色 获取菜单id
	 * @param roleId
	 * @return
	 */
	int[] getMenuIdByRoleId(int roleId);
	
	/**
	 * 增加user role 关系
	 * @param userRole
	 * @return
	 */
	int updateUserRole(UserRole userRole);
}
