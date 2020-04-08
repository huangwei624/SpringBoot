package life.lovestudy.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
	
	private int id;
	private String username;
	private String password;
	private Boolean isLock; // 账号是否锁定
	private Boolean isExpired; // 账号是否过期
	private List<Role> roles;   // 当前用户的所有角色
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * 账号是否过期
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return this.isExpired;
	}
	
	/**
	 * 账号是否锁定
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return this.isLock;
	}
	
	/**
	 * 凭证是否过期
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	
	/**
	 * 是否启用
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return false;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getLock() {
		return isLock;
	}
	
	public void setLock(Boolean lock) {
		isLock = lock;
	}
	
	public Boolean getExpired() {
		return isExpired;
	}
	
	public void setExpired(Boolean expired) {
		isExpired = expired;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
