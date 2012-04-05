package com.zaneli.rtm4j.model.timezones;

import com.zaneli.rtm4j.model.Model;

public class TimezoneInfo extends Model {

	private String id;
	private String name;
	private String dst;
	private String offset;
	private String currentOffset;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getCurrentOffset() {
		return currentOffset;
	}
	public void setCurrentOffset(String currentOffset) {
		this.currentOffset = currentOffset;
	}
}
