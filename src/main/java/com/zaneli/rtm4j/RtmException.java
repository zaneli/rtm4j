package com.zaneli.rtm4j;

import com.zaneli.rtm4j.model.Rsp.Err;

@SuppressWarnings("serial")
public class RtmException extends Exception {

	public RtmException(Err err) {
		super("code=" + err.getCode() + ", msg=" + err.getMsg());
	}

	public RtmException(Throwable cause) {
		super(cause);
	}
}
