package org.pmix.settings;

import android.content.SharedPreferences;

public final class Settings {

	private String serverAddress = "192.168.1.150";
	

	private static Settings instance = new Settings();

	private Settings() {

	}
	
	public void load(SharedPreferences s)
	{
		this.setServerAddress(s.getString("serverAddress", "127.0.0.1"));
	}

	public static Settings getInstance() {
		return instance;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

}
