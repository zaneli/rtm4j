package com.zaneli.rtm4j.model;

public class Rsp<T extends Model> extends Model {

	private String stat;
	private Err err;
	private T response;
	private Transaction transaction;

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
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
