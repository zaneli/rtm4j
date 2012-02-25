package com.zaneli.rtm4j.model.auth;

import com.zaneli.rtm4j.model.Rsp;

public class AuthGetTokenRsp extends Rsp {

	private String token;

	private String perms;

	private String id;

	private String username;

	private String fullname;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
}
