package com.zaneli.rtm4j.util;

import java.util.Map;
import java.util.Map.Entry;

import com.zaneli.rtm4j.RtmException;

public class AuthUtil {

	private static final String URL = "http://www.rememberthemilk.com/services/auth/";

	public static String createAuthUrl(
			String apiKey, String sharedSecret, Perms perms, String frob) throws RtmException {
		return createQueryUrl(
				URL,
				new ParamsBuilder(apiKey, sharedSecret).setPerms(perms.getValue()).setFrob(frob).build());
	}

	private static String createQueryUrl(String url, Map<String, String> queryParams) {
		StringBuilder sb = new StringBuilder(url);
		sb.append('?');
		for (Entry<String, String> entry : queryParams.entrySet()) {
			sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
		}
		return sb.substring(0, sb.length() - 1).toString();
	}

	public enum Perms {
		READ("read"), READ_WRITE("write"), ALL("delete");
		private final String value;
		private Perms(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	private AuthUtil() {};
}
