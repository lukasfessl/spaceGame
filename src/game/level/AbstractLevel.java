package game.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.UI;
import game.object.Owner;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;

public abstract class AbstractLevel {

	protected HashMap<Color, Owner> players;
	protected List<Planet> planets;
	protected List<UI> uis;
	protected Scene scene;

	public AbstractLevel() {
		players = new HashMap<Color, Owner>();
		planets = new ArrayList<Planet>();
		uis = new ArrayList<UI>();
		initPlayers();
	}
	
	protected void initPlayers() {
		players.put(Color.gray, new Owner(Color.gray, 0));
		players.put(Color.blue,new Owner(Color.blue, 1));
		players.put(Color.red, new Owner(Color.red, 2));
		players.put(Color.green, new Owner(Color.green, 3));
		players.put(Color.yellow,new Owner(Color.yellow, 4));
		players.put(Color.orange, new Owner(Color.orange, 5));
		players.put(Color.pink, new Owner(Color.pink, 6));
//		players.put(Color.magenta, new Owner(new Color(110, 165, 91), 7)); // for campaign UI
	}
	
	abstract protected void createLevel();

	public Scene getScene() {
		return this.scene;
	}
	
	
	// TODO: not working
	public Scene cloneScene() {
		scene = new Scene(-1);
		scene.setUser(players.get(Config.playerColor));	// user
		
		List<Planet> p = new ArrayList<>();
		for (Planet pl : planets) {
			p.add(pl.clone());
		}
		scene.setPlanets(p);
		
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.backgrounds.get(0));
		return scene;
	}
}
