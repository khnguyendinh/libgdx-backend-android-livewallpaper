/*
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com), Nathan Sweet (admin@esotericsoftware.com)
 * 
 * Modified by Elijah Cornell
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.badlogic.gdx.backends.android.livewallpaper;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Pool;

/**
 * An implementation of the {@link Input} interface for Android.
 * 
 * @author mzechner
 * 
 */
public final class AndroidInputLW implements Input {

	int[] touchX = new int[10];
	int[] touchY = new int[10];
	boolean[] touched = new boolean[10];
	ArrayList<TouchEvent> touchEvents = new ArrayList<TouchEvent>();

	private InputProcessor processor;
	private boolean justTouched;

	class TouchEvent {
		static final int TOUCH_DOWN = 0;
		static final int TOUCH_UP = 1;
		static final int TOUCH_DRAGGED = 2;

		long timeStamp;
		int type;
		int x;
		int y;
		int pointer;
	}

	Pool<TouchEvent> usedTouchEvents = new Pool<TouchEvent>(16, 1000) {
		protected TouchEvent newObject() {
			return new TouchEvent();
		}
	};
	private AndroidTouchHandlerLW touchHandler;
	private int sleepTime;

	public AndroidInputLW(AndroidApplicationLW androidApplicationLW,
			int sleepTime) {

		touchHandler = new AndroidSingleTouchHandlerLW();
		this.sleepTime = sleepTime;
	}

	// ///

	@Override
	public boolean isAccelerometerAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getAccelerometerX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAccelerometerY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAccelerometerZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		return touchX[0];
	}

	@Override
	public int getY() {
		return touchY[0];
	}

	@Override
	public int getX(int pointer) {
		return touchX[pointer];
	}

	@Override
	public int getY(int pointer) {
		return touchY[pointer];
	}

	@Override
	public boolean isTouched(int pointer) {
		return touched[pointer];
	}

	@Override
	public boolean isTouched() {
		return touched[0];
	}

	@Override
	public boolean isButtonPressed(int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isKeyPressed(int key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getTextInput(TextInputListener listener, String title,
			String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOnscreenKeyboardVisible(boolean visible) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsOnscreenKeyboard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supportsMultitouch() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supportsVibrator() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void vibrate(int milliseconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void vibrate(long[] pattern, int repeat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelVibrate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsCompass() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getAzimuth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getPitch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRoll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCatchBackKey(boolean catchBack) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean justTouched() {
		return justTouched;
	}

	// ///

	public void setInputProcessor(InputProcessor processor) {
		synchronized (this) {
			this.processor = processor;
		}
	}

	void processEvents() {
		synchronized (this) {
			justTouched = false;

			if (processor != null) {
				final InputProcessor processor = this.processor;

				int len = touchEvents.size();
				for (int i = 0; i < len; i++) {
					TouchEvent e = touchEvents.get(i);
					switch (e.type) {
					//case TouchEvent.TOUCH_DOWN:
					//	processor.touchDown(e.x, e.y, e.pointer, Buttons.LEFT);
					//	justTouched = true;
					//	break;
					case TouchEvent.TOUCH_UP:
						processor.touchUp(e.x, e.y, e.pointer, Buttons.LEFT);
						justTouched = true;
						break;
					// case TouchEvent.TOUCH_DRAGGED:
					// processor.touchDragged(e.x, e.y, e.pointer);
					}
					usedTouchEvents.free(e);
				}
			} else {
				int len = touchEvents.size();
				for (int i = 0; i < len; i++) {
					TouchEvent e = touchEvents.get(i);
					if (e.type == TouchEvent.TOUCH_UP)
						justTouched = true;
					usedTouchEvents.free(e);
				}

			}

			touchEvents.clear();
		}
	}

	public boolean onTap(int pX, int pY) {

		// synchronized in handler.postTouchEvent()
		touchHandler.onTap(pX, pY, this);

		if (sleepTime != 0) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
		}
		return true;
	}

}
