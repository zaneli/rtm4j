package com.zaneli.rtm4j.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Rsps<T extends Model> extends Model {

	private String stat;
	private Err err;
	private List<T> responses;

	public Rsps() {
		responses = new ArrayList<T>();
	}

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
	@SuppressWarnings("unchecked")
	public T[] getResponses() {
		if (responses.isEmpty()) {
			// TODO
			return null;
		}
		return responses.toArray((T[]) Array.newInstance(responses.get(0).getClass(), responses.size()));
	}
	public void addResponse(T response) {
		responses.add(response);
	}
}
