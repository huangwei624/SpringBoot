package life.lovestudy.utils;

import java.util.List;

// 层级菜单
public class TreeMenu {
	
	private int id;
	private String name;
	private String path;
	private String iconClz;
	private List<TreeMenu> children;  // 子菜单
	
	public String getIconClz() {
		return iconClz;
	}
	
	public void setIconClz(String iconClz) {
		this.iconClz = iconClz;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
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
