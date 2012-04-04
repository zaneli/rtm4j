package com.zaneli.rtm4j.model.groups;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.groups.GroupsGetListRsp.Contact;
import com.zaneli.rtm4j.model.groups.GroupsGetListRsp.Group;

public class GroupsGetListRspFactory extends RspFactory<GroupsGetListRsp> {

	private static GroupsGetListRspFactory factory;

	private GroupsGetListRspFactory() {
		super(GroupsGetListRsp.class);
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/groups/group", Group.class);
		digester.addSetNext("rsp/groups/group", "addGroup");
		digester.addSetProperties("rsp/groups/group", "id", "id");
		digester.addSetProperties("rsp/groups/group", "name", "name");
		digester.addObjectCreate("rsp/groups/group/contacts/contact", Contact.class);
		digester.addSetNext("rsp/groups/group/contacts/contact", "addContact");
		digester.addSetProperties("rsp/groups/group/contacts/contact", "id", "id");
	}

	@Override
	public GroupsGetListRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized GroupsGetListRspFactory getInstance() {
		if (factory == null) {
			factory = new GroupsGetListRspFactory();
		}
		return factory;
	}
}
