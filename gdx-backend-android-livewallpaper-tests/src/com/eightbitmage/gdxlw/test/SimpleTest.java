package com.eightbitmage.gdxlw.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.tests.utils.GdxTest;

public class SimpleTest extends GdxTest implements InputProcessor {
	float r = 1, g = 0, b = 0;

	@Override
	public void create() {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
				+ ", surface created");
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		GL10 gl = Gdx.app.getGraphics().getGL10();

		gl.glClearColor(r, g, b, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose() {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
				+ ", application destroyed");
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int newParam) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		r = (float) Math.random();
		g = (float) Math.random();
		b = (float) Math.random();
		return false;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean needsGL20() {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}