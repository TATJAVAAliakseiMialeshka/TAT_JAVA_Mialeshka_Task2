package com.epam.ta.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

	private static final long serialVersionUID = 2909440320052723746L;
	
	private Integer id;
	private String name;
	private String password;
	private List<Role> roleList = new ArrayList<>();
	private UserStatus status;
	private List<Subscription> subscriptions = new ArrayList<>();

	public enum UserStatus {
		G, R, A, B
	};

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public UserStatus getStatus() {
		return status;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setId(Integer id) {
		if (null != id) {
			this.id = id;
		}
	}

	public void setName(String name) {
		if (null != name && !name.isEmpty()) {
			this.name = name;
		}
	}

	public void setPassword(String password) {
		if (null != password && !password.isEmpty()) {
			this.password = password;
		}
	}

	public void setRoleList(List<Role> roleList) {
		if (null != roleList) {
			this.roleList = roleList;
		}
	}

	public void setStatus(String statusStr) {
		if (statusStr != null) {
			for (UserStatus status : UserStatus.values()) {
				if (status.name().equals(statusStr)) {
					this.status = UserStatus.valueOf(statusStr);
					break;
				}
			}
		}
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		if (null != subscriptions) {
			this.subscriptions = subscriptions;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		User other = (User) obj;
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", status=" + status + "]";
	}

}
