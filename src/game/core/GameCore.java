package game.core;
import javax.annotation.Resources;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import game.level.AbstractLevel;
import game.level.Level;
import game.level.QuickGame;
import game.level.TestLevel;
import game.level.TestLevel2;
import game.trans.Lang;
import game.utils.Config;
import game.utils.GamePosition;
import game.utils.ResourceStore;
import game.utils.ScreenManager;
import game.utils.Util;

/**
 * 
 * @author Lukas Fessl
 *
 */
public class GameCore extends BasicGame {
	
	private Scene scene;	
	private AbstractLevel tmpLevel;	// for quick game
	private Menu menu;
	private boolean pause;
	private boolean menuSoundPlay;
	
	public GameCore(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		pause = false;
		menuSoundPlay = false;
		ScreenManager.gamePosition = GamePosition.MENU_MAIN;
//		ScreenManager.gamePosition = GamePosition.GAME;
//		scene = new Level().createLevel1(-1).getScene();
	}
	
	
	// -- update methods --

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {	
		if (ResourceStore.progress < 100) {
			// skip first update to render loading text
			if (ResourceStore.progress == 0) {
				ResourceStore.progress = 1;
			} else if (ResourceStore.progress == 1) {
				ResourceStore.init(true);
				menu = new Menu(gc);
			}
		} else {
			updateGame(gc, delta);
		}
	}	
	
	public void updateGame(GameContainer gc, int delta) throws SlickException {
		// Menu
		if (ScreenManager.gamePosition.toString().startsWith(GamePosition.MENU.toString())) {
			// Sound
			this.playSound("menuSound", false);
			
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
		
		if (gc.getInput().isKeyPressed(Input.KEY_U)) {
			if (ResourceStore.languages.get(Config.currentLangIndex) == Lang.CZECH) {
				Config.currentLangIndex = 0;
			} else {
				Config.currentLangIndex = 1;
			}
			ResourceStore.initTranslation();
		}
	}
	
	
	// -- render methods --
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (ResourceStore.progress < 100) {
			g.drawString("Loading...", 10, 740);
		} else {
			renderGame(gc, g);
		}
	}
	
	public void renderGame(GameContainer gc, Graphics g) throws SlickException {
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
		}	
	}
	
	private void initScene() {
		if (ResourceStore.currentMusic != null && Config.sound) {
			ResourceStore.currentMusic.stop();
			ResourceStore.currentMusic = null;
			playSound("gameMusic1", true);
			// All maps have same music, but start position is random
			ResourceStore.currentMusic.setPosition(Config.rs.nextInt(240));
		}

		if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_1) {
			scene = new Level().createLevel1(1).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_2) {
			scene = new Level().createLevel2(2).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_3) {;
			scene = new Level().createLevel3(3).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_4) {
			scene = new Level().createLevel4(4).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_5) {
			scene = new Level().createLevel5(5).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_6) {
			scene = new Level().createLevel6(6).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_7) {
			scene = new Level().createLevel7(7).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_8) {
			scene = new Level().createLevel8(8).getScene();
			playSound("gameMusic2", true); // Custom music
		}

		if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_TEST_1) {
			scene = new TestLevel(-1).getScene();
		} else if (ScreenManager.gamePosition == GamePosition.GAME_LEVEL_TEST_2) {
			scene = new TestLevel2(-1).getScene();
		}
		
		if (ScreenManager.gamePosition == GamePosition.GAME_QUICK) {
//			tmpLevel = new QuickGame(-1);
//			scene = new QuickGame(-1).getScene();
			if (tmpLevel == null) {
				tmpLevel = new QuickGame(-1);
				scene = tmpLevel.cloneScene();
			} else {
				scene = tmpLevel.cloneScene();
			}
		}
	}
	
	private void playSound(String name, boolean manualSoundSet) {
		if (Config.sound) {	
			if (!this.menuSoundPlay) {
				this.menuSoundPlay = true;
				if (ResourceStore.currentMusic != null) {
					ResourceStore.currentMusic.stop();
					ResourceStore.currentMusic = null;
				}
				ResourceStore.currentMusic = ResourceStore.music.get(name);
				ResourceStore.currentMusic.loop();
				ResourceStore.currentMusic.setVolume(0.1f);	
			}
			
			if (manualSoundSet) {
				ResourceStore.currentMusic = ResourceStore.music.get(name);
				ResourceStore.currentMusic.loop();
				ResourceStore.currentMusic.setVolume(0.1f);	
				this.menuSoundPlay = false;
			}
		}
		
		if (!Config.sound && ResourceStore.currentMusic != null) {
			ResourceStore.currentMusic.stop();
			ResourceStore.currentMusic = null;
			this.menuSoundPlay = false;
		}
	}
	
}
