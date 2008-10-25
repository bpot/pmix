package org.pmix.ui;

import java.util.ArrayList;
import java.util.List;

import org.a0z.mpd.MPDServerException;
import org.pmix.settings.Contexte;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArtistsActivity extends ListActivity {

	private List<String> items = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.artists);

		items.clear();
		try {
      items.add("Add All Songs");
			items.addAll(Contexte.getInstance().getMpd().listArtists());

			ArrayAdapter<String> notes = new ArrayAdapter<String>(this, R.layout.artist_row, items);
			setListAdapter(notes);
		} catch (MPDServerException e) {
			e.printStackTrace();
			this.setTitle(e.getMessage());
		}

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, AlbumsActivity.class);
		intent.putExtra("artist", items.get(position));
		//startSubActivity(intent, -1);
		startActivity(intent);
	}

}
