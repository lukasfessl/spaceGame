package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class Level5 extends AbstractLevel {
	
	public Level5(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	
	protected void createLevel() {
	
		planets.add(new Planet(320, 160, 40, planets.size(), 0, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(300, 360, 50, planets.size(), 1, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(100, 420, 50, planets.size(), 2, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(500, 600, 60, planets.size(), 8, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(1200, 460, 40, planets.size(), 5, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(1030, 510, 50, planets.size(), 4, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(1000, 690, 40, planets.size(), 11, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(800, 130, 70, planets.size(), 6, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(120, 120, 60, planets.size(), 7, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulation(20);
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(1300, 700, 60, planets.size(), 14, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulation(40);
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		
		uis.add(new UI(ResourceStore.players.get(Util.selectUiColor()).getTeam()));
//		uis.add(new UI(ResourceStore.players.get(Color.red).getTeam()));
		scene.setUser(ResourceStore.players.get(Config.playerColor));	// user
		
		scene.setPlanets(planets);
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));

	}
	
}
