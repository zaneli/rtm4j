package com.zaneli.rtm4j.model.timezones;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspsFactory;

public class TimezonesFactory extends RspsFactory<TimezoneInfo> {

	private static TimezonesFactory factory;

	private TimezonesFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/timezones/timezone", TimezoneInfo.class);
		digester.addSetNext("rsp/timezones/timezone", "addResponse");
		digester.addSetProperties("rsp/timezones/timezone", "id", "id");
		digester.addSetProperties("rsp/timezones/timezone", "name", "name");
		digester.addSetProperties("rsp/timezones/timezone", "dst", "dst");
		digester.addSetProperties("rsp/timezones/timezone", "offset", "offset");
		digester.addSetProperties("rsp/timezones/timezone", "current_offset", "currentOffset");
	}

	public static synchronized TimezonesFactory getInstance() {
		if (factory == null) {
			factory = new TimezonesFactory();
		}
		return factory;
	}
}
