package com.helloandroid.android.horizontalslider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.helloandroid.android.horizontalslider.HorizontalSlider.OnProgressChangeListener;

public class SliderTest extends Activity {

	private HorizontalSlider slider;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.main);
		setProgressBarVisibility(true);

		slider = (HorizontalSlider) this.findViewById(R.id.slider);
		slider.setOnProgressChangeListener(changeListener);
	}

	private OnProgressChangeListener changeListener = new OnProgressChangeListener() {

		public void onProgressChanged(View v, int progress) {
			setProgress(progress);
		}

	};
}