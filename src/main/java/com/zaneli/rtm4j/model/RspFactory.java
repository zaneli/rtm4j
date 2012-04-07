package com.zaneli.rtm4j.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.RtmException;

public abstract class RspFactory<T extends Model> extends ResultFactory {

	protected RspFactory() {
		super(Rsp.class);
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/transaction", Transaction.class);
		digester.addSetNext("rsp/transaction", "setTransaction");
		digester.addSetProperties("rsp/transaction", "id", "id");
		digester.addSetProperties("rsp/transaction", "undoable", "undoable");
	}

	@Override
	public Rsp<T> create(InputStream in) throws IOException, SAXException, RtmException {
		Rsp<T> rsp = getDigester().parse(in);
		checkStatus(rsp);
		return rsp;
	}
}
