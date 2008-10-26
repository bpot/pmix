package org.pmix.ui;

import java.util.ArrayList;
import java.util.List;

import org.a0z.mpd.Music;
import org.a0z.mpd.MPDPlaylist;
import org.a0z.mpd.MPDServerException;
import org.pmix.settings.Contexte;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlaylistActivity extends ListActivity {

	private List<String> items = new ArrayList<String>();
	private List<Music> musics;

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
		setContentView(R.layout.artists);
    
    try {
      MPDPlaylist playlist = Contexte.getInstance().getMpd().getPlaylist();
      playlist.refresh();
      musics = playlist.getMusics();
      for(Music m : musics) {
        items.add(m.getArtist() + " - " + m.getTitle());
      }

      ArrayAdapter<String> notes = new ArrayAdapter<String>(this, R.layout.artist_row, items);
      setListAdapter(notes);
    } catch (MPDServerException e) {
    } 
  }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    Music m = musics.get(position);

    try {
      Contexte.getInstance().getMpd().skipTo(m.getSongId());
    } catch (MPDServerException e) {
    }
		
	}

}
