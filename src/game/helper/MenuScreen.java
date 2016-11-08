package game.helper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import game.core.Screen;
import game.gui.ActionHandler;
import game.gui.Button;
import game.utils.GamePosition;
import game.utils.ScreenManager;

public class MenuScreen {

	
	public static Screen init(int buttonWidth, int buttonHeight, float points[], int labelMarginLeft, int labelMarginTop, Color 
			buttonColor[], final GameContainer gc) {
		
		Screen screen = new Screen();

		Button campaign = new Button(500, 200, buttonWidth, buttonHeight, points.clone(), "campaign", labelMarginLeft, labelMarginTop);
		campaign.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		campaign.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.MENU_CAMPAIGN_SELECT;
			}
		});
		screen.addButton(campaign);
		
		
		
		Button quickGame = new Button(650, 200, buttonWidth, buttonHeight, points.clone(), "quickGame", labelMarginLeft, labelMarginTop);
		quickGame.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		quickGame.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.MENU_QUICK_GAME;
			}
		});
		screen.addButton(quickGame);
		
		
		
		Button settings = new Button(500, 350, buttonWidth, buttonHeight, points.clone(), "settings", labelMarginLeft, labelMarginTop);
		settings.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		settings.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.MENU_SETTINGS;
			}
		});
		screen.addButton(settings);
		
		
		
		Button quit = new Button(650, 350, buttonWidth, buttonHeight, points.clone(), "quit", labelMarginLeft, labelMarginTop);
		quit.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		quit.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				gc.exit();
			}
		});
		screen.addButton(quit);
		
		
		
		return screen;
	}	
}
