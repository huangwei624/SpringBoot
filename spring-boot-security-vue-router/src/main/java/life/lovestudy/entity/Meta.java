package life.lovestudy.entity;

public class Meta {
	
	private Boolean keepAlive;  // 当前页面是否需要缓存
	private Boolean requireAuth;    // 当前页面是否需要认证
	
	public Boolean getKeepAlive() {
		return keepAlive;
	}
	
	public void setKeepAlive(Boolean keepAlive) {
		this.keepAlive = keepAlive;
	}
	
	public Boolean getRequireAuth() {
		return requireAuth;
	}
	
	public void setRequireAuth(Boolean requireAuth) {
		this.requireAuth = requireAuth;
	}
}
