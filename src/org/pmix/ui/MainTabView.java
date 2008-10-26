package org.pmix.ui;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Intent;


public class MainTabView extends TabActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    

    final TabHost tabHost = getTabHost();


    LayoutInflater.from(this).inflate(R.layout.tabs, tabHost.getTabContentView(), true);
    

    Intent i = new Intent(this, MainMenuActivity.class);
    tabHost.addTab(tabHost.newTabSpec("now playing")
            .setIndicator("now playing")
            .setContent(i));

    i = new Intent(this, PlaylistActivity.class);
    tabHost.addTab(tabHost.newTabSpec("playlist")
            .setIndicator("playlist")
            .setContent(i));

    i = new Intent(this, ArtistsActivity.class);
    tabHost.addTab(tabHost.newTabSpec("browse")
            .setIndicator("browse")
            .setContent(i));

    i = new Intent(this, ArtistsActivity.class);
    tabHost.addTab(tabHost.newTabSpec("search")
            .setIndicator("search")
            .setContent(i));

  }
}
