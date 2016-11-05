package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class Level6 extends AbstractLevel {
	
	public Level6(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	
	protected void createLevel() {
		
		planets.add(new Planet(250, 150, 30, planets.size(), 0, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);

		planets.add(new Planet(250,680, 40, planets.size(), 8, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		
		planets.add(new Planet(1000, 150, 40, planets.size(), 12, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(1050, 670, 50, planets.size(), 14, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		
		planets.add(new Planet(600, 400, 75, planets.size(), 6, 100, 40));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(15);
		
		
		planets.add(new Planet(100, 350, 60, planets.size(), 2, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(30);
		
		planets.add(new Planet(1200, 360, 60, planets.size(), 3, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(40);
		
		
		uis.add(new UI(ResourceStore.players.get(Util.selectUiColor()).getTeam()));
		scene.setUser(ResourceStore.players.get(Config.playerColor));	// user
		
		scene.setPlanets(planets);
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));

	}
	
}
