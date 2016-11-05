package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;

public class QuickGame extends AbstractLevel {

	public QuickGame(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	protected void createLevel() {
		int rowHeight = Config.windowHeight/3;
		int colWidth = Config.windowWidth/5;
		
		// init planets
		while (true) {
			for (int col = 0; col < 5; col++) {
				for (int row = 0; row < 3; row++) {
					if (Config.rs.nextInt(10) > 5) {
						int planetRadius = 30 + Config.rs.nextInt(30);
						int planetType = Config.rs.nextInt(15);
						int randPosX = (col * colWidth) + (planetRadius + 15) + (Config.rs.nextInt(colWidth - (2 * planetRadius) - 15*2));
						int randPosY = (row * rowHeight) + (planetRadius + 15) + (Config.rs.nextInt(rowHeight - (2 * planetRadius) - 15*2));

						planets.add(new Planet(randPosX, randPosY, planetRadius, planets.size(), planetType, 100, 10));
						planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
						planets.get(planets.size()-1).setPopulationSpeedUp(Config.rs.nextInt(2)+1);
						planets.get(planets.size()-1).setPopulation(15);
					}
				}
			}
			if (planets.size() >= 9) {
				break;
			}
			planets.clear();
		}
		
		// init players and UI
		int playerPlanet = Config.rs.nextInt(planets.size());
		int uiPlanet = Config.rs.nextInt(planets.size());
		while(true) {
			if (playerPlanet == uiPlanet) {
				uiPlanet = Config.rs.nextInt(planets.size());
			} else {
				break;
			}
		}

		uis.add(new UI(ResourceStore.players.get(Color.green).getTeam()));
		planets.get(uiPlanet).setPopulation(30);
		planets.get(uiPlanet).setOwner(ResourceStore.players.get(Color.green));
		
		scene.setUser(ResourceStore.players.get(Config.playerColor));	// user
		planets.get(playerPlanet).setPopulation(30);
		planets.get(playerPlanet).setOwner(ResourceStore.players.get(Config.playerColor));
		
		scene.setPlanets(planets);
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));
	}

	
}
