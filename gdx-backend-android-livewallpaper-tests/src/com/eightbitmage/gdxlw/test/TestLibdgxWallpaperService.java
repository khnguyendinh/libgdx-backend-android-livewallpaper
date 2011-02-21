package com.eightbitmage.gdxlw.test;

import com.badlogic.gdx.backends.android.livewallpaper.AndroidApplicationLW;
import com.eightbitmage.gdxlw.LibdgxWallpaperService;

public class TestLibdgxWallpaperService extends LibdgxWallpaperService {

	@Override
	protected void initialize(AndroidApplicationLW app) {
		app.initialize(new PongTest(), true); 
	}

}
