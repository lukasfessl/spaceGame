package game.level;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.CommandToAttack;
import game.enemy.UI;
import game.object.Planet;
import game.store.LevelDataStore;
import game.store.PlanetMapper;
import game.utils.Config;
import game.utils.ResourceStore;
import game.utils.Util;

public class Level {

	protected List<Planet> planets;
	protected int bcgIndex;
	protected List<UI> uis;
	protected Scene scene;

	public Level() {
		planets = new ArrayList<Planet>();
		uis = new ArrayList<UI>();
	}
	
	public Level createLevel1(int id) {
		scene = new Scene(id);
		this.loadLevel("data/level/level1.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel2(int id) {
		scene = new Scene(id);
		this.loadLevel("data/level/level2.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel3(int id) {
		scene = new Scene(id);
		this.loadLevel("data/level/level3.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel4(int id) {
		scene = new Scene(id);
		this.loadLevel("data/level/level4.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel5(int id) {
		scene = new Scene(id);
		this.loadLevel("data/level/level5.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel6(int id) {
		scene = new Scene(id);
		this.loadLevel("data/level/level6.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel7(int id) {
		scene = new Scene(id);
		this.loadLevel("data/level/level7.xml");
		initLevelMap();
		UI ui1 = uis.get(0);
		ui1.addCommandToAttack(new CommandToAttack(planets.get(planets.size()-1), planets.get(planets.size()-2), 100));
		return this;
	}
	
	public Level createLevel8(int id) {
		scene = new Scene(id);
		this.loadLevel("data/level/level8.xml");
		initLevelMap();
		return this;
	}
	
	/**
	 * Load Level from xml file from giver path
	 * @param path path to file
	 */
	private void loadLevel(String path) {
        try {
        	JAXBContext jc = JAXBContext.newInstance(LevelDataStore.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
			LevelDataStore lds = (LevelDataStore) unmarshaller.unmarshal(new File(path));
			this.bcgIndex = lds.getBcgIndex();
			this.planets = PlanetMapper.planetDataStoreToPlanet(lds.getPlanets());
		} catch (JAXBException e) {
			JOptionPane.showMessageDialog(null, "Level se nepodařilo načíst\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Transform local data to {@link Scene} specific for level
	 */
	private void initLevelMap() {
		Color tmpPlayerColor = null;
		for (Planet planet : planets) { 
			if (planet.getOwnerTeam() == 1) {
				tmpPlayerColor = planet.getOwnerColor();
				break;
			}
		}
		
		if (tmpPlayerColor != null) {
			for (Planet planet : planets) {
				if (planet.getOwnerTeam() == 1) {
					scene.setUser(ResourceStore.players.get(Config.playerColor));
					planet.setOwner(ResourceStore.players.get(Config.playerColor));
				} else if (planet.getOwnerTeam() > 1) {
					planet.setOwner(ResourceStore.players.get(Util.selectUiColor()));
					uis.add(new UI(ResourceStore.players.get(Util.selectUiColor()).getTeam()));
				}
			}
		}
		
		scene.setUIs(uis);
		scene.setPlanets(planets);
		scene.setBackground(ResourceStore.backgrounds.get(this.bcgIndex));		
	}
	
	/**
	 * Transform local data to {@link Scene} 
	 */
	private void initGeneralMap() {
		Color tmpPlayerColor = null;
		for (Planet planet : planets) { 
			if (planet.getOwnerTeam() == 1) {
				tmpPlayerColor = planet.getOwnerColor();
				break;
			}
		}
		
		if (tmpPlayerColor != null) {
			for (Planet planet : planets) {
				if (planet.getOwnerTeam() == 1) {
					scene.setUser(ResourceStore.players.get(Config.playerColor));
					planet.setOwner(ResourceStore.players.get(Config.playerColor));
				} else if (planet.getOwnerTeam() > 1) {
					planet.setOwner(ResourceStore.players.get(Util.selectUiColor(planet.getOwnerColor(), tmpPlayerColor)));
					uis.add(new UI(ResourceStore.players.get(Util.selectUiColor(planet.getOwnerColor(), tmpPlayerColor)).getTeam()));
				}
			}
		}
		
		scene.setUIs(uis);
		scene.setPlanets(planets);
		scene.setBackground(ResourceStore.backgrounds.get(this.bcgIndex));
	}
	
	
	// -- setters and getters
	
	public Scene getScene() {
		return this.scene;
	}

}
