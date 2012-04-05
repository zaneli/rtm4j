package com.zaneli.rtm4j.model.auth;

import com.zaneli.rtm4j.model.RspFactory;

public class FrobFactory extends RspFactory<FrobInfo> {

	private static FrobFactory factory;

	private FrobFactory() {
		getDigester().addObjectCreate("rsp/frob", FrobInfo.class);
		getDigester().addSetNext("rsp/frob", "setResponse");
		getDigester().addBeanPropertySetter("rsp/frob");
	}

	public static synchronized FrobFactory getInstance() {
		if (factory == null) {
			factory = new FrobFactory();
		}
		return factory;
	}
}
