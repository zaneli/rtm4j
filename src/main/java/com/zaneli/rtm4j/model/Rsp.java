package com.zaneli.rtm4j.model;

public class Rsp extends Model {

	private String stat;

	private Err err;

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getStat() {
		return stat;
	}

	public void setErr(Err err) {
		this.err = err;
	}

	public Err getErr() {
		return err;
	}

	public static class Err extends Model {

		private String code;

		private String msg;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
