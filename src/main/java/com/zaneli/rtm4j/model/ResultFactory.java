package com.zaneli.rtm4j.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.RtmException;

public class ResultFactory {

	private static ResultFactory factory;

	private final Digester digester;

	protected <T extends Result> ResultFactory(Class<T> resultClass) {
		digester = new Digester();
		
		digester.addObjectCreate("rsp", resultClass);
		digester.addSetProperties("rsp", "stat", "stat");
		
		digester.addObjectCreate("rsp/err", Err.class);
		digester.addSetNext("rsp/err", "setErr");
		digester.addSetProperties("rsp/err", "code", "code");
		digester.addSetProperties("rsp/err", "msg", "msg");
	}

	public Result create(InputStream in) throws IOException, SAXException, RtmException {
		Result result = getDigester().parse(in);
		checkStatus(result);
		return result;
	}

	protected Digester getDigester() {
		return digester;
	}

	protected void checkStatus(Result result) throws RtmException {
		String stat = result.getStat();
		if ("ok".equals(stat)) {
			return;
		} else if ("fail".equals(stat)) {
			throw new RtmException(result.getErr());
		} else {
			throw new IllegalStateException("stat=" + stat);
		}
	}

	public static synchronized ResultFactory getInstance() {
		if (factory == null) {
			factory = new ResultFactory(Result.class);
		}
		return factory;
	}
}
