package com.zaneli.rtm4j.model.tasks;

import java.util.ArrayList;
import java.util.List;

import com.zaneli.rtm4j.model.Model;

public class Taskseries extends Model {

	private String id;
	private String created;
	private String modified;
	private String name;
	private String source;
	private String url;
	private String locationId;
	private List<Tag> tags;
	private List<Note> notes;
	private Task task;

	public Taskseries() {
		tags = new ArrayList<Tag>();
		notes = new ArrayList<Note>();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public Tag[] getTags() {
		return tags.toArray(new Tag[tags.size()]);
	}
	public void addTag(Tag tag) {
		tags.add(tag);
	}
	public Note[] getNotes() {
		return notes.toArray(new Note[notes.size()]);
	}
	public void addNote(Note note) {
		notes.add(note);
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
}
