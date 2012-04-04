package com.zaneli.rtm4j.model.contacts;

import java.util.ArrayList;
import java.util.List;

import com.zaneli.rtm4j.model.Model;
import com.zaneli.rtm4j.model.Rsp;

public class ContactsGetListRsp extends Rsp {

	private List<Contact> contacts;

	public ContactsGetListRsp() {
		contacts = new ArrayList<Contact>();
	}
	public Contact[] getContacts() {
		return contacts.toArray(new Contact[contacts.size()]);
	}
	public void addContact(Contact contact) {
		contacts.add(contact);
	}

	public static class Contact extends Model {
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
}
