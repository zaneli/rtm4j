package com.zaneli.rtm4j.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.zaneli.rtm4j.RtmException;

public class ParamsBuilder {

	private static final String KEY_METHOD = "method";
	private static final String KEY_API_KEY = "api_key";
	private static final String KEY_PERMS = "perms";
	private static final String KEY_FROB = "frob";
	private static final String KEY_AUTH_TOKEN = "auth_token";
	private static final String KEY_API_SIG = "api_sig";
	private static final String KEY_TIMELINE = "timeline";
	private static final String KEY_CONTACT = "contact";
	private static final String KEY_CONTACT_ID = "contact_id";
	private static final String KEY_GROUP = "group";
	private static final String KEY_GROUP_ID = "group_id";
	private static final String KEY_NAME = "name";
	private static final String KEY_FILTER = "filter";
	private static final String KEY_LIST_ID = "list_id";
	private static final String KEY_TRANSACTION_ID = "transaction_id";

	private final Map<String, String> params;

	private final String sharedSecret;

	public ParamsBuilder(String apiKey, String sharedSecret) {
		this.sharedSecret = sharedSecret;
		params = new HashMap<String, String>();
		params.put(KEY_API_KEY, apiKey);
	}
	public ParamsBuilder(String apiKey, String sharedSecret, String categoryName, String methodName) {
		this(apiKey, sharedSecret);
		params.put(KEY_METHOD, "rtm." + categoryName + "." + methodName);
	}
	public ParamsBuilder(
			String apiKey, String sharedSecret, String categoryName, String methodName, String authToken) {
		this(apiKey, sharedSecret, categoryName, methodName);
		params.put(KEY_AUTH_TOKEN, authToken);
	}

	public ParamsBuilder setPerms(String perms) {
		if (perms != null) {
			params.put(KEY_PERMS, perms);
		}
		return this;
	}
	public ParamsBuilder setFrob(String frob) {
		if (frob != null) {
			params.put(KEY_FROB, frob);
		}
		return this;
	}
	public ParamsBuilder setTimeline(String timeline) {
		if (timeline != null) {
			params.put(KEY_TIMELINE, timeline);
		}
		return this;
	}
	public ParamsBuilder setContact(String contact) {
		if (contact != null) {
			params.put(KEY_CONTACT, contact);
		}
		return this;
	}
	public ParamsBuilder setContactId(String contactId) {
		if (contactId != null) {
			params.put(KEY_CONTACT_ID, contactId);
		}
		return this;
	}
	public ParamsBuilder setGroup(String group) {
		if (group != null) {
			params.put(KEY_GROUP, group);
		}
		return this;
	}
	public ParamsBuilder setGroupId(String groupId) {
		if (groupId != null) {
			params.put(KEY_GROUP_ID, groupId);
		}
		return this;
	}
	public ParamsBuilder setName(String name) {
		if (name != null) {
			params.put(KEY_NAME, name);
		}
		return this;
	}
	public ParamsBuilder setFilter(String filter) {
		if (filter != null) {
			params.put(KEY_FILTER, filter);
		}
		return this;
	}
	public ParamsBuilder setListId(String listId) {
		if (listId != null) {
			params.put(KEY_LIST_ID, listId);
		}
		return this;
	}
	public ParamsBuilder setTransactionId(String transactionId) {
		if (transactionId != null) {
			params.put(KEY_TRANSACTION_ID, transactionId);
		}
		return this;
	}

	public Map<String, String> build() throws RtmException {
		params.put(KEY_API_SIG, createApiSig(sharedSecret, params));
		return new HashMap<String, String>(params);
	}

	private static String createApiSig(String sharedSecret, Map<String, String> params) throws RtmException {
		Map<String, String> sortedParams = new TreeMap<String, String>(params);
		StringBuilder sb = new StringBuilder(sharedSecret);
		for (Entry<String, String> entry : sortedParams.entrySet()) {
			sb.append(entry.getKey());
			sb.append(entry.getValue());
		}
		try {
			return digest(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			throw new RtmException(e);
		}
	}

	private static String digest(String value) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(value.getBytes());
		return toHexString(md.digest());
	}

	private static String toHexString(byte[] byteArray) {
		StringBuilder sb = new StringBuilder();
		for (byte b : byteArray) {
			String hexStr = Integer.toHexString(b & 0xff);
			if (hexStr.length() == 1) {
				sb.append('0');
			}
			sb.append(hexStr);
		}
		return sb.toString();
	}
}
