package com.zaneli.rtm4j.model.contacts;

import com.zaneli.rtm4j.model.Model;

public class ContactInfo extends Model {

	private String id;
	private String fullname;
	private String username;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
