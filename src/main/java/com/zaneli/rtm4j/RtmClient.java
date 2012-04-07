package com.zaneli.rtm4j;

import com.zaneli.rtm4j.MethodExecutor.Auth;
import com.zaneli.rtm4j.MethodExecutor.Contacts;
import com.zaneli.rtm4j.MethodExecutor.Groups;
import com.zaneli.rtm4j.MethodExecutor.Lists;
import com.zaneli.rtm4j.MethodExecutor.Locations;
import com.zaneli.rtm4j.MethodExecutor.Reflection;
import com.zaneli.rtm4j.MethodExecutor.Settings;
import com.zaneli.rtm4j.MethodExecutor.Tasks;
import com.zaneli.rtm4j.MethodExecutor.Timelines;
import com.zaneli.rtm4j.MethodExecutor.Timezones;
import com.zaneli.rtm4j.MethodExecutor.Transactions;

public class RtmClient {

	private final String apiKey;
	private final String sharedSecret;

	private Auth auth;
	private Contacts contacts;
	private Groups groups;
	private Lists lists;
	private Locations locations;
	private Reflection reflection;
	private Settings settings;
	private Tasks tasks;
	private Timelines timelines;
	private Timezones timezones;
	private Transactions transactions;

	public RtmClient(String apiKey, String sharedSecret) {
		this.apiKey = apiKey;
		this.sharedSecret = sharedSecret;
	}

	public synchronized Auth auth() throws RtmException {
		if (auth == null) {
			auth = new Auth(apiKey, sharedSecret);
		}
		return auth;
	}
	public synchronized Contacts contacts() {
		if (contacts == null) {
			contacts = new Contacts(apiKey, sharedSecret);
		}
		return contacts;
	}
	public synchronized Groups groups() {
		if (groups == null) {
			groups = new Groups(apiKey, sharedSecret);
		}
		return groups;
	}
	public synchronized Lists lists() {
		if (lists == null) {
			lists = new Lists(apiKey, sharedSecret);
		}
		return lists;
	}
	public synchronized Locations locations() {
		if (locations == null) {
			locations = new Locations(apiKey, sharedSecret);
		}
		return locations;
	}
	public synchronized Reflection reflection() {
		if (reflection == null) {
			reflection = new Reflection(apiKey, sharedSecret);
		}
		return reflection;
	}
	public synchronized Settings settings() {
		if (settings == null) {
			settings = new Settings(apiKey, sharedSecret);
		}
		return settings;
	}
	public synchronized Tasks tasks() {
		if (tasks == null) {
			tasks = new Tasks(apiKey, sharedSecret);
		}
		return tasks;
	}
	public synchronized Timelines timelines() {
		if (timelines == null) {
			timelines = new Timelines(apiKey, sharedSecret);
		}
		return timelines;
	}
	public synchronized Timezones timezones() {
		if (timezones == null) {
			timezones = new Timezones(apiKey, sharedSecret);
		}
		return timezones;
	}
	public synchronized Transactions transactions() {
		if (transactions == null) {
			transactions = new Transactions(apiKey, sharedSecret);
		}
		return transactions;
	}
}
