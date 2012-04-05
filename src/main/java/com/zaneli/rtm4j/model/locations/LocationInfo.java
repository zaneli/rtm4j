package com.zaneli.rtm4j.model.locations;

import com.zaneli.rtm4j.model.Model;

public class LocationInfo extends Model {

	private String id;
	private String name;
	private String longitude;
	private String latitude;
	private String zoom;
	private String address;
	private String viewable;

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
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getZoom() {
		return zoom;
	}
	public void setZoom(String zoom) {
		this.zoom = zoom;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getViewable() {
		return viewable;
	}
	public void setViewable(String viewable) {
		this.viewable = viewable;
	}
}
