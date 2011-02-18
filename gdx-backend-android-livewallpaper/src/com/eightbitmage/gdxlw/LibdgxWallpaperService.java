/*
 * Copyright 2010 Elijah Cornell
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 *  // Original code provided by Robert Green
 *  // http://www.rbgrn.net/content/354-glsurfaceview-adapted-3d-live-wallpapers
 */

package com.eightbitmage.gdxlw;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

import com.badlogic.gdx.backends.android.livewallpaper.AndroidApplicationLW;
import com.badlogic.gdx.backends.android.livewallpaper.AndroidGraphicsLW;
import com.badlogic.gdx.backends.android.livewallpaper.AndroidInputLW;
import com.badlogic.gdx.backends.android.livewallpaper.surfaceview.GLBaseSurfaceView;

public abstract class LibdgxWallpaperService extends WallpaperService {

	private final String TAG = "GDX-LW";
	
	MyEngine oldEngine;
	MyEngine activeEngine;

	public LibdgxWallpaperService() {
		super();
	}

	abstract protected void initialize(AndroidApplicationLW app);

	public Engine onCreateEngine() {

		Log.d(TAG, " > onCreateEngine()");

		return new MyEngine(this);

	}

	@Override
	public void onCreate() {

		Log.d(TAG, " > LibdgxWallpaperService - onCreate()");

		super.onCreate();
	}

	@Override
	public void onDestroy() {

		Log.d(TAG, " > LibdgxWallpaperService - onDestroy()");

		super.onDestroy();
	}

	// ~~~~~~~~ MyEngine ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	class MyEngine extends Engine {

		AndroidApplicationLW app;
		GLBaseSurfaceView view;

		public MyEngine(LibdgxWallpaperService libdgxWallpaperService) {
			super();

			Log.d(TAG, " > MyEngine() " + this.hashCode());

			app = new AndroidApplicationLW(libdgxWallpaperService, this);
			libdgxWallpaperService.initialize(app);
			view = ((AndroidGraphicsLW) app.getGraphics()).getView();

			// setRenderer((AndroidGraphicsLW) app.getGraphics());
			// setRenderMode(RENDERMODE_CONTINUOUSLY);
		}

		@Override
		public Bundle onCommand(final String pAction, final int pX,
				final int pY, final int pZ, final Bundle pExtras,
				final boolean pResultRequested) {

			Log.d(TAG, " > onCommand(" + pAction + " " + pX + " " + pY + " "
					+ pZ + " " + pExtras + " " + pResultRequested + ")");

			if (pAction.equals(WallpaperManager.COMMAND_TAP)) {
				((AndroidInputLW) app.getInput()).onTap(pX, pY);
			} else if (pAction.equals(WallpaperManager.COMMAND_DROP)) {
				// TODO: handle drop event
			}

			return super.onCommand(pAction, pX, pY, pZ, pExtras,
					pResultRequested);
		}

		public void onResume() {

			Log.d(TAG, " > onResume() " + this.hashCode());

			app.onResume();
			 view.onResume();
			// super.onResume();
		}

		public void onPause() {

			Log.d(TAG, " > onPause() " + this.hashCode());

			app.onPause();
			 view.onPause();
			// super.onPause();

		}

		@Override
		public void onDestroy() {

			Log.d(TAG, " > onDestroy() " + this.hashCode());

			view.onDestroy();
			app.onDestroy();
			super.onDestroy();

		}

		@Override
		public void onVisibilityChanged(boolean visible) {

			Log.d(TAG,
					" > onVisibilityChanged(" + visible + ") "
							+ this.hashCode());

			if (visible) {
				onResume();
			} else {
				onPause();
			}

			super.onVisibilityChanged(visible);

		}

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {

			Log.d(TAG, " > onCreate() " + this.hashCode());

			super.onCreate(surfaceHolder);
			
			if (oldEngine != null) {
				oldEngine.view.onPause();
			}
			oldEngine = this;
		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format,
				int width, int height) {

			Log.d(TAG,
					" > onSurfaceChanged() " + this.isPreview() + " "
							+ this.hashCode());

			//view.surfaceChanged(holder, format, width, height);

			super.onSurfaceChanged(holder, format, width, height);
		}

		@Override
		public void onSurfaceCreated(SurfaceHolder holder) {

			Log.d(TAG, " > onSurfaceCreated() " + this.hashCode());

			//view.surfaceCreated(holder);

			super.onSurfaceCreated(holder);
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {

			Log.d(TAG, " > onSurfaceDestroyed() " + this.hashCode());

			//view.surfaceDestroyed(holder);

			super.onSurfaceDestroyed(holder);
		}

	}

}
