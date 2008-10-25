package org.pmix.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.a0z.mpd.Directory;
import org.a0z.mpd.MPDServerException;
import org.a0z.mpd.Music;
import org.pmix.settings.Contexte;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FSActivity extends ListActivity {
	private List<String> items = new ArrayList<String>();

	private Directory currentDirectory = null;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.files);

		items.clear();
		try {

			if (this.getIntent().getStringExtra("directory") != null) {
				currentDirectory = Contexte.getInstance().getMpd().getRootDirectory().makeDirectory((String) this.getIntent().getStringExtra("directory"));
			} else {
				currentDirectory = Contexte.getInstance().getMpd().getRootDirectory();
			}
			currentDirectory.refreshData();

			Collection<Directory> directories = currentDirectory.getDirectories();

			for (Directory child : directories) {
				items.add(child.getName());
			}

			Collection<Music> musics = currentDirectory.getFiles();

			for (Music music : musics) {
				items.add(music.getTitle());
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

		// click on a file
		if (position > currentDirectory.getDirectories().size() - 1 || currentDirectory.getDirectories().size() == 0) {

			Music music = (Music) currentDirectory.getFiles().toArray()[position - currentDirectory.getDirectories().size()];

			try {

				int songId = -1;
				// try to find it in the current playlist first

				Collection<Music> founds = Contexte.getInstance().getMpd().find("filename", music.getFullpath());
				
				// not found
				if (founds.isEmpty()) {
					Contexte.getInstance().getMpd().getPlaylist().add(music);
				} else {
					// found
					songId = founds.toArray(new Music[founds.size()])[0].getSongId();
				}
				if (songId > -1) {
					Contexte.getInstance().getMpd().skipTo(songId);
				}
				
			} catch (MPDServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// click on a directory
			// open the same subactivity, it would be better to reuse the
			// same instance

			Intent intent = new Intent(this, FSActivity.class);
			String dir;

			dir = ((Directory) currentDirectory.getDirectories().toArray()[position]).getFullpath();

			intent.putExtra("directory", dir);
			//startSubActivity(intent, -1);
			startActivity(intent);

		}

	}
}
