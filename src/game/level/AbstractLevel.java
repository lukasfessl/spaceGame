package game.level;

import java.util.ArrayList;
import java.util.List;

import game.core.Scene;
import game.enemy.UI;
import game.object.Planet;
import game.utils.Config;
import game.utils.ResourceStore;

public abstract class AbstractLevel {

	protected List<Planet> planets;
	protected List<UI> uis;
	protected Scene scene;

	public AbstractLevel() {
		planets = new ArrayList<Planet>();
		uis = new ArrayList<UI>();
	}
		
	abstract protected void createLevel();

	public Scene getScene() {
		return this.scene;
	}
	
	
	// TODO: not working
	public Scene cloneScene() {
		scene = new Scene(-1);
		scene.setUser(ResourceStore.players.get(Config.playerColor));	// user
		
		List<Planet> p = new ArrayList<>();
		for (Planet pl : planets) {
			p.add(pl.clone());
		}
		scene.setPlanets(p);
		
		scene.setUIs(uis);
		scene.setBackground(ResourceStore.images.get("bcg_0"));
		return scene;
	}
}
