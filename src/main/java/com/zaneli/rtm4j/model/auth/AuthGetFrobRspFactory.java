package com.zaneli.rtm4j.model.auth;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;

public class AuthGetFrobRspFactory extends RspFactory<AuthGetFrobRsp> {

	private static AuthGetFrobRspFactory factory;

	private AuthGetFrobRspFactory() {
		super(AuthGetFrobRsp.class);
		getDigester().addBeanPropertySetter("rsp/frob");
	}

	@Override
	public AuthGetFrobRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized AuthGetFrobRspFactory getInstance() {
		if (factory == null) {
			factory = new AuthGetFrobRspFactory();
		}
		return factory;
	}
}
