package com.eightbitmage.gdxlw.test;

import com.badlogic.gdx.backends.android.livewallpaper.AndroidApplicationLW;
import com.eightbitmage.gdxlw.LibdgxWallpaperInterface;
import com.eightbitmage.gdxlw.LibdgxWallpaperService;

public class TestLibdgxWallpaperService extends LibdgxWallpaperService {
	
	@Override
	protected LibdgxWallpaperInterface initialize(AndroidApplicationLW androidApplicationLW) {
		
		PongTest libdgxWallpaperApp = new PongTest();
		
		androidApplicationLW.initialize(libdgxWallpaperApp, false);
		
		return libdgxWallpaperApp;
		
	}

} 
