package org.pmix.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.a0z.mpd.MPD;
import org.a0z.mpd.MPDPlaylist;
import org.a0z.mpd.MPDServerException;
import org.a0z.mpd.Music;
import org.pmix.settings.Contexte;

import android.util.Log;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SongsActivity extends ListActivity {

	private List<Music> musics = null;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.artists);
		List<String> items = new ArrayList<String>();

		try {
			String album = (String) this.getIntent().getStringExtra("album");
			this.setTitle(album);
			musics = new ArrayList<Music>(Contexte.getInstance().getMpd().find(MPD.MPD_FIND_ALBUM, album));

      items.add("Add All Songs");
			for (Music music : musics) {
				items.add(music.getTitle());
			}

			// items.addAll(Contexte.getInstance().getMpd()..listAlbums(artist));

			ArrayAdapter<String> notes = new ArrayAdapter<String>(this, R.layout.artist_row, items);
			setListAdapter(notes);
		} catch (MPDServerException e) {
			e.printStackTrace();
			this.setTitle(e.getMessage());
		}

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {


		Music music = musics.get(position - 1);
		try {
      if(position == 0) {
        Contexte.getInstance().getMpd().getPlaylist().add(musics);
        return;
      }

			int songId = -1;
			// try to find it in the current playlist first

			Collection<Music> founds = Contexte.getInstance().getMpd().getPlaylist().find("filename", music.getFullpath()); 

			// not found
			if (founds.isEmpty()) {
				songId = Contexte.getInstance().getMpd().getPlaylist().addid(music);
			} else {
				// found
				songId = founds.toArray(new Music[founds.size()])[0].getSongId();
      }
      Log.v("PMIX","Added song: " + songId);
			if (songId > -1) {
				Contexte.getInstance().getMpd().skipTo(songId);
			}
			
		} catch (MPDServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
