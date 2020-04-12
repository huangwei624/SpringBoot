package life.lovestudy.utils;

import java.util.List;

// 层级菜单
public class TreeMenu {
	
	private int id;
	private String name;
	private List<TreeMenu> children;  // 子菜单
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TreeMenu> getChildren() {
		return children;
	}
	
	public void setChildren(List<TreeMenu> children) {
		this.children = children;
	}
}
