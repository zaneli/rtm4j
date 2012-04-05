package com.zaneli.rtm4j.model.locations;

import org.apache.commons.digester3.Digester;

import com.zaneli.rtm4j.model.RspsFactory;

public class LocationsFactory extends RspsFactory<LocationInfo> {

	private static LocationsFactory factory;

	private LocationsFactory() {
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/locations/location", LocationInfo.class);
		digester.addSetNext("rsp/locations/location", "addResponse");
		digester.addSetProperties("rsp/locations/location", "id", "id");
		digester.addSetProperties("rsp/locations/location", "name", "name");
		digester.addSetProperties("rsp/locations/location", "longitude", "longitude");
		digester.addSetProperties("rsp/locations/location", "latitude", "latitude");
		digester.addSetProperties("rsp/locations/location", "zoom", "zoom");
		digester.addSetProperties("rsp/locations/location", "address", "address");
		digester.addSetProperties("rsp/locations/location", "viewable", "viewable");
	}

	public static synchronized LocationsFactory getInstance() {
		if (factory == null) {
			factory = new LocationsFactory();
		}
		return factory;
	}
}
