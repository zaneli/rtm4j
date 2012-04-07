package com.zaneli.rtm4j.model;

public class Rsp<T extends Model> extends Result {

	private T response;
	private Transaction transaction;

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
