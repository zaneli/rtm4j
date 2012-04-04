package com.zaneli.rtm4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.Rsp;
import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.auth.AuthGetFrobRsp;
import com.zaneli.rtm4j.model.auth.AuthGetFrobRspFactory;
import com.zaneli.rtm4j.model.auth.AuthGetTokenRsp;
import com.zaneli.rtm4j.model.auth.AuthGetTokenRspFactory;
import com.zaneli.rtm4j.model.contacts.ContactsGetListRsp;
import com.zaneli.rtm4j.model.contacts.ContactsGetListRspFactory;
import com.zaneli.rtm4j.model.groups.GroupsGetListRsp;
import com.zaneli.rtm4j.model.groups.GroupsGetListRspFactory;
import com.zaneli.rtm4j.model.lists.ListsGetListRsp;
import com.zaneli.rtm4j.model.lists.ListsGetListRspFactory;
import com.zaneli.rtm4j.model.locations.LocationsGetListRsp;
import com.zaneli.rtm4j.model.locations.LocationsGetListRspFactory;
import com.zaneli.rtm4j.model.settings.SettingsGetListRsp;
import com.zaneli.rtm4j.model.settings.SettingsGetListRspFactory;
import com.zaneli.rtm4j.model.tasks.TasksGetListRsp;
import com.zaneli.rtm4j.model.tasks.TasksGetListRspFactory;
import com.zaneli.rtm4j.model.timezones.TimezonesGetListRsp;
import com.zaneli.rtm4j.model.timezones.TimezonesGetListRspFactory;
import com.zaneli.rtm4j.util.ParamsBuilder;

public class RtmClient {

	private static final String URL = "https://api.rememberthemilk.com/services/rest/";

	private final Auth auth;
	private final Contacts contacts;
	private final Groups groups;
	private final Lists lists;
	private final Locations locations;
	private final Settings settings;
	private final Tasks tasks;
	private final Timezones timezones;

	public RtmClient(String apiKey, String sharedSecret) {
		this.auth = new Auth(apiKey, sharedSecret);
		this.contacts = new Contacts(apiKey, sharedSecret);
		this.groups = new Groups(apiKey, sharedSecret);
		this.lists = new Lists(apiKey, sharedSecret);
		this.locations = new Locations(apiKey, sharedSecret);
		this.settings = new Settings(apiKey, sharedSecret);
		this.tasks = new Tasks(apiKey, sharedSecret);
		this.timezones = new Timezones(apiKey, sharedSecret);
	}

	public Auth auth() {
		return auth;
	}
	public Contacts contacts() {
		return contacts;
	}
	public Groups groups() {
		return groups;
	}
	public Lists lists() {
		return lists;
	}
	public Locations locations() {
		return locations;
	}
	public Settings settings() {
		return settings;
	}
	public Tasks tasks() {
		return tasks;
	}
	public Timezones timezones() {
		return timezones;
	}

	private static <T extends RspFactory<R>, R extends Rsp> R execute(
			HttpRequestBase request, T factory) throws RtmException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);
		
		InputStream in = null;
		try {
			in = response.getEntity().getContent();
			R rsp = factory.create(in);
			String stat = rsp.getStat();
			if ("ok".equals(stat)) {
				return rsp;
			} else if ("fail".equals(stat)) {
				throw new RtmException(rsp.getErr());
			} else {
				throw new IllegalStateException("stat=" + stat);
			}
		} catch (SAXException e) {
			throw new RtmException(e);
		} finally {
			if (in != null) in.close();
		}
	}

	private static HttpRequestBase createRequest(Map<String, String> params) throws RtmException {
		HttpPost request = new HttpPost(URL);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : params.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		try {
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
			return request;
		} catch (UnsupportedEncodingException e) {
			throw new RtmException(e);
		}
	}

	private static abstract class Category {
		private final String apiKey;
		private final String sharedSecret;
		private final String categoryName;
		private Category(String apiKey, String sharedSecret, String categoryName) {
			this.apiKey = apiKey;
			this.sharedSecret = sharedSecret;
			this.categoryName = categoryName;
		}
		protected String getApiKey() {
			return apiKey;
		}
		protected String getSharedSecret() {
			return sharedSecret;
		}
		protected String getCategoryName() {
			return categoryName;
		}
	}

	public static class Auth extends Category {
		private Auth(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "auth");
		}
		public AuthGetTokenRsp checkToken(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "checkToken").setAuthToken(authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, AuthGetTokenRspFactory.getInstance());
		}
		public AuthGetFrobRsp getFrob() throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getFrob");
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, AuthGetFrobRspFactory.getInstance());
		}
		public AuthGetTokenRsp getToken(String frob) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getToken").setFrob(frob);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, AuthGetTokenRspFactory.getInstance());
		}
	}

	public static class Contacts extends Category {
		private Contacts(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "contacts");
		}
		public ContactsGetListRsp getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList").setAuthToken(authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, ContactsGetListRspFactory.getInstance());
		}
	}

	public static class Groups extends Category {
		private Groups(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "groups");
		}
		public GroupsGetListRsp getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList").setAuthToken(authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, GroupsGetListRspFactory.getInstance());
		}
	}

	public static class Lists extends Category {
		private Lists(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "lists");
		}
		public ListsGetListRsp getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList").setAuthToken(authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, ListsGetListRspFactory.getInstance());
		}
	}

	public static class Locations extends Category {
		private Locations(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "locations");
		}
		public LocationsGetListRsp getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList").setAuthToken(authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, LocationsGetListRspFactory.getInstance());
		}
	}

	public static class Settings extends Category {
		private Settings(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "settings");
		}
		public SettingsGetListRsp getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList").setAuthToken(authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, SettingsGetListRspFactory.getInstance());
		}
	}

	public static class Tasks extends Category {
		private Tasks(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "tasks");
		}
		public TasksGetListRsp getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList").setAuthToken(authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, TasksGetListRspFactory.getInstance());
		}
	}

	public static class Timezones extends Category {
		private Timezones(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "timezones");
		}
		public TimezonesGetListRsp getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList").setAuthToken(authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, TimezonesGetListRspFactory.getInstance());
		}
	}
}
