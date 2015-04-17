package com.packtpub.libgdx.vikingdodgeball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.packtpub.libgdx.vikingdodgeball.VikingDodgeballMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
        config.height = 600;
        config.resizable = false;
        new LwjglApplication(new VikingDodgeballMain(), config);
	}
}
