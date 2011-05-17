package com.eightbitmage.gdx.lw.example;

import com.badlogic.gdx.backends.android.livewallpaper.AndroidApplicationLW;
import com.badlogic.gdx.tests.SimpleTest;
import com.eightbitmage.gdxlw.LibdgxWallpaperService;

public class TestLibdgxWallpaperService extends LibdgxWallpaperService {

	@Override
	protected void initialize(AndroidApplicationLW app) {
		app.initialize(new SimpleTest(), false);
	}
	

}
  