package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class Level1 extends AbstractLevel {
	
	public Level1(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	
	protected void createLevel() {
		
		planets.add(new Planet(500, 200, 50, planets.size(), 12, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);

		planets.add(new Planet(700, 500, 50, planets.size(), 11, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(200, 350, 60, planets.size(), 2, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(30);
		
		planets.add(new Planet(1000, 350, 50, planets.size(), 5, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(30);
		
		
		uis.add(new UI(ResourceStore.players.get(Util.selectUiColor()).getTeam()));
		scene.setUser(ResourceStore.players.get(Config.playerColor));	// user
		
		scene.setPlanets(planets);
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));
	}
	
}
