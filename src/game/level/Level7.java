package game.level;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.CommandToAttack;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class Level7 extends AbstractLevel {
	
	public Level7(int id) {
		super();
		scene = new Scene(id);
		this.createLevel();
	}
	
	
	protected void createLevel() {
		
		planets.add(new Planet(700, 100, 35, planets.size(), 4, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(1100, 400, 30, planets.size(), 13, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);

		planets.add(new Planet(1000, 500, 35, planets.size(), 12, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(1200, 600, 40, planets.size(), 15, 100, 10));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(2);
		planets.get(planets.size()-1).setPopulation(10);
		
		
		planets.add(new Planet(350, 200, 35, planets.size(), 11, 100, 15));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		planets.add(new Planet(350, 450, 40, planets.size(), 12, 100, 15));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Color.gray));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(10);
		
		
		planets.add(new Planet(110, 410, 45, planets.size(), 14, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Config.playerColor));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(20);
		
		planets.add(new Planet(150, 110, 50, planets.size(), 2, 100, 20));
		planets.get(planets.size()-1).setOwner(ResourceStore.players.get(Util.selectUiColor()));
		planets.get(planets.size()-1).setPopulationSpeedUp(1);
		planets.get(planets.size()-1).setPopulation(50);
		
		
		UI ui1 = new UI(ResourceStore.players.get(Util.selectUiColor()).getTeam());
		ui1.addCommandToAttack(new CommandToAttack(planets.get(planets.size()-1), planets.get(planets.size()-2), 100));
		uis.add(ui1);
		scene.setUser(ResourceStore.players.get(Config.playerColor));	// user
		
		scene.setPlanets(planets);
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));
	}
	
}
