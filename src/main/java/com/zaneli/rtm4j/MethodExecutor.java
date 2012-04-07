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

import com.zaneli.rtm4j.model.Result;
import com.zaneli.rtm4j.model.ResultFactory;
import com.zaneli.rtm4j.model.Model;
import com.zaneli.rtm4j.model.Rsp;
import com.zaneli.rtm4j.model.RspFactory;
import com.zaneli.rtm4j.model.Rsps;
import com.zaneli.rtm4j.model.RspsFactory;
import com.zaneli.rtm4j.model.auth.FrobFactory;
import com.zaneli.rtm4j.model.auth.FrobInfo;
import com.zaneli.rtm4j.model.auth.TokenFactory;
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
import com.zaneli.rtm4j.model.reflection.MethodInfo;
import com.zaneli.rtm4j.model.reflection.MethodsFactory;
import com.zaneli.rtm4j.model.settings.SettingInfo;
import com.zaneli.rtm4j.model.settings.SettingsFactory;
import com.zaneli.rtm4j.model.tasks.TaskInfo;
import com.zaneli.rtm4j.model.tasks.TasksFactory;
import com.zaneli.rtm4j.model.timelines.TimelineFactory;
import com.zaneli.rtm4j.model.timelines.TimelineInfo;
import com.zaneli.rtm4j.model.timezones.TimezoneInfo;
import com.zaneli.rtm4j.model.timezones.TimezonesFactory;
import com.zaneli.rtm4j.util.ParamsBuilder;

abstract class MethodExecutor {

	private static final String URL = "https://api.rememberthemilk.com/services/rest/";

	private final String apiKey;
	private final String sharedSecret;
	private final String categoryName;

