package com.zaneli.rtm4j.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

public abstract class RspsFactory<T extends Model> {

	private final Digester digester;

	protected RspsFactory() {
		digester = new Digester();
		digester.addObjectCreate("rsp", Rsps.class);
		digester.addSetProperties("rsp", "stat", "stat");
		digester.addObjectCreate("rsp/err", Err.class);
		digester.addSetNext("rsp/err", "setErr");
		digester.addSetProperties("rsp/err", "code", "code");
		digester.addSetProperties("rsp/err", "msg", "msg");
	}

	public Rsps<T> create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	protected Digester getDigester() {
		return digester;
	}
}
