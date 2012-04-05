package com.zaneli.rtm4j.model.tasks;

import java.util.ArrayList;

import com.zaneli.rtm4j.model.Model;

public class TaskInfo extends Model {
	
	private String rev;

	private java.util.List<List> list;

	public TaskInfo() {
		list = new ArrayList<List>();
	}

	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public List[] getList() {
		return list.toArray(new List[list.size()]);
	}
	public void addList(List list) {
		this.list.add(list);
	}
}
