package org.pmix.settings;

import org.a0z.mpd.MPD;
import org.a0z.mpd.MPDServerException;

public class Contexte {

	private MPD mpd = new MPD();

	private static Contexte instance = new Contexte();

	
	
	private Contexte() {

	}

	public static Contexte getInstance() {
		return instance;
	}

	public void disconnect() {
		if (mpd.isConnected()) {

			try {
				mpd.disconnect();
			} catch (MPDServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public MPD getMpd() throws MPDServerException {

		if (!mpd.isConnected()) {

			mpd.connect(Settings.getInstance().getServerAddress());
		}
		return mpd;
	}

}
