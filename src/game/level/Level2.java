package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class Level2 extends AbstractLevel {
	
	public Level2(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	
	protected void createLevel() {
		
		planets.add(new Planet(220, 100, 50, planets.size(), 0, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);

		planets.add(new Planet(990, 600, 30, planets.size(), 11, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(600, 330, 50, planets.size(), 12, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(200, 300, 60, planets.size(), 2, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(30);
		
		planets.add(new Planet(1000, 350, 60, planets.size(), 15, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(30);
		
		
		uis.add(new UI(players.get(Util.selectUiColor()).getTeam()));
		scene.setUser(players.get(Config.playerColor));	// user
		
		scene.setPlanets(planets);
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));
	}
	
}
