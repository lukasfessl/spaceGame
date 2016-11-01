package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class TestLevel2 extends AbstractLevel {
	
	public TestLevel2(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	
	protected void createLevel() {
		planets.add(new Planet(320, 160, 40, planets.size(), 0, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(300, 360, 50, planets.size(), 1, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(100, 420, 50, planets.size(), 2, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		
		planets.add(new Planet(500, 630, 50, planets.size(), 3, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		
		planets.add(new Planet(1200, 460, 40, planets.size(), 4, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(1030, 510, 50, planets.size(), 4, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(1000, 690, 50, planets.size(), 1, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(800, 130, 60, planets.size(), 1, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		
		planets.add(new Planet(120, 120, 60, planets.size(), 1, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		planets.add(new Planet(1300, 700, 60, planets.size(), 1, 100, 20));
		planets.get(planets.size()-1).setOwner(players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		
		
		uis.add(new UI(players.get(Util.selectUiColor()).getTeam()));
//		uis.add(new UI(players.get(Color.red).getTeam()));
		scene.setUser(players.get(Config.playerColor));	// user
		
		scene.setPlanets(planets);
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));
	}
	
}
