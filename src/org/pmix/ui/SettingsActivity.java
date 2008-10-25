package org.pmix.ui;

import org.pmix.settings.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.settings);
		final EditText editText = (EditText) findViewById(R.id.serverAddress);
		
		editText.setText(Settings.getInstance().getServerAddress());
		
		Button button = (Button) findViewById(R.id.ok);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Settings.getInstance().setServerAddress(editText.getText().toString());
				finish();
				
			}
		});
		
	}
	
}
