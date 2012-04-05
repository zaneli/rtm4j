package com.zaneli.rtm4j.model.auth;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspFactory;

public class TokenFactory extends RspFactory<TokenInfo> {

	private static TokenFactory factory;

	private TokenFactory() {
		Digester digester = getDigester();
		getDigester().addObjectCreate("rsp/auth", TokenInfo.class);
		getDigester().addSetNext("rsp/auth", "setResponse");
		digester.addBeanPropertySetter("rsp/auth/token");
		digester.addBeanPropertySetter("rsp/auth/perms");
		digester.addSetProperties("rsp/auth/user", "id", "id");
		digester.addSetProperties("rsp/auth/user", "username", "username");
		digester.addSetProperties("rsp/auth/user", "fullname", "fullname");
	}

	public static synchronized TokenFactory getInstance() {
		if (factory == null) {
			factory = new TokenFactory();
		}
		return factory;
	}
}
