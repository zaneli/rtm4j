package com.zaneli.rtm4j.model.settings;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;

public class SettingsGetListRspFactory extends RspFactory<SettingsGetListRsp> {

	private static SettingsGetListRspFactory factory;

	private SettingsGetListRspFactory() {
		super(SettingsGetListRsp.class);
		Digester digester = getDigester();
		digester.addBeanPropertySetter("rsp/settings/timezone");
		digester.addBeanPropertySetter("rsp/settings/dateformat");
		digester.addBeanPropertySetter("rsp/settings/timeformat");
		digester.addBeanPropertySetter("rsp/settings/defaultlist");
	}

	@Override
	public SettingsGetListRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized SettingsGetListRspFactory getInstance() {
		if (factory == null) {
			factory = new SettingsGetListRspFactory();
		}
		return factory;
	}
}
