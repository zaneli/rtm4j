package com.zaneli.rtm4j.model.contacts;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspFactory;

public class ContactFactory extends RspFactory<ContactInfo> {

	private static ContactFactory factory;

	private ContactFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/contact", ContactInfo.class);
		digester.addSetNext("rsp/contact", "setResponse");
		digester.addSetProperties("rsp/contact", "id", "id");
		digester.addSetProperties("rsp/contact", "fullname", "fullname");
		digester.addSetProperties("rsp/contact", "username", "username");
	}

	public static synchronized ContactFactory getInstance() {
		if (factory == null) {
			factory = new ContactFactory();
		}
		return factory;
	}
}
