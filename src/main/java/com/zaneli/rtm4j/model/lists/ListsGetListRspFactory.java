package com.zaneli.rtm4j.model.lists;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.lists.ListsGetListRsp.List;

public class ListsGetListRspFactory extends RspFactory<ListsGetListRsp> {

	private static ListsGetListRspFactory factory;

	private ListsGetListRspFactory() {
		super(ListsGetListRsp.class);
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/lists/list", List.class);
		digester.addSetNext("rsp/lists/list", "addList");
		digester.addSetProperties("rsp/lists/list", "id", "id");
		digester.addSetProperties("rsp/lists/list", "name", "name");
		digester.addSetProperties("rsp/lists/list", "deleted", "deleted");
		digester.addSetProperties("rsp/lists/list", "locked", "locked");
		digester.addSetProperties("rsp/lists/list", "archived", "archived");
		digester.addSetProperties("rsp/lists/list", "position", "position");
		digester.addSetProperties("rsp/lists/list", "smart", "smart");
	}

	@Override
	public ListsGetListRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized ListsGetListRspFactory getInstance() {
		if (factory == null) {
			factory = new ListsGetListRspFactory();
		}
		return factory;
	}
}
