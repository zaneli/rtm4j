package com.zaneli.rtm4j.model.groups;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.groups.GroupInfo.Contact;

public class GroupFactory extends RspFactory<GroupInfo> {

	private static GroupFactory factory;

	private GroupFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/group", GroupInfo.class);
		digester.addSetNext("rsp/group", "setResponse");
		digester.addSetProperties("rsp/group", "id", "id");
		digester.addSetProperties("rsp/group", "name", "name");
		digester.addObjectCreate("rsp/group/contacts/contact", Contact.class);
		digester.addSetNext("rsp/group/contacts/contact", "addContact");
		digester.addSetProperties("rsp/group/contacts/contact", "id", "id");
	}

	public static synchronized GroupFactory getInstance() {
		if (factory == null) {
			factory = new GroupFactory();
		}
		return factory;
	}
}
