package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class Level4 extends AbstractLevel {

	public Level4(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	
	protected void createLevel() {
		
		planets.add(new Planet(353, 270, 30, planets.size(),0, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);

		planets.add(new Planet(300, 500, 50, planets.size(), 12, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(603, 100, 40, planets.size(), 11, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(656, 450, 45, planets.size(), 2, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(950, 350, 30, planets.size(), 13, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(1150, 650, 45, planets.size(), 1, 100, 10));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(140, 140, 40, planets.size(), 7, 35, 20));
		planets.get(planets.size()-1).setOwner(players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(30);
		
		planets.add(new Planet(1100, 210, 40, planets.size(), 5, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(30);
		
		planets.add(new Planet(1200, 410, 50, planets.size(), 14, 100, 20));
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
