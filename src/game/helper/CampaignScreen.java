package game.helper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import game.core.Screen;
import game.gui.ActionHandler;
import game.gui.Button;
import game.utils.GamePosition;
import game.utils.ScreenManager;

public class CampaignScreen  {
	
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

		Button mission = new Button(100, 200, buttonWidth, buttonHeight, points.clone(), "mission1", labelMarginLeft, labelMarginTop);
		mission.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_1;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission);	
		
		Button mission2 = new Button(250, 200, buttonWidth, buttonHeight, points.clone(), "mission2", labelMarginLeft, labelMarginTop);
		mission2.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission2.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_2;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission2);	
		
		Button mission3 = new Button(400, 200, buttonWidth, buttonHeight, points.clone(), "mission3", labelMarginLeft, labelMarginTop);
		mission3.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission3.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_3;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission3);	
		
		Button mission4 = new Button(550, 200, buttonWidth, buttonHeight, points.clone(), "mission4", labelMarginLeft, labelMarginTop);
		mission4.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission4.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_4;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission4);
		
		Button mission5 = new Button(700, 200, buttonWidth, buttonHeight, points.clone(), "mission5", labelMarginLeft, labelMarginTop);
		mission5.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission5.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_5;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission5);
		
		Button mission6 = new Button(850, 200, buttonWidth, buttonHeight, points.clone(), "mission6", labelMarginLeft, labelMarginTop);
		mission6.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission6.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_6;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission6);
		
		Button mission7 = new Button(1000, 200, buttonWidth, buttonHeight, points.clone(), "mission7", labelMarginLeft, labelMarginTop);
		mission7.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission7.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_7;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission7);
		
		Button mission8 = new Button(1150, 200, buttonWidth, buttonHeight, points.clone(), "mission8", labelMarginLeft, labelMarginTop);
		mission8.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission8.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_8;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission8);
		
		Button mission9 = new Button(100, 350, buttonWidth, buttonHeight, points.clone(), "mission9", labelMarginLeft, labelMarginTop);
		mission9.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission9.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_9;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission9);
		
		Button mission10 = new Button(250, 350, buttonWidth, buttonHeight, points.clone(), "mission10", labelMarginLeft, labelMarginTop);
		mission10.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission10.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_10;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission10);
		
		Button mission11 = new Button(400, 350, buttonWidth, buttonHeight, points.clone(), "mission11", labelMarginLeft, labelMarginTop);
		mission11.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission11.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_11;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission11);
		
		Button mission12 = new Button(550, 350, buttonWidth, buttonHeight, points.clone(), "mission12", labelMarginLeft, labelMarginTop);
		mission12.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission12.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_12;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission12);
		
		Button mission13 = new Button(700, 350, buttonWidth, buttonHeight, points.clone(), "mission13", labelMarginLeft, labelMarginTop);
		mission13.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission13.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_13;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission13);
		
		Button mission14 = new Button(850, 350, buttonWidth, buttonHeight, points.clone(), "mission14", labelMarginLeft, labelMarginTop);
		mission14.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission14.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_14;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission14);
		
		Button mission15 = new Button(1000, 350, buttonWidth, buttonHeight, points.clone(), "mission15", labelMarginLeft, labelMarginTop);
		mission15.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission15.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_15;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission15);
		
		Button mission16 = new Button(1150, 350, buttonWidth, buttonHeight, points.clone(), "mission16", labelMarginLeft, labelMarginTop);
		mission16.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		mission16.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_16;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(mission16);
		
		Button test1 = new Button(1100, 600, buttonWidth, buttonHeight, points.clone(), "Test 1", labelMarginLeft, labelMarginTop);
		test1.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
		test1.setActionHandler(new ActionHandler() {
			@Override
			public void onAction() {
				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_TEST_1;
				ScreenManager.tmpPosition = GamePosition.NEW;
			}
		});
		screen.addButton(test1);
		
//		Button test2 = new Button(1150, 600, buttonWidth, buttonHeight, points.clone(), "Test 2", labelMarginLeft, labelMarginTop);
//		test2.setColors(buttonColor[0], buttonColor[1], buttonColor[2], buttonColor[3]);
//		test2.setActionHandler(new ActionHandler() {
//			@Override
//			public void onAction() {
//				ScreenManager.gamePosition = GamePosition.GAME_LEVEL_TEST_2;
//				ScreenManager.tmpPosition = GamePosition.NEW;
//			}
//		});
//		screen.addButton(test2);

		
		return screen;
	} 
}
