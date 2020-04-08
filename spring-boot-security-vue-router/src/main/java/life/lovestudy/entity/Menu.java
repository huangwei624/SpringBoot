package life.lovestudy.entity;

// 对应vue 的路由
public class Menu {
	
	private int id;
	private String name;    // 当前路由所具备的业务意义，比如说 /per/train 代表着“员工培训”，这里的员工培训就是name值
	private String path;    // 路由地址  /per/train
	private String component;   // 路由所对应的组件名称 PerTrain
	private String iconClz;     // 路由图标的 class 样式 “fa fa-user-circle-o”
	private int parentId;   // 当前路由的父亲路由
	private boolean isEnable;   // 当前路由是否被禁用
	private Meta meta;  //  路由元数据
	
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
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getComponent() {
		return component;
	}
	
	public void setComponent(String component) {
		this.component = component;
	}
	
	public String getIconClz() {
		return iconClz;
	}
	
	public void setIconClz(String iconClz) {
		this.iconClz = iconClz;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public boolean isEnable() {
		return isEnable;
	}
	
	public void setEnable(boolean enable) {
		isEnable = enable;
	}
	
	public Meta getMeta() {
		return meta;
	}
	
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}
