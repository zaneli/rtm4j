package com.zaneli.rtm4j.model.contacts;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspsFactory;

public class ContactsFactory extends RspsFactory<ContactInfo> {

	private static ContactsFactory factory;

	private ContactsFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/contacts/contact", ContactInfo.class);
		digester.addSetNext("rsp/contacts/contact", "addResponse");
		digester.addSetProperties("rsp/contacts/contact", "id", "id");
		digester.addSetProperties("rsp/contacts/contact", "fullname", "fullname");
		digester.addSetProperties("rsp/contacts/contact", "username", "username");
	}

	public static synchronized ContactsFactory getInstance() {
		if (factory == null) {
			factory = new ContactsFactory();
		}
		return factory;
	}
}
