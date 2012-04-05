package com.zaneli.rtm4j.model.lists;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspsFactory;

public class ListsFactory extends RspsFactory<ListInfo> {

	private static ListsFactory factory;

	private ListsFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/lists/list", ListInfo.class);
		digester.addSetNext("rsp/lists/list", "addResponse");
		digester.addSetProperties("rsp/lists/list", "id", "id");
		digester.addSetProperties("rsp/lists/list", "name", "name");
		digester.addSetProperties("rsp/lists/list", "deleted", "deleted");
		digester.addSetProperties("rsp/lists/list", "locked", "locked");
		digester.addSetProperties("rsp/lists/list", "archived", "archived");
		digester.addSetProperties("rsp/lists/list", "position", "position");
		digester.addSetProperties("rsp/lists/list", "smart", "smart");
	}

	public static synchronized ListsFactory getInstance() {
		if (factory == null) {
			factory = new ListsFactory();
		}
		return factory;
	}
}
