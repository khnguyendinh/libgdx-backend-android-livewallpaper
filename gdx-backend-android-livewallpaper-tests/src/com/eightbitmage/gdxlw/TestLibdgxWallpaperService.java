package com.eightbitmage.gdxlw;

import com.badlogic.gdx.backends.android.livewallpaper.AndroidApplicationLW;
import com.badlogic.gdx.tests.TileTest;

public class TestLibdgxWallpaperService extends LibdgxWallpaperService {

	@Override
	protected void initialize(AndroidApplicationLW app) {
		//app.initialize(new SimpleTest(), false);
		app.initialize(new TileTest(), false);
	}
	

}
