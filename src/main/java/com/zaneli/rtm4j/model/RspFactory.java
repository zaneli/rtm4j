package com.zaneli.rtm4j.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.Rsp.Err;

public abstract class RspFactory<T extends Rsp> {

	private final Digester digester;

	protected RspFactory(Class<? extends T> cls) {
		digester = new Digester();
		digester.addObjectCreate("rsp", cls);
		digester.addSetProperties("rsp", "stat", "stat");
		digester.addObjectCreate("rsp/err", Err.class);
		digester.addSetNext("rsp/err", "setErr");
		digester.addSetProperties("rsp/err", "code", "code");
		digester.addSetProperties("rsp/err", "msg", "msg");
	}

	public abstract T create(InputStream in) throws IOException, SAXException;

	protected Digester getDigester() {
		return digester;
	}
}
