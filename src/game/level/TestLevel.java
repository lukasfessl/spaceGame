package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class TestLevel extends AbstractLevel {
	
	public TestLevel(int id) {
		super();
		scene = new Scene(id);
		createLevel();
	}
	
	
	protected void createLevel() {
		
		planets.add(new Planet(1020, 120, 60, planets.size(), 1, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(720, 520, 60, planets.size(), 4, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(440, 190, 60, planets.size(), 5, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(100);;
		
		planets.add(new Planet(1020, 333, 40, planets.size(), 2, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(100);
		
		
		uis.add(new UI(ResourceStore.players.get(Util.selectUiColor()).getTeam()));
//		uis.add(new UI(ResourceStore.players.get(Color.red).getTeam()));
		scene.setUser(ResourceStore.players.get(Config.playerColor));	// user
		
		scene.setPlanets(planets);
//		scene.setUIs(uis);
		scene.setBackground(ResourceStore.images.get("bcg_5"));
	}
	
}
