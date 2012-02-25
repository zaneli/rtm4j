package com.zaneli.rtm4j.model.settings;

import com.zaneli.rtm4j.model.Rsp;

public class SettingsGetListRsp extends Rsp {

	private String timezone;

	private String dateformat;

	private String timeformat;

	private String defaultlist;

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getDateformat() {
		return dateformat;
	}

	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}

	public String getTimeformat() {
		return timeformat;
	}

	public void setTimeformat(String timeformat) {
		this.timeformat = timeformat;
	}

	public String getDefaultlist() {
		return defaultlist;
	}

	public void setDefaultlist(String defaultlist) {
		this.defaultlist = defaultlist;
	}
}
