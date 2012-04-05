package com.zaneli.rtm4j.model.timelines;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspFactory;

public class TimelineFactory extends RspFactory<TimelineInfo> {

	private static TimelineFactory factory;

	private TimelineFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/timeline", TimelineInfo.class);
		digester.addSetNext("rsp/timeline", "setResponse");
		digester.addBeanPropertySetter("rsp/timeline");
	}

	public static synchronized TimelineFactory getInstance() {
		if (factory == null) {
			factory = new TimelineFactory();
		}
		return factory;
	}
}
