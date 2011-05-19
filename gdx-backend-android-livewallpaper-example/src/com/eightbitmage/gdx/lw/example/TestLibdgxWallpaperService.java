package com.eightbitmage.gdx.lw.example;

import android.content.SharedPreferences;
import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.livewallpaper.AndroidApplicationLW;
import com.badlogic.gdx.tests.SimpleTest;
import com.eightbitmage.gdxlw.LibdgxWallpaperService;

public class TestLibdgxWallpaperService extends LibdgxWallpaperService {

//	@Override
//	protected void initialize(AndroidApplicationLW app) {
//		app.initialize(new SimpleTest(), false);
//	}
	
	
	
	private boolean DEBUG = false;
	private final String TAG = "Lucky-LW-Service";

	public static final String SHARED_PREFS_NAME = "luckysettings";

	@Override
	public Engine onCreateEngine() {
		if (DEBUG)
			Log.d(TAG, " > onCreateEngine()");
		return new RainbowSwirlLibdgxWallpaperEngine(this);
	}

	public class RainbowSwirlLibdgxWallpaperEngine extends
			LibdgxWallpaperEngine implements
			SharedPreferences.OnSharedPreferenceChangeListener {

		public RainbowSwirlLibdgxWallpaperEngine(
				LibdgxWallpaperService libdgxWallpaperService) {
			super(libdgxWallpaperService);
		}

		@Override
		protected void initialize(AndroidApplicationLW androidApplicationLW) {

			libdgxWallpaperApp = new SimpleTest();  

			androidApplicationLW.initialize(libdgxWallpaperApp, false);

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
  