package com.zaneli.rtm4j.model.tasks;

import com.zaneli.rtm4j.model.Model;

public class Task extends Model {

	private String id;

	private String due;

	private String hasDueTime;

	private String added;

	private String completed;

	private String deleted;

	private String priority;

	private String postponed;

	private String estimate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getHasDueTime() {
		return hasDueTime;
	}

	public void setHasDueTime(String hasDueTime) {
		this.hasDueTime = hasDueTime;
	}

	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPostponed() {
		return postponed;
	}

	public void setPostponed(String postponed) {
		this.postponed = postponed;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
}
