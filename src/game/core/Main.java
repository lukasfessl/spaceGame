package game.core;
import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import game.utils.Config;
import game.utils.ResourceStore;

/**
 * 
 * @author Lukas Fessl
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		System.setProperty("java.library.path", "libs");
		System.setProperty("org.lwjgl.librarypath", new File("libs/natives").getAbsolutePath());
	
		try {
			AppGameContainer game = new AppGameContainer(new GameCore("Space ship game 0.5.911"));
			game.setTargetFrameRate(60);
			game.setMaximumLogicUpdateInterval(60);
			game.setAlwaysRender(true);
			game.setDisplayMode(Config.windowWidth, Config.windowHeight, false);
			game.setVSync(true);
			game.setIcon(ResourceStore.imgPath + "icon.png");
			
			Config.load();
			
			game.setFullscreen(Config.fullscreen);
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}
