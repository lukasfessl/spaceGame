package game.helper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import game.core.Screen;
import game.gui.ActionHandler;
import game.gui.Button;
import game.utils.GamePosition;
import game.utils.ScreenManager;

public class PauseScreen {

	
	public static Screen init(int buttonWidth, int buttonHeight, float points[], int labelMarginLeft, int labelMarginTop, Color 
			buttonColor[], final GameContainer gc) {
		
		Screen screen = new Screen();
		
		Button resume = new Button(600, 150, buttonWidth, buttonHeight, points.clone(), "continue", labelMarginLeft, labelMarginTop);
		resume.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		resume.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.tmpPosition = GamePosition.RESUME;
			}
		});
		screen.addButton(resume);	
		
		Button restart = new Button(600, 300, buttonWidth, buttonHeight, points.clone(), "restart", labelMarginLeft, labelMarginTop);
		restart.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		restart.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.tmpPosition = GamePosition.RESTART;
			}
		});
		screen.addButton(restart);	
		
		Button quit = new Button(600, 450, buttonWidth, buttonHeight, points.clone(), "quit", labelMarginLeft, labelMarginTop);
		quit.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		quit.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.tmpPosition = GamePosition.MENU_MAIN;
			}
		});
		screen.addButton(quit);
		
		return screen;
	}
}
