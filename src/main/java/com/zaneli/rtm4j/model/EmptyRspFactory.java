package com.zaneli.rtm4j.model;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;

public class EmptyRspFactory extends RspFactory<EmptyInfo> {

	private static EmptyRspFactory factory;

	private EmptyRspFactory() {
	}

	@Override
	public Rsp<EmptyInfo> create(InputStream in) throws IOException, SAXException {
		Rsp<EmptyInfo> rsp = getDigester().parse(in);
		rsp.setResponse(new EmptyInfo());
		return rsp;
	}

	public static synchronized EmptyRspFactory getInstance() {
		if (factory == null) {
			factory = new EmptyRspFactory();
		}
		return factory;
	}
}
