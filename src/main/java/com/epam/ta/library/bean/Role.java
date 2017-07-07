package com.epam.ta.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable{

	private static final long serialVersionUID = -4646483753573165723L;
	
	private Integer id;
	private List<User> userList = new ArrayList<>();
	private String name;
	private Authority authority;

	public enum Authority {ROLE_SUPERADMIN, ROLE_ADMIN, ROLE_USER};
	
	public Integer getId() {
		return id;
	}

	public List<User> getUserList() {
		return userList;
	}

	public String getName() {
		return name;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setId(Integer id) {
		if (null != id) {
			this.id = id;
		}
	}

	public void setUserList(List<User> userList) {
		if (null != userList) {
			this.userList = userList;
		}
	}

	public void setName(String name) {
		if (null != name && !name.isEmpty()) {
			this.name = name;
		}
	}

	public void setAuthority(String authorityStr) {
		if (authorityStr != null) {
			for (Authority authority : Authority.values()) {
				if (authority.name().equals(authorityStr)) {
					this.authority = Authority.valueOf(authorityStr);
					break;
				}
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", authority=" + authority + "]";
	}
	
	

}
