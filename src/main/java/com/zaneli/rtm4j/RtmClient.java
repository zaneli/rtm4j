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

import com.zaneli.rtm4j.model.EmptyInfo;
import com.zaneli.rtm4j.model.EmptyRspFactory;
import com.zaneli.rtm4j.model.Model;
import com.zaneli.rtm4j.model.Rsp;
import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.Rsps;
import com.zaneli.rtm4j.model.RspsFactory;
import com.zaneli.rtm4j.model.auth.FrobFactory;
import com.zaneli.rtm4j.model.auth.TokenFactory;
import com.zaneli.rtm4j.model.auth.FrobInfo;
import com.zaneli.rtm4j.model.auth.TokenInfo;
import com.zaneli.rtm4j.model.contacts.ContactFactory;
import com.zaneli.rtm4j.model.contacts.ContactInfo;
import com.zaneli.rtm4j.model.contacts.ContactsFactory;
import com.zaneli.rtm4j.model.groups.GroupFactory;
import com.zaneli.rtm4j.model.groups.GroupInfo;
import com.zaneli.rtm4j.model.groups.GroupsFactory;
import com.zaneli.rtm4j.model.lists.ListFactory;
import com.zaneli.rtm4j.model.lists.ListInfo;
import com.zaneli.rtm4j.model.lists.ListsFactory;
import com.zaneli.rtm4j.model.locations.LocationInfo;
import com.zaneli.rtm4j.model.locations.LocationsFactory;
import com.zaneli.rtm4j.model.settings.SettingInfo;
import com.zaneli.rtm4j.model.settings.SettingsFactory;
import com.zaneli.rtm4j.model.tasks.TaskInfo;
import com.zaneli.rtm4j.model.tasks.TasksFactory;
import com.zaneli.rtm4j.model.timelines.TimelineInfo;
import com.zaneli.rtm4j.model.timelines.TimelineFactory;
import com.zaneli.rtm4j.model.timezones.TimezoneInfo;
import com.zaneli.rtm4j.model.timezones.TimezonesFactory;
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
	private final Timelines timelines;
	private final Timezones timezones;
	private final Transactions transactions;

	public RtmClient(String apiKey, String sharedSecret) {
		this.auth = new Auth(apiKey, sharedSecret);
		this.contacts = new Contacts(apiKey, sharedSecret);
		this.groups = new Groups(apiKey, sharedSecret);
		this.lists = new Lists(apiKey, sharedSecret);
		this.locations = new Locations(apiKey, sharedSecret);
		this.settings = new Settings(apiKey, sharedSecret);
		this.tasks = new Tasks(apiKey, sharedSecret);
		this.timelines = new Timelines(apiKey, sharedSecret);
		this.timezones = new Timezones(apiKey, sharedSecret);
		this.transactions = new Transactions(apiKey, sharedSecret);
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
	public Timelines timelines() {
		return timelines;
	}
	public Timezones timezones() {
		return timezones;
	}
	public Transactions transactions() {
		return transactions;
	}

	private static <T extends RspFactory<M>, M extends Model> Rsp<M> execute(
			HttpRequestBase request, T factory) throws RtmException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);
		
		InputStream in = null;
		try {
			in = response.getEntity().getContent();
			Rsp<M> rsp = factory.create(in);
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

	private static <T extends RspsFactory<M>, M extends Model> Rsps<M> execute(
			HttpRequestBase request, T factory) throws RtmException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);
		
		InputStream in = null;
		try {
			in = response.getEntity().getContent();
			Rsps<M> rsps = factory.create(in);
			String stat = rsps.getStat();
			if ("ok".equals(stat)) {
				return rsps;
			} else if ("fail".equals(stat)) {
				throw new RtmException(rsps.getErr());
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
		public Rsp<TokenInfo> checkToken(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "checkToken", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, TokenFactory.getInstance());
		}
		public Rsp<FrobInfo> getFrob() throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getFrob");
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, FrobFactory.getInstance());
		}
		public Rsp<TokenInfo> getToken(String frob) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getToken").setFrob(frob);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, TokenFactory.getInstance());
		}
	}

	public static class Contacts extends Category {
		private Contacts(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "contacts");
		}
		public Rsp<ContactInfo> add(String authToken, String timeline, String contact) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"add",
					authToken).setTimeline(timeline).setContact(contact);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, ContactFactory.getInstance());
		}
		public Rsp<EmptyInfo> delete(String authToken, String timeline, String contactId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"delete",
					authToken).setTimeline(timeline).setContactId(contactId);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, EmptyRspFactory.getInstance());
		}
		public Rsps<ContactInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, ContactsFactory.getInstance());
		}
	}

	public static class Groups extends Category {
		private Groups(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "groups");
		}
		public Rsp<GroupInfo> add(String authToken, String timeline, String group) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"add",
					authToken).setTimeline(timeline).setGroup(group);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, GroupFactory.getInstance());
		}
		public Rsp<EmptyInfo> addContact(
				String authToken, String timeline, String groupId, String contactId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"addContact",
					authToken).setTimeline(timeline).setGroupId(groupId).setContactId(contactId);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, EmptyRspFactory.getInstance());
		}
		public Rsp<EmptyInfo> delete(String authToken, String timeline, String groupId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"delete",
					authToken).setTimeline(timeline).setGroupId(groupId);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, EmptyRspFactory.getInstance());
		}
		public Rsps<GroupInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, GroupsFactory.getInstance());
		}
		public Rsp<EmptyInfo> removeContact(
				String authToken, String timeline, String groupId, String contactId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"removeContact",
					authToken).setTimeline(timeline).setGroupId(groupId).setContactId(contactId);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, EmptyRspFactory.getInstance());
		}
	}

	public static class Lists extends Category {
		private Lists(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "lists");
		}
		public Rsp<ListInfo> add(String authToken, String timeline, String name) throws RtmException, IOException {
			return add(authToken, timeline, name, null);
		}
		public Rsp<ListInfo> add(
				String authToken, String timeline, String name, String filter) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"add",
					authToken).setTimeline(timeline).setName(name).setFilter(filter);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, ListFactory.getInstance());
		}
		public Rsp<ListInfo> archive(
				String authToken, String timeline, String listId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"archive",
					authToken).setTimeline(timeline).setListId(listId);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, ListFactory.getInstance());
		}
		public Rsps<ListInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, ListsFactory.getInstance());
		}
	}

	public static class Locations extends Category {
		private Locations(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "locations");
		}
		public Rsps<LocationInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, LocationsFactory.getInstance());
		}
	}

	public static class Settings extends Category {
		private Settings(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "settings");
		}
		public Rsps<SettingInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, SettingsFactory.getInstance());
		}
	}

	public static class Tasks extends Category {
		private Tasks(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "tasks");
		}
		public Rsps<TaskInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, TasksFactory.getInstance());
		}
	}

	public static class Timelines extends Category {
		private Timelines(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "timelines");
		}
		public Rsp<TimelineInfo> create(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "create", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, TimelineFactory.getInstance());
		}
	}

	public static class Timezones extends Category {
		private Timezones(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "timezones");
		}
		public Rsps<TimezoneInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, TimezonesFactory.getInstance());
		}
	}

	public static class Transactions extends Category {
		private Transactions(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "transactions");
		}
		public Rsp<EmptyInfo> undo(
				String authToken, String timeline, String transactionId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"undo",
					authToken).setTimeline(timeline).setTransactionId(transactionId);
			HttpRequestBase request = createRequest(paramsBuilder.build());
			return execute(request, EmptyRspFactory.getInstance());
		}
	}
}
