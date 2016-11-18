package game.helper;

import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import game.core.Screen;
import game.gui.ActionHandler;
import game.gui.Button;
import game.object.Owner;
import game.trans.Lang;
import game.utils.Config;
import game.utils.GamePosition;
import game.utils.ResourceStore;
import game.utils.ScreenManager;
import game.utils.Util;

public class SettingsScreen {
	
	public static Screen init(int buttonWidth, int buttonHeight, float points[], int labelMarginLeft, int labelMarginTop, Color 
			buttonColor[], final GameContainer gc) {
		
		final Screen screen = new Screen();
		
		Button playerColor = new Button(548, 250, buttonWidth, buttonHeight, points.clone(), "player", labelMarginLeft, labelMarginTop);
		playerColor.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		screen.addButton(playerColor);

		Button back = new Button(100, 600, buttonWidth, buttonHeight, points.clone(), "back", labelMarginLeft, labelMarginTop);
		back.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		back.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.MENU_MAIN;
			}
		});
		screen.addButton(back);
		
		Button fullscreen = new Button(698, 250, buttonWidth, buttonHeight, points.clone(), "fullScreen", labelMarginLeft, labelMarginTop);
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
		
		Button sound = new Button(548, 400, buttonWidth, buttonHeight, points.clone(), "sound", labelMarginLeft, labelMarginTop);
		sound.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		sound.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				Config.sound = !Config.sound;
				Config.save();
			}
		});
		screen.addButton(sound);
		
		Button language = new Button(698, 400, buttonWidth, buttonHeight, points.clone(), "language", labelMarginLeft, labelMarginTop);
		language.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		language.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				if (Config.currentLangIndex < ResourceStore.languages.size() - 1) {
					Config.currentLangIndex++;
				} else {
					Config.currentLangIndex = 0;
				}
				ResourceStore.initResourceBundle(Config.currentLangIndex);
				
				Config.save();
			}
		});
		screen.addButton(language);
		
		
		// COLORS
		
		Button playerColorBlue = new Button(568, 285, 20, 20, null, 0, 0);
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
		
		Button playerColorRed = new Button(598, 285, 20, 20, null, 0, 0);
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
		
		Button playerColorGreen = new Button(628, 285, 20, 20, null, 0, 0);
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
		
		Button playerColorYellow = new Button(568, 315, 20, 20, null, 0, 0);
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
		
		Button playerColorOrange = new Button(598, 315, 20, 20, null, 0, 0);
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
		
		Button playerColorPink = new Button(628, 315, 20, 20, null, 0, 0);
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


