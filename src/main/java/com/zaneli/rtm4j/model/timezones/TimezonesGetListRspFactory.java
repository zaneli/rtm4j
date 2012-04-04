package com.zaneli.rtm4j.model.timezones;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.timezones.TimezonesGetListRsp.Timezone;

public class TimezonesGetListRspFactory extends RspFactory<TimezonesGetListRsp> {

	private static TimezonesGetListRspFactory factory;

	private TimezonesGetListRspFactory() {
		super(TimezonesGetListRsp.class);
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/timezones/timezone", Timezone.class);
		digester.addSetNext("rsp/timezones/timezone", "addTimezone");
		digester.addSetProperties("rsp/timezones/timezone", "id", "id");
		digester.addSetProperties("rsp/timezones/timezone", "name", "name");
		digester.addSetProperties("rsp/timezones/timezone", "dst", "dst");
		digester.addSetProperties("rsp/timezones/timezone", "offset", "offset");
		digester.addSetProperties("rsp/timezones/timezone", "current_offset", "currentOffset");
	}

	@Override
	public TimezonesGetListRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized TimezonesGetListRspFactory getInstance() {
		if (factory == null) {
			factory = new TimezonesGetListRspFactory();
		}
		return factory;
	}
}
