package game.core;
import java.util.Timer;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import game.level.AbstractLevel;
import game.level.Level1;
import game.level.Level2;
import game.level.Level3;
import game.level.Level4;
import game.level.Level5;
import game.level.Level6;
import game.level.Level7;
import game.level.Level8;
import game.level.QuickGame;
import game.level.TestLevel;
import game.level.TestLevel2;
import game.utils.Config;
import game.utils.GamePosition;
import game.utils.ResourceStore;
import game.utils.ScreenManager;

public class GameCore extends BasicGame {
	
	Scene scene;	
	AbstractLevel tmpLevel;	// for quick game
	Menu menu;
	boolean pause;

	public GameCore(String title) {
		super(title);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		ResourceStore.init();
		pause = false;
		ScreenManager.gamePosition = GamePosition.MENU_MAIN;
		menu = new Menu(gc);
//		Config.gamePosition = GamePosition.GAME;
//		scene = new Level6(-1).createLevel();
	}
	

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {	
		// Menu
		if (ScreenManager.gamePosition.toString().startsWith(GamePosition.MENU.toString())) {
			menu.update(gc, delta);
			
			// When mission was finished was set to tmp finished_level flag
			if (ScreenManager.tmpPosition == GamePosition.FINISHED_LEVEL) {
				ScreenManager.tmpPosition = null;
				if (scene.getLevelId() == Config.unlockedLevel) {
					Config.unlockedLevel++;
					Config.save();
					menu.updateCampaignMenu();
				}
				scene = null;
			}
			
			// Check if you lose go back to menu
			if (ScreenManager.tmpPosition == GamePosition.MENU_CAMPAIGN_SELECT) {
				ScreenManager.tmpPosition = null;
				pause = false;
				scene = null;
				tmpLevel = null;
				ScreenManager.gamePosition = GamePosition.MENU_CAMPAIGN_SELECT;
			}
		}

		// Game
		if (ScreenManager.gamePosition.toString().startsWith(GamePosition.GAME.toString())) {
			// New game - create new Level
			if (scene == null && ScreenManager.tmpPosition == GamePosition.NEW) {
				ScreenManager.tmpPosition = null;
				initScene();
			}
			
			// Gaming
			if (scene != null && !pause) {
				scene.update(gc, delta);
			}
			
			// Game is paused and show pause menu
			if (scene != null && pause) {
				menu.updatePauseMenu(gc, delta);
				// Check clicked resume
				if (ScreenManager.tmpPosition == GamePosition.RESUME) {
					ScreenManager.tmpPosition = null;
					pause = false;
				}
				
				// Check clicked restart
				if (ScreenManager.tmpPosition == GamePosition.RESTART) {
					ScreenManager.tmpPosition = null;
					pause = false;
					initScene();
				}
			}
			
			// Check clicked back to menu or quickgame is finished
			if (ScreenManager.tmpPosition == GamePosition.MENU_MAIN) {
				ScreenManager.tmpPosition = null;
				pause = false;
				scene = null;
				tmpLevel = null;
				ScreenManager.gamePosition = GamePosition.MENU_MAIN;
			}

			// When gaming and esc key was pressed
			if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
				if (pause) {
					pause = false;
					ScreenManager.tmpPosition = null;
				} else {
					pause = true;
					ScreenManager.tmpPosition = GamePosition.PAUSE;
				}
			}
		}


		
		if (gc.getInput().isKeyPressed(Input.KEY_R) ) {
			Config.unlockedLevel = 1;
			Config.save();
			menu.updateCampaignMenu();
			
		}
	
		if (gc.getInput().isKeyPressed(Input.KEY_T) ) {
			Config.manualyLevelUnlock = !Config.manualyLevelUnlock; 
			Config.save();
			menu.updateCampaignMenu();
		}
	}	
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Render Menu
		if (ScreenManager.gamePosition.toString().startsWith(GamePosition.MENU.toString())) {
			menu.render(gc, g);
		}
		
		// Render Game
		if (ScreenManager.gamePosition.toString().startsWith(GamePosition.GAME.toString())) {
			if (scene != null) {
				scene.render(gc, g);
			}
			
			if (pause) {
				g.setColor(new Color(0,0,0, 0.5f));
				g.fillRect(0, 0, Config.windowWidth, Config.windowHeight);
				g.setColor(Color.red);
				g.drawString("PAUZA", 20, 20);
			}
			
			if (ScreenManager.tmpPosition == GamePosition.PAUSE) {
				menu.renderPauseMenu(gc, g);
			}	
			
//			g.setColor(Color.red);
//			g.resetLineWidth(1);
//			for (int col = 0; col < 5; col++) {
//				for (int row = 0; row < 3; row++) {
//					g.drawRect(col*(Config.windowWidth/5) , row*(Config.windowHeight/3), Config.windowWidth/5, Config.windowHeight/3);
//				}
//			}
		}	
	}
	
	
	private void initScene() {
		if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_1) {
			scene = new Level1(1).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_2) {
			scene = new Level2(2).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_3) {
			scene = new Level3(3).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_4) {
			scene = new Level4(4).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_5) {
			scene = new Level5(5).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_6) {
			scene = new Level6(6).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_7) {
			scene = new Level7(7).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_8) {
			scene = new Level8(8).getScene();
		}

		if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_TEST_1) {
			scene = new TestLevel(-1).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_TEST_2) {
			scene = new TestLevel2(-1).getScene();
		}
		
		if (ScreenManager.gamePosition == GamePosition.GAME_QUICK) {
			tmpLevel = new QuickGame(-1);
			scene = new QuickGame(-1).getScene();
//			if (tmpLevel == null) {
//				tmpLevel = new QuickGame(-1);
//				scene = tmpLevel.cloneScene();
//			} else {
//				System.out.println("xx");
//				scene = tmpLevel.cloneScene();
//			}
		}
	}
	


}
