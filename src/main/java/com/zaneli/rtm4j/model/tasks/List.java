package com.zaneli.rtm4j.model.tasks;

import java.util.ArrayList;

import com.zaneli.rtm4j.model.Model;

public class List extends Model {

	private String id;

	private java.util.List<Taskseries> taskseriesList;

	public List() {
		taskseriesList = new ArrayList<Taskseries>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Taskseries[] getTaskseries() {
		return taskseriesList.toArray(new Taskseries[taskseriesList.size()]);
	}

	public void addTaskseries(Taskseries taskseries) {
		taskseriesList.add(taskseries);
	}
}