	private MethodExecutor(String apiKey, String sharedSecret, String categoryName) {
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

	protected Result execute(
			ParamsBuilder paramsBuilder, ResultFactory factory) throws RtmException, IOException {
		InputStream in = null;
		try {
			in = sendRequest(paramsBuilder).getEntity().getContent();
			return factory.create(in);
		} catch (SAXException e) {
			throw new RtmException(e);
		} finally {
			if (in != null) in.close();
		}
	}

	protected <T extends RspFactory<M>, M extends Model> Rsp<M> execute(
			ParamsBuilder paramsBuilder, T factory) throws RtmException, IOException {
		InputStream in = null;
		try {
			in = sendRequest(paramsBuilder).getEntity().getContent();
			return factory.create(in);
		} catch (SAXException e) {
			throw new RtmException(e);
		} finally {
			if (in != null) in.close();
		}
	}

	protected <T extends RspsFactory<M>, M extends Model> Rsps<M> execute(
			ParamsBuilder paramsBuilder, T factory) throws RtmException, IOException {
		InputStream in = null;
		try {
			in = sendRequest(paramsBuilder).getEntity().getContent();
			return factory.create(in);
		} catch (SAXException e) {
			throw new RtmException(e);
		} finally {
			if (in != null) in.close();
		}
	}

	private HttpRequestBase createRequest(Map<String, String> params) throws RtmException {
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

	private HttpResponse sendRequest(ParamsBuilder paramsBuilder) throws RtmException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpRequestBase request = createRequest(paramsBuilder.build());
		HttpResponse response = httpClient.execute(request);
		return response;
	}

	public static class Auth extends MethodExecutor {
		Auth(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "auth");
		}
		public Rsp<TokenInfo> checkToken(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "checkToken", authToken);
			return execute(paramsBuilder, TokenFactory.getInstance());
		}
		public Rsp<FrobInfo> getFrob() throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(), getSharedSecret(), getCategoryName(), "getFrob");
			return execute(paramsBuilder, FrobFactory.getInstance());
		}
		public Rsp<TokenInfo> getToken(String frob) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getToken").setFrob(frob);
			return execute(paramsBuilder, TokenFactory.getInstance());
		}
	}

	public static class Contacts extends MethodExecutor {
		Contacts(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "contacts");
		}
		public Rsp<ContactInfo> add(
				String authToken, String timeline, String contact) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"add",
					authToken).setTimeline(timeline).setContact(contact);
			return execute(paramsBuilder, ContactFactory.getInstance());
		}
		public Result delete(
				String authToken, String timeline, String contactId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"delete",
					authToken).setTimeline(timeline).setContactId(contactId);
			return execute(paramsBuilder, ResultFactory.getInstance());
		}
		public Rsps<ContactInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			return execute(paramsBuilder, ContactsFactory.getInstance());
		}
	}

	public static class Groups extends MethodExecutor {
		Groups(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "groups");
		}
		public Rsp<GroupInfo> add(String authToken, String timeline, String group) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"add",
					authToken).setTimeline(timeline).setGroup(group);
			return execute(paramsBuilder, GroupFactory.getInstance());
		}
		public Result addContact(
				String authToken, String timeline, String groupId, String contactId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"addContact",
					authToken).setTimeline(timeline).setGroupId(groupId).setContactId(contactId);
			return execute(paramsBuilder, ResultFactory.getInstance());
		}
		public Result delete(
				String authToken, String timeline, String groupId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"delete",
					authToken).setTimeline(timeline).setGroupId(groupId);
			return execute(paramsBuilder, ResultFactory.getInstance());
		}
		public Rsps<GroupInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			return execute(paramsBuilder, GroupsFactory.getInstance());
		}
		public Result removeContact(
				String authToken, String timeline, String groupId, String contactId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"removeContact",
					authToken).setTimeline(timeline).setGroupId(groupId).setContactId(contactId);
			return execute(paramsBuilder, ResultFactory.getInstance());
		}
	}

	public static class Lists extends MethodExecutor {
		Lists(String apiKey, String sharedSecret) {
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
			return execute(paramsBuilder, ListFactory.getInstance());
		}
		public Rsp<ListInfo> archive(
				String authToken, String timeline, String listId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"archive",
					authToken).setTimeline(timeline).setListId(listId);
			return execute(paramsBuilder, ListFactory.getInstance());
		}
		public Rsp<ListInfo> delete(
				String authToken, String timeline, String listId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"delete",
					authToken).setTimeline(timeline).setListId(listId);
			return execute(paramsBuilder, ListFactory.getInstance());
		}
		public Rsps<ListInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			return execute(paramsBuilder, ListsFactory.getInstance());
		}
		public Result setDefaultList(
				String authToken, String timeline, String listId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"setDefaultList",
					authToken).setTimeline(timeline).setListId(listId);
			return execute(paramsBuilder, ResultFactory.getInstance());
		}
		public Rsp<ListInfo> setName(
				String authToken, String timeline, String listId, String name) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"setName",
					authToken).setTimeline(timeline).setListId(listId).setName(name);
			return execute(paramsBuilder, ListFactory.getInstance());
		}
		public Rsp<ListInfo> unarchive(
				String authToken, String timeline, String listId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"unarchive",
					authToken).setTimeline(timeline).setListId(listId);
			return execute(paramsBuilder, ListFactory.getInstance());
		}
	}

	public static class Locations extends MethodExecutor {
		Locations(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "locations");
		}
		public Rsps<LocationInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			return execute(paramsBuilder, LocationsFactory.getInstance());
		}
	}

	public static class Reflection extends MethodExecutor {
		Reflection(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "reflection");
		}
		public Rsps<MethodInfo> getMethods(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getMethods", authToken);
			return execute(paramsBuilder, MethodsFactory.getInstance());
		}
	}

	public static class Settings extends MethodExecutor {
		Settings(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "settings");
		}
		public Rsps<SettingInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			return execute(paramsBuilder, SettingsFactory.getInstance());
		}
	}

	public static class Tasks extends MethodExecutor {
		Tasks(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "tasks");
		}
		public Rsps<TaskInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			return execute(paramsBuilder, TasksFactory.getInstance());
		}
	}

	public static class Timelines extends MethodExecutor {
		Timelines(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "timelines");
		}
		public Rsp<TimelineInfo> create(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "create", authToken);
			return execute(paramsBuilder, TimelineFactory.getInstance());
		}
	}

	public static class Timezones extends MethodExecutor {
		Timezones(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "timezones");
		}
		public Rsps<TimezoneInfo> getList(String authToken) throws RtmException, IOException {
			ParamsBuilder paramsBuilder =
					new ParamsBuilder(getApiKey(), getSharedSecret(), getCategoryName(), "getList", authToken);
			return execute(paramsBuilder, TimezonesFactory.getInstance());
		}
	}

	public static class Transactions extends MethodExecutor {
		Transactions(String apiKey, String sharedSecret) {
			super(apiKey, sharedSecret, "transactions");
		}
		public Result undo(
				String authToken, String timeline, String transactionId) throws RtmException, IOException {
			ParamsBuilder paramsBuilder = new ParamsBuilder(
					getApiKey(),
					getSharedSecret(),
					getCategoryName(),
					"undo",
					authToken).setTimeline(timeline).setTransactionId(transactionId);
			return execute(paramsBuilder, ResultFactory.getInstance());
		}
	}
}
