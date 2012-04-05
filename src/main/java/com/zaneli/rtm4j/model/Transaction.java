package com.zaneli.rtm4j.model;

public class Transaction extends Model {

	private String id;
	private String undoable;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUndoable() {
		return undoable;
	}
	public void setUndoable(String undoable) {
		this.undoable = undoable;
	}
}
