package game.helper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import game.core.Screen;
import game.gui.ActionHandler;
import game.gui.Button;
import game.utils.Config;
import game.utils.GamePosition;
import game.utils.ScreenManager;
import game.utils.Util;

public class SettingsScreen {
	
	public static Screen init(int buttonWidth, int buttonHeight, float points[], int labelMarginLeft, int labelMarginTop, Color 
			buttonColor[], final GameContainer gc) {
		
		final Screen screen = new Screen();
		
		Button playerColor = new Button(600, 150, buttonWidth, buttonHeight, points.clone(), "Hráč", labelMarginLeft, labelMarginTop);
		playerColor.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		screen.addButton(playerColor);

		Button back = new Button(100, 600, buttonWidth, buttonHeight, points.clone(), "Zpět", labelMarginLeft, labelMarginTop);
		back.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		back.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.MENU_MAIN;
			}
		});
		screen.addButton(back);
		
		Button fullscreen = new Button(600, 300, buttonWidth, buttonHeight, points.clone(), "Celá\nobrazovka", labelMarginLeft, labelMarginTop);
		fullscreen.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		fullscreen.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.fullscreen = !Config.fullscreen;
				Config.save();
				try {
					gc.setFullscreen(Config.fullscreen);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		screen.addButton(fullscreen);
		
		Button sound = new Button(600, 450, buttonWidth, buttonHeight, points.clone(), "Zvuk", labelMarginLeft, labelMarginTop);
		sound.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		sound.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.sound = !Config.sound;
				Config.save();
			}
		});
		screen.addButton(sound);
		
		
		// COLORS
		
		Button playerColorBlue = new Button(620, 185, 20, 20, null, 0, 0);
		playerColorBlue.setColors(null, Color.blue, null, null);
		playerColorBlue.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.playerColor = Color.blue;
				initSelectedPlayerColor(screen);
				Config.save();
			}
		});
		screen.addButton(playerColorBlue);
		
		Button playerColorRed = new Button(650, 185, 20, 20, null, 0, 0);
		playerColorRed.setColors(null, Color.red, null, null);
		playerColorRed.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.playerColor = Color.red;
				initSelectedPlayerColor(screen);
				Config.save();
			}
		});
		screen.addButton(playerColorRed);
		
		Button playerColorGreen = new Button(680, 185, 20, 20, null, 0, 0);
		playerColorGreen.setColors(null, Color.green, null, null);
		playerColorGreen.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.playerColor = Color.green;
				initSelectedPlayerColor(screen);
				Config.save();
			}
		});
		screen.addButton(playerColorGreen);
		
		Button playerColorYellow = new Button(620, 215, 20, 20, null, 0, 0);
		playerColorYellow.setColors(null, Color.yellow, null, null);
		playerColorYellow.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.playerColor = Color.yellow;
				initSelectedPlayerColor(screen);
				Config.save();
			}
		});
		screen.addButton(playerColorYellow);
		
		Button playerColorOrange = new Button(650, 215, 20, 20, null, 0, 0);
		playerColorOrange.setColors(null, Color.orange, null, null);
		playerColorOrange.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.playerColor = Color.orange;
				initSelectedPlayerColor(screen);
				Config.save();
			}
		});
		screen.addButton(playerColorOrange);
		
		Button playerColorPink = new Button(680, 215, 20, 20, null, 0, 0);
		playerColorPink.setColors(null, Color.pink, null, null);
		playerColorPink.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.playerColor = Color.pink;
				initSelectedPlayerColor(screen);
				Config.save();
			}
		});
		screen.addButton(playerColorPink);
		
		
		return screen;
	} 


	public static void initSelectedPlayerColor(Screen screen) {
		for (Button button : screen.getButtons()) {
			if (button.getLabel() == null) {
				if (Util.compColor(Config.playerColor, button.getFill())) {
					button.setBorder(Color.white);
					button.setBorderSize(2);
				} else {
					button.setBorder(null);
				}
			}
		}
	}

}


