package com.zaneli.rtm4j.model.settings;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspsFactory;

public class SettingsFactory extends RspsFactory<SettingInfo> {

	private static SettingsFactory factory;

	private SettingsFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/settings", SettingInfo.class);
		digester.addSetNext("rsp/settings", "addResponse");
		digester.addBeanPropertySetter("rsp/settings/timezone");
		digester.addBeanPropertySetter("rsp/settings/dateformat");
		digester.addBeanPropertySetter("rsp/settings/timeformat");
		digester.addBeanPropertySetter("rsp/settings/defaultlist");
	}

	public static synchronized SettingsFactory getInstance() {
		if (factory == null) {
			factory = new SettingsFactory();
		}
		return factory;
	}
}
