package com.zaneli.rtm4j.model.lists;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspFactory;

public class ListFactory extends RspFactory<ListInfo> {

	private static ListFactory factory;

	private ListFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/list", ListInfo.class);
		digester.addSetNext("rsp/list", "setResponse");
		digester.addSetProperties("rsp/list", "id", "id");
		digester.addSetProperties("rsp/list", "name", "name");
		digester.addSetProperties("rsp/list", "deleted", "deleted");
		digester.addSetProperties("rsp/list", "locked", "locked");
		digester.addSetProperties("rsp/list", "archived", "archived");
		digester.addSetProperties("rsp/list", "position", "position");
		digester.addSetProperties("rsp/list", "smart", "smart");
	}

	public static synchronized ListFactory getInstance() {
		if (factory == null) {
			factory = new ListFactory();
		}
		return factory;
	}
}
