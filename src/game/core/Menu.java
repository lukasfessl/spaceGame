package game.core;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import game.gui.Button;
import game.helper.CampaignScreen;
import game.helper.MenuScreen;
import game.helper.PauseScreen;
import game.helper.QuickGameScreen;
import game.helper.SettingsScreen;
import game.trans.Lang;
import game.utils.Config;
import game.utils.GamePosition;
import game.utils.ResourceStore;
import game.utils.ScreenManager;

/**
 * 
 * @author Lukas Fessl
 *
 */
public class Menu {

	// Button dimension
	private int buttonWidth;
	private int buttonHeight;
	private Color buttonColor[];
	private float points[];
	
	// Button label position
	private int labelMarginTop;
	private int labelMarginLeft;
	
	// Menu Screens
	private HashMap<GamePosition, Screen> screens;
	Screen mainMenuScreen;
	Screen campaignScreen;
	Screen settingsScreen;
	Screen pauseScreen;
	Screen quickGameScreen;
	
	public Menu(final GameContainer gc) {
		this.screens = new HashMap<GamePosition, Screen>();
		this.buttonWidth = 120;
		this.buttonHeight = 110;
		this.buttonColor = new Color[]{Color.white, new Color(0,0,0,0.6f), Color.red, new Color(0,0,0,1f)};
		this.labelMarginTop = 10;
		this.labelMarginLeft = 10;
		this.points = new float[] {
				0, 0,
				buttonWidth, 0,
				buttonWidth, buttonHeight - 20,
				buttonWidth - 20, buttonHeight,
				0, buttonHeight,
		};
		
		// MENU MAIN
		this.screens.put(GamePosition.MENU_MAIN, MenuScreen.init(buttonWidth, buttonHeight, points.clone(), labelMarginLeft, labelMarginTop, buttonColor, gc));
		// MENU CAMPAIGN SELECT
		this.screens.put(GamePosition.MENU_CAMPAIGN_SELECT, CampaignScreen.init(buttonWidth, buttonHeight, points.clone(), labelMarginLeft, labelMarginTop, buttonColor, gc));
		updateCampaignMenu();
		// QUICK GAME SCREEN
		this.screens.put(GamePosition.MENU_QUICK_GAME, QuickGameScreen.init(buttonWidth, buttonHeight, points.clone(), labelMarginLeft, labelMarginTop, buttonColor, gc));
		// MENu SETTINGS
		this.screens.put(GamePosition.MENU_SETTINGS, SettingsScreen.init(buttonWidth, buttonHeight, points.clone(), labelMarginLeft, labelMarginTop, buttonColor, gc));
		SettingsScreen.initSelectedPlayerColor(screens.get(GamePosition.MENU_SETTINGS));
		// MENU PAUSE - IN GAME
		this.screens.put(GamePosition.PAUSE, PauseScreen.init(buttonWidth, buttonHeight, points.clone(), labelMarginLeft, labelMarginTop, buttonColor, gc));
	}
	
	public void updateCampaignMenu() {
		int count = 0;
		for (Button button : getScreens().get(GamePosition.MENU_CAMPAIGN_SELECT).getButtons()) {
			if (button.getLabel().toLowerCase().startsWith("mise")) {
				if (!Config.manualyLevelUnlock) {
					if (Config.unlockedLevel > count) {
						button.setDisabled(false);
					} else if (Config.unlockedLevel < count + 1) {
						button.setDisabled(true);
					}
				} else {
					button.setDisabled(false);
				}
				count ++;
			}
			
		}
	}
	

	// Menu
	public void update(GameContainer gc, int delta) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_U)) {
			if (ResourceStore.lang == Lang.CZECH) {
				ResourceStore.lang = Lang.ENGLISH;
			} else {
				ResourceStore.lang = Lang.CZECH;
			}
			ResourceStore.initTranslation();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			if (ScreenManager.gamePosition == GamePosition.MENU_SETTINGS || ScreenManager.gamePosition == GamePosition.MENU_CAMPAIGN_SELECT) {
				ScreenManager.gamePosition = GamePosition.MENU_MAIN;
			}
		}
		screens.get(ScreenManager.gamePosition).update(gc, delta);
	}

	
	
	// Menu
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(ResourceStore.backgrounds.get(0), 0, 0, 1600, 1226, 0, 0, 1600, 1226);
		screens.get(ScreenManager.gamePosition).render(gc, g);
		if (ScreenManager.gamePosition == GamePosition.MENU_CAMPAIGN_SELECT) {
			int count = 0;
			for (Button button : screens.get(GamePosition.MENU_CAMPAIGN_SELECT).getButtons()) {
				if (button.getLabel().toLowerCase().startsWith("mise")) {
					if (button.isDisabled()) {
						g.drawImage(ResourceStore.items.get("lock"), button.getMask().getX() + 30 , button.getMask().getY() + 30 , button.getMask().getX() + 91, button.getMask().getY() + 91, 0, 0, 
								128, 128, Color.red);
					}
					if (Config.unlockedLevel > count + 1) {
						g.drawImage(ResourceStore.items.get("check"), button.getMask().getX() + 30 , button.getMask().getY() + 30 , button.getMask().getX() + 91, button.getMask().getY() + 91, 0, 0, 
								128, 128, Color.green);
					}
					count ++;
				}
			}
		}
		
		g.setColor(Color.white);
		if (ScreenManager.gamePosition == GamePosition.MENU_SETTINGS) {
			Screen screen =  screens.get(GamePosition.MENU_SETTINGS);
			for (Button button : screen.getButtons()) {
				if (button.getLabel() != null) {
					if (button.getLabel().toLowerCase().equals("celá\nobrazovka")) {
						g.drawString(Config.fullscreen ? "Zapnuto" : "Vypnuto", button.getMask().getX() + 10, button.getMask().getY() + 80);
					}
					
					if (button.getLabel().toLowerCase().equals("zvuk")) {
						g.drawString(Config.sound ? "Zapnuto" : "Vypnuto", button.getMask().getX() + 10, button.getMask().getY() + 80);
					}
				}
			}
			
		}
	}

	
	
	// Pause menu in game
	public void updatePauseMenu(GameContainer gc, int delta) throws SlickException {
		screens.get(GamePosition.PAUSE).update(gc, delta);
	}
	
	// Pause menu in game
	public void renderPauseMenu(GameContainer gc, Graphics g) throws SlickException {
		screens.get(GamePosition.PAUSE).render(gc, g);
	}

	
	
	public HashMap<GamePosition, Screen> getScreens() {
		return screens;
	}

	public void setScreens(HashMap<GamePosition, Screen> screens) {
		this.screens = screens;
	}
	

	
}
