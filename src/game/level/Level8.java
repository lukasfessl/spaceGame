package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class Level8 extends AbstractLevel {

	public Level8(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	
	protected void createLevel() {
		
		planets.add(new Planet(400, 140, 30, planets.size(), 13, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);

		planets.add(new Planet(250,300, 40, planets.size(), 7, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(233, 610, 40, planets.size(), 11, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(433, 500, 50, planets.size(), 9, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		
		planets.add(new Planet(960, 300, 30, planets.size(), 0, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(15);
		
		planets.add(new Planet(866, 600, 60, planets.size(), 8, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(15);
		
		
		planets.add(new Planet(100, 120, 60, planets.size(), 5, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(30);
		
		planets.add(new Planet(1200, 410, 60, planets.size(), 3, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(30);
		
		
		uis.add(new UI(players.get(Util.selectUiColor()).getTeam()));
		scene.setUser(players.get(Config.playerColor));	// user
		
		scene.setPlanets(planets);
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));
	}
	
}
