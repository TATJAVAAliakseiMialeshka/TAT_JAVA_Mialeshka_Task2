package com.epam.ta.library.bean;

import java.io.Serializable;
import java.sql.Date;

public class Subscription implements Serializable{

	private static final long serialVersionUID = -5652380306813206701L;
	
	private Integer id;
	private Integer userId;
	private Integer bookId;
	private Date startDate;
	private Date endDate;
	private SbsStatus status;

	private String bookName;

	public enum SbsStatus {
		P, S, E
	}

	public Integer getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public SbsStatus getStatus() {
		return status;
	}

	public void setId(Integer id) {
		if (null != id) {
			this.id = id;
		}
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	};

	public void setStartDate(Date startDate) {
		if (null != startDate) {
			this.startDate = startDate;
		}
	}

	public void setEndDate(Date endDate) {
		if (null != endDate) {
			this.endDate = endDate;
		}
	}

	public void setStatus(String statusStr) {
		if (status != null) {
			for (SbsStatus status : SbsStatus.values()) {
				if (status.name().equals(statusStr)) {
					this.status = SbsStatus.valueOf(statusStr);
					break;
				}
			}
		}
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		if (null != bookName && !bookName.isEmpty()) {
			this.bookName = bookName;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Subscription other = (Subscription) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", status=" + status + "]";
	}

}
