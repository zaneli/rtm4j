package com.zaneli.rtm4j.model.groups;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspsFactory;
import com.zaneli.rtm4j.model.groups.GroupInfo.Contact;

public class GroupsFactory extends RspsFactory<GroupInfo> {

	private static GroupsFactory factory;

	private GroupsFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/groups/group", GroupInfo.class);
		digester.addSetNext("rsp/groups/group", "addResponse");
		digester.addSetProperties("rsp/groups/group", "id", "id");
		digester.addSetProperties("rsp/groups/group", "name", "name");
		digester.addObjectCreate("rsp/groups/group/contacts/contact", Contact.class);
		digester.addSetNext("rsp/groups/group/contacts/contact", "addContact");
		digester.addSetProperties("rsp/groups/group/contacts/contact", "id", "id");
	}

	public static synchronized GroupsFactory getInstance() {
		if (factory == null) {
			factory = new GroupsFactory();
		}
		return factory;
	}
}
