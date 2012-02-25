package com.zaneli.rtm4j.model.locations;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.locations.LocationsGetListRsp.Location;

public class LocationsGetListRspFactory extends RspFactory<LocationsGetListRsp> {

	private static LocationsGetListRspFactory factory;

	private LocationsGetListRspFactory() {
		super(LocationsGetListRsp.class);
		Digester digester = getDigester();
		digester.addObjectCreate("rsp/locations/location", Location.class);
		digester.addSetNext("rsp/locations/location", "addLocation");
		digester.addSetProperties("rsp/locations/location", "id", "id");
		digester.addSetProperties("rsp/locations/location", "name", "name");
		digester.addSetProperties("rsp/locations/location", "longitude", "longitude");
		digester.addSetProperties("rsp/locations/location", "latitude", "latitude");
		digester.addSetProperties("rsp/locations/location", "zoom", "zoom");
		digester.addSetProperties("rsp/locations/location", "address", "address");
		digester.addSetProperties("rsp/locations/location", "viewable", "viewable");
	}

	@Override
	public LocationsGetListRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized LocationsGetListRspFactory getInstance() {
		if (factory == null) {
			factory = new LocationsGetListRspFactory();
		}
		return factory;
	}
}
