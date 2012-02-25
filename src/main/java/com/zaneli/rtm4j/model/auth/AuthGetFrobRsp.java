package com.zaneli.rtm4j.model.auth;

import com.zaneli.rtm4j.model.Rsp;

public class AuthGetFrobRsp extends Rsp {

	private String frob;

	public String getFrob() {
		return frob;
	}

	public void setFrob(String frob) {
		this.frob = frob;
	}
}
