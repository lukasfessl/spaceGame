package game.helper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import game.core.Screen;
import game.gui.ActionHandler;
import game.gui.Button;
import game.utils.GamePosition;
import game.utils.ScreenManager;

public class QuickGameScreen {
	
	public static Screen init(int buttonWidth, int buttonHeight, float points[], int labelMarginLeft, int labelMarginTop, Color 
			buttonColor[], final GameContainer gc) {
		
		Screen screen = new Screen();
		
		Button back = new Button(100, 600, buttonWidth, buttonHeight, points.clone(), "back", labelMarginLeft, labelMarginTop);
		back.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		back.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.MENU_MAIN;
			}
		});
		screen.addButton(back);
		
		
		Button play = new Button(1150, 600, buttonWidth, buttonHeight, points.clone(), "play", labelMarginLeft, labelMarginTop);
		play.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		play.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_QUICK;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(play);
		
		return screen;
	}
}
