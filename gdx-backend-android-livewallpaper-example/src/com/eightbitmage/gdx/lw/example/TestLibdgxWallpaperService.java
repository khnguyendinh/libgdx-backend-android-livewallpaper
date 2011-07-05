package com.eightbitmage.gdx.lw.example;

import android.content.SharedPreferences;
import android.util.Log;

import com.badlogic.gdx.backends.android.livewallpaper.AndroidApplicationLW;
import com.badlogic.gdx.tests.WaterRipplesLW;
import com.eightbitmage.gdxlw.LibdgxWallpaperService;

public class TestLibdgxWallpaperService extends LibdgxWallpaperService {
	
	private boolean DEBUG = true;
	private final String TAG = "Example-LW-Service";

	public static final String SHARED_PREFS_NAME = "examplesettings";

	@Override
	public Engine onCreateEngine() {
		if (DEBUG) {
			Log.d(TAG, " > onCreateEngine()");
		}
			
		return new ExampleLibdgxWallpaperEngine(this);
	}

	public class ExampleLibdgxWallpaperEngine extends
			LibdgxWallpaperEngine implements
			SharedPreferences.OnSharedPreferenceChangeListener {

		public ExampleLibdgxWallpaperEngine(
				LibdgxWallpaperService libdgxWallpaperService) {
			super(libdgxWallpaperService);
		}

		@Override
		protected void initialize(AndroidApplicationLW androidApplicationLW) {
			
			WaterRipplesLW app = new WaterRipplesLW();
			
			setWallpaperListener(app);

			androidApplicationLW.initialize(app, false);

			//

			SharedPreferences mPrefs = getSharedPreferences(SHARED_PREFS_NAME,
					0);
			mPrefs.registerOnSharedPreferenceChangeListener(this);
			onSharedPreferenceChanged(mPrefs, null);

		}

		// ///

		@Override
		public void onSharedPreferenceChanged(SharedPreferences prefs,
				String key) {
		}
	}
	

}
  