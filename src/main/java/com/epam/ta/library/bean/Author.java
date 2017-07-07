package com.epam.ta.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Author implements Serializable{

	private static final long serialVersionUID = -6073658929164760617L;
	
	private Integer id;
	private String name;
	private List<Book> bookList = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Book> getBookList() {
		return bookList;
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

	public void setBookList(List<Book> bookList) {
		if (null != bookList) {
			this.bookList = bookList;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Author other = (Author) obj;
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
		return "Author [id=" + id + ", name=" + name + "]";
	}

}
