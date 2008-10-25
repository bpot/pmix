package org.pmix.ui;

import java.util.ArrayList;
import java.util.List;

import org.a0z.mpd.MPDServerException;
import org.pmix.settings.Contexte;

import android.app.ListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlbumsActivity extends ListActivity {

	private List<String> items = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.artists);

		try {
			items.clear();

			if (this.getIntent().getStringExtra("artist") != null) {
				items.addAll(Contexte.getInstance().getMpd().listAlbums((String) this.getIntent().getStringExtra("artist")));
				this.setTitle((String) this.getIntent().getStringExtra("artist"));
			} else {
				items.addAll(Contexte.getInstance().getMpd().listAlbums());
			}

			ArrayAdapter<String> notes = new ArrayAdapter<String>(this, R.layout.artist_row, items);
			setListAdapter(notes);
		} catch (MPDServerException e) {
			e.printStackTrace();
			this.setTitle(e.getMessage());
		}

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, SongsActivity.class);
		
		
		
		intent.putExtra("album", items.get(position));
		//startSubActivity(intent, -1);
		startActivity(intent);
	}

}
