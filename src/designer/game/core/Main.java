package designer.game.core;
import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import game.utils.Config;
import game.utils.ResourceStore;

public class Main {
	
	public static void main(String[] args) {
		
		System.setProperty("java.library.path", "libs");
		System.setProperty("org.lwjgl.librarypath", new File("libs/natives").getAbsolutePath());
	
		try {
			AppGameContainer game = new AppGameContainer(new DesignerCore("Designer 0.3.1"));
			game.setTargetFrameRate(60);
			game.setMaximumLogicUpdateInterval(60);
			game.setAlwaysRender(true);
			game.setDisplayMode(Config.windowWidth, Config.windowHeight, false);
			game.setVSync(true);
			game.setIcon(ResourceStore.imgPath + "icon.png");		
			game.setFullscreen(Config.fullscreen);
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}
