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
	
	private final String TAG = "GDX-LW-Service";
	
	private LibdgxWallpaperEngine previousEngine;
	protected LibdgxWallpaperApp libdgxWallpaperListener;
	
	private boolean DEBUG = false;

	public LibdgxWallpaperService() {
		super();
	}

	abstract protected void initialize(AndroidApplicationLW app);

	@Override
	public void onCreate() {
		if (DEBUG) Log.d(TAG, " > LibdgxWallpaperService - onCreate()");
		super.onCreate();
	}

	@Override
	public Engine onCreateEngine() {
		if (DEBUG) Log.d(TAG, " > onCreateEngine()");
		return new LibdgxWallpaperEngine(this);
	}

	@Override
	public void onDestroy() {
		if (DEBUG) Log.d(TAG, " > LibdgxWallpaperService - onDestroy()");
		super.onDestroy();
	}
	
	// ~~~~~~~~ MyEngine ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	class LibdgxWallpaperEngine extends Engine {

		AndroidApplicationLW app;
		GLBaseSurfaceView view;

		public LibdgxWallpaperEngine(final LibdgxWallpaperService libdgxWallpaperService) {
			super();

			if (DEBUG) Log.d(TAG, " > MyEngine() " + hashCode());

			app = new AndroidApplicationLW(libdgxWallpaperService, this);
			libdgxWallpaperService.initialize(app);
			view = ((AndroidGraphicsLW) app.getGraphics()).getView();

		}

		@Override
		public Bundle onCommand(final String pAction, final int pX,
				final int pY, final int pZ, final Bundle pExtras,
				final boolean pResultRequested) {

			if (DEBUG) Log.d(TAG, " > onCommand(" + pAction + " " + pX + " " + pY + " "
					+ pZ + " " + pExtras + " " + pResultRequested + ")");

			if (pAction.equals(WallpaperManager.COMMAND_TAP)) {
				((AndroidInputLW) app.getInput()).onTap(pX, pY);
			} else if (pAction.equals(WallpaperManager.COMMAND_DROP)) {
				// TODO: handle drop event
			}

			return super.onCommand(pAction, pX, pY, pZ, pExtras,
					pResultRequested);
		}

		@Override
		public void onCreate(final SurfaceHolder surfaceHolder) {

			if (DEBUG) Log.d(TAG, " > onCreate() " + hashCode());

			super.onCreate(surfaceHolder);

			if (previousEngine != null) {
				previousEngine.view.onPause();
			}
			previousEngine = this;
		}

		@Override
		public void onDestroy() {

			if (DEBUG) Log.d(TAG, " > onDestroy() " + hashCode());

			view.onDestroy();
			app.onDestroy();
			super.onDestroy();

		}

		public void onPause() {

			if (DEBUG) Log.d(TAG, " > onPause() " + hashCode());

			app.onPause();
			view.onPause();

		}

		public void onResume() {

			if (DEBUG) Log.d(TAG, " > onResume() " + hashCode());

			app.onResume();
			view.onResume();
		}

		@Override
		public void onSurfaceChanged(final SurfaceHolder holder,
				final int format, final int width, final int height) {

			if (DEBUG) Log.d(TAG, " > onSurfaceChanged() " + isPreview() + " "
					+ hashCode());

			super.onSurfaceChanged(holder, format, width, height);
		}

		@Override
		public void onSurfaceCreated(final SurfaceHolder holder) {

			if (DEBUG) Log.d(TAG, " > onSurfaceCreated() " + hashCode());

			super.onSurfaceCreated(holder);
		}

		@Override
		public void onSurfaceDestroyed(final SurfaceHolder holder) {

			if (DEBUG) Log.d(TAG, " > onSurfaceDestroyed() " + hashCode());

			super.onSurfaceDestroyed(holder);
		}

		@Override
		public void onVisibilityChanged(final boolean visible) {

			if (DEBUG) Log.d(TAG, " > onVisibilityChanged(" + visible + ") " + hashCode());

			if (visible) {
				onResume();
			} else {
				onPause();
			}

			super.onVisibilityChanged(visible);

		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset,
				float xOffsetStep, float yOffsetStep, int xPixelOffset,
				int yPixelOffset) {

			if (DEBUG) Log.d(TAG, " > onVisibilityChanged(" + xOffset + " " + yOffset
					+ " " + xOffsetStep + " " + yOffsetStep + " "
					+ xPixelOffset + " " + yPixelOffset + ") " + hashCode());

			libdgxWallpaperListener.offsetChange(xOffset, yOffset, xOffsetStep, yOffsetStep, xPixelOffset, yPixelOffset);
			
			super.onOffsetsChanged(xOffset, yOffset, xOffsetStep, yOffsetStep,
					xPixelOffset, yPixelOffset);

		}

	}

}
