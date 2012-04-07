package com.zaneli.rtm4j.model.reflection;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspsFactory;

public class MethodsFactory extends RspsFactory<MethodInfo> {

	private static MethodsFactory factory;

	private MethodsFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/methods/method", MethodInfo.class);
		digester.addSetNext("rsp/methods/method", "addResponse");
		digester.addBeanPropertySetter("rsp/methods/method");
	}

	public static synchronized MethodsFactory getInstance() {
		if (factory == null) {
			factory = new MethodsFactory();
		}
		return factory;
	}
}
