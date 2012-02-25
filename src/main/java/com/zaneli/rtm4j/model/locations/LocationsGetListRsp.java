package com.zaneli.rtm4j.model.locations;

import java.util.ArrayList;
import java.util.List;

import com.zaneli.rtm4j.model.Model;
import com.zaneli.rtm4j.model.Rsp;

public class LocationsGetListRsp extends Rsp {

	private List<Location> locations;

	public LocationsGetListRsp() {
		locations = new ArrayList<Location>();
	}

	public Location[] getLocations() {
		return locations.toArray(new Location[locations.size()]);
	}

	public void addLocation(Location location) {
		locations.add(location);
	}

	public static class Location extends Model {

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
}
