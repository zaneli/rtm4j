package com.zaneli.rtm4j.model.groups;

import java.util.ArrayList;
import java.util.List;

import com.zaneli.rtm4j.model.Model;

public class GroupInfo extends Model {

	private String id;
	private String name;
	private List<Contact> contacts;

	public GroupInfo() {
		contacts = new ArrayList<Contact>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Contact[] getContacts() {
		return contacts.toArray(new Contact[contacts.size()]);
	}
	public void addContact(Contact contact) {
		contacts.add(contact);
	}

	public static class Contact extends Model {
		private String id;

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	}
}
