package com.eightbitmage.lwtemplate;

import com.badlogic.gdx.backends.android.livewallpaper.AndroidApplicationLW;
import com.eightbitmage.gdxlw.LibdgxWallpaperService;


public class TestLibdgxWallpaperService extends LibdgxWallpaperService {
		
	
	@Override
	public Engine onCreateEngine() {
		return new ExampleLibdgxWallpaperEngine(this); 
	}

	public class ExampleLibdgxWallpaperEngine extends LibdgxWallpaperEngine {

		public ExampleLibdgxWallpaperEngine(
				LibdgxWallpaperService libdgxWallpaperService) {
			super(libdgxWallpaperService);
		}

		@Override
		protected void initialize(AndroidApplicationLW androidApplicationLW) {

			WaterRipplesLW app = new WaterRipplesLW();

			setWallpaperListener(app); 

			androidApplicationLW.initialize(app, false);

		}

	}

}
