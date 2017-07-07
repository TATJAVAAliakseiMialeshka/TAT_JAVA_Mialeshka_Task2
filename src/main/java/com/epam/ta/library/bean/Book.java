package com.epam.ta.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable{

	private static final long serialVersionUID = -8377263953465540921L;
	
	private Integer id;
	private Integer year;
	private Integer quantity;
	private String name;
	private String description;
	private BookIsAvailable isAvailable;
	private List<String> authorList = new ArrayList<>();
	private List<Subscription> subscriptionList = new ArrayList<>();

	public enum BookIsAvailable {
		Y, N;
	};

	public Integer getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public String getName() {
		return name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getDescription() {
		return description;
	}

	public BookIsAvailable getIsAvailable() {
		return isAvailable;
	}

	public List<String> getAuthorList() {
		return authorList;
	}

	public List<Subscription> getSubscriptionList() {
		return subscriptionList;
	}

	public void setId(Integer id) {
		if (null != id) {
			this.id = id;
		}
	}

	public void setYear(Integer year) {
		if (null != year) {
			this.year = year;
		}
	}

	public void setName(String name) {
		if (null != name && !name.isEmpty()) {
			this.name = name;
		}
	}

	public void setIsAvailable(String isAvailable) {

		    for (BookIsAvailable status : BookIsAvailable.values()) {
		        if (status.name().equals(isAvailable)) {
		    		this.isAvailable = BookIsAvailable.valueOf(isAvailable);
		    		break;
		        }
		    }
		}

	public void setAuthorList(List<String> authorList) {
		if (null != authorList) {
			this.authorList = authorList;
		}
	}

	public void setSubscriptionList(List<Subscription> subscriptionList) {
		if (null != subscriptionList) {
			this.subscriptionList = subscriptionList;
		}
	}

	public void setQuantity(Integer quantity) {
		if (null != quantity) {
			this.quantity = quantity;
		}
	}

	public void setDescription(String description) {
		if (null != description && !description.isEmpty()) {
			this.description = description;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isAvailable == null) ? 0 : isAvailable.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Book other = (Book) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAvailable != other.isAvailable)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", year=" + year + ", quantity=" + quantity + ", name=" + name + ", description="
				+ description + ", isAvailable=" + isAvailable + "]";
	}



}
