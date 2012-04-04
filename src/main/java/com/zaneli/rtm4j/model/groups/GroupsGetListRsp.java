package com.zaneli.rtm4j.model.groups;

import java.util.ArrayList;
import java.util.List;

import com.zaneli.rtm4j.model.Model;
import com.zaneli.rtm4j.model.Rsp;

public class GroupsGetListRsp extends Rsp {

	private List<Group> groups;

	public GroupsGetListRsp() {
		groups = new ArrayList<Group>();
	}
	public Group[] getGroups() {
		return groups.toArray(new Group[groups.size()]);
	}
	public void addGroup(Group group) {
		groups.add(group);
	}

	public static class Group extends Model {
		private String id;
		private String name;
		private List<Contact> contacts;

		public Group() {
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
