package com.zaneli.rtm4j.model.auth;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;

public class AuthGetTokenRspFactory extends RspFactory<AuthGetTokenRsp> {

	private static AuthGetTokenRspFactory factory;

	private AuthGetTokenRspFactory() {
		super(AuthGetTokenRsp.class);
		Digester digester = getDigester();
		digester.addBeanPropertySetter("rsp/auth/token");
		digester.addBeanPropertySetter("rsp/auth/perms");
		digester.addSetProperties("rsp/auth/user", "id", "id");
		digester.addSetProperties("rsp/auth/user", "username", "username");
		digester.addSetProperties("rsp/auth/user", "fullname", "fullname");
	}

	@Override
	public AuthGetTokenRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized AuthGetTokenRspFactory getInstance() {
		if (factory == null) {
			factory = new AuthGetTokenRspFactory();
		}
		return factory;
	}
}
