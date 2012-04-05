package com.zaneli.rtm4j.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

public abstract class RspFactory<T extends Model> {

	private final Digester digester;

	protected RspFactory() {
		digester = new Digester();
		
		digester.addObjectCreate("rsp", Rsp.class);
		digester.addSetProperties("rsp", "stat", "stat");
		
		digester.addObjectCreate("rsp/transaction", Transaction.class);
		digester.addSetNext("rsp/transaction", "setTransaction");
		digester.addSetProperties("rsp/transaction", "id", "id");
		digester.addSetProperties("rsp/transaction", "undoable", "undoable");
		
		digester.addObjectCreate("rsp/err", Err.class);
		digester.addSetNext("rsp/err", "setErr");
		digester.addSetProperties("rsp/err", "code", "code");
		digester.addSetProperties("rsp/err", "msg", "msg");
	}

	public Rsp<T> create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	protected Digester getDigester() {
		return digester;
	}
}
