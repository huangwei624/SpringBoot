package life.lovestudy.entity;

// 角色， 菜单中间表
public class RoleMenu {
	private int id;
	private int roleId;
	private int menuId;
	
	public RoleMenu() {
	}
	
	public RoleMenu(int roleId, int menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getMenuId() {
		return menuId;
	}
	
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
}
