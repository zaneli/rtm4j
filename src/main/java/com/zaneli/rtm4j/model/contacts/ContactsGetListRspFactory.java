package com.zaneli.rtm4j.model.contacts;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.contacts.ContactsGetListRsp.Contact;

public class ContactsGetListRspFactory extends RspFactory<ContactsGetListRsp> {

	private static ContactsGetListRspFactory factory;

	private ContactsGetListRspFactory() {
		super(ContactsGetListRsp.class);
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/contacts/contact", Contact.class);
		digester.addSetNext("rsp/contacts/contact", "addContact");
		digester.addSetProperties("rsp/contacts/contact", "id", "id");
		digester.addSetProperties("rsp/contacts/contact", "fullname", "fullname");
		digester.addSetProperties("rsp/contacts/contact", "username", "username");
	}

	@Override
	public ContactsGetListRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized ContactsGetListRspFactory getInstance() {
		if (factory == null) {
			factory = new ContactsGetListRspFactory();
		}
		return factory;
	}
}
