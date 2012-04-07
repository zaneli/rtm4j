package com.zaneli.rtm4j.model;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;

import com.zaneli.rtm4j.RtmException;

public abstract class RspsFactory<T extends Model> extends ResultFactory {

	protected RspsFactory() {
		super(Rsps.class);
	}

	@Override
	public Rsps<T> create(InputStream in) throws IOException, SAXException, RtmException {
		Rsps<T> rsps = getDigester().parse(in);
		checkStatus(rsps);
		return rsps;
	}
}
