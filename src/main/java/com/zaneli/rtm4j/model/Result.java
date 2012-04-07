package com.zaneli.rtm4j.model;

public class Result extends Model {

	private String stat;
	private Err err;

	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public Err getErr() {
		return err;
	}
	public void setErr(Err err) {
		this.err = err;
	}
}
