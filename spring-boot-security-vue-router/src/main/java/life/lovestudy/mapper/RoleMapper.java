package life.lovestudy.mapper;

import life.lovestudy.entity.Role;
import life.lovestudy.entity.RoleMenu;
import life.lovestudy.entity.UserRole;

import java.util.ArrayList;
import java.util.List;

public interface RoleMapper {
	
	int save(Role role);
	
	List<Role> getAll();
	
	int deleteById(int id);
	
	/**
	 * 批量插入
	 * @param roleMenus
	 * @return
	 */
	int addRoleMenus(ArrayList<RoleMenu> roleMenus);
	
	/**
	 * 删除关系
	 * @param roleId
	 */
	void deleteRoleMenuByRoleId(int roleId);
	
	/**
	 * 获取MenuId 数组
	 * @return
	 */
	int[] getMenuIdByRoleId(int roleId);
	
	int saveUserRole(UserRole userRole);
	
	/**
	 * 根据用户id获取角色信息
	 * @param userId
	 * @return
	 */
	// List<Role> findRoleByUserId(int userId);
}
