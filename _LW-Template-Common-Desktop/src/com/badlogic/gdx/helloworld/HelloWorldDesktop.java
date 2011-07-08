package com.badlogic.gdx.helloworld;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.eightbitmage.lwtemplate.WaterRipplesLW;

public class HelloWorldDesktop {
	public static void main(String[] argv) {

		JoglApplication app = new JoglApplication((ApplicationListener) new WaterRipplesLW(),
				"LW Template App", 320, 480, false);

	}
}
