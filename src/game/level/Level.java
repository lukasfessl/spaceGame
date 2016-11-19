package game.level;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.newdawn.slick.Color;

import game.core.Scene;
import game.enemy.CommandToAttack;
import game.enemy.UI;
import game.enemy.UI1;
import game.enemy.UI2;
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
		this.loadLevel(ResourceStore.levelPath + "level1.xml");
		initLevelMap();
		uis.get(0).setAgression(2);
		return this;
	}
	
	public Level createLevel2(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level2.xml");
		uis.get(0).setAgression(2);
		initLevelMap();
		return this;
	}
	
	public Level createLevel3(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level3.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel4(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level4.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel5(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level5.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel6(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level6.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel7(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level7.xml");
		initLevelMap();
		UI ui1 = uis.get(0);
		ui1.addCommandToAttack(new CommandToAttack(planets.get(planets.size()-1), planets.get(planets.size()-2), 100));
		return this;
	}
	
	public Level createLevel8(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level8.xml");
		initLevelMap();
//		uis.clear();
//		UI ui = new UI2(ResourceStore.players.get(Color.green));
//		uis.add(ui);
		return this;
	}
	
	public Level createLevel9(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level9.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel10(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level10.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel11(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level11.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel12(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level12.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel13(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level13.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel14(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level14.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel15(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level15.xml");
		initLevelMap();
		return this;
	}
	
	public Level createLevel16(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level16.xml");
		initLevelMap();
		return this;
	}
	
	public Level createTest(int id) {
		scene = new Scene(id);
		this.loadLevel(ResourceStore.levelPath + "level9.xml");
		initLevelMap();
//		uis.clear();
//		UI ui = new UI2(ResourceStore.players.get(Color.green));
//		uis.add(ui);
//		UI ui2 = new UI2(ResourceStore.players.get(Color.red));
//		uis.add(ui2);
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
			JOptionPane.showMessageDialog(null, "Level was not found\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
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
					if (planet.getOwnerTeam() <= 3) {
						planet.setOwner(ResourceStore.players.get(Util.selectUi1Color()));
						uis.add(new UI1(ResourceStore.players.get(Util.selectUi1Color())));
					} else {
						planet.setOwner(ResourceStore.players.get(Util.selectUi2Color()));
						uis.add(new UI2(ResourceStore.players.get(Util.selectUi2Color())));
					}
				}
			}
		}
		
		scene.setUIs(uis);
		scene.setPlanets(planets);
		scene.setBackground(ResourceStore.images.get("bcg_" + this.bcgIndex));
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
					uis.add(new UI1(ResourceStore.players.get(Util.selectUiColor(planet.getOwnerColor(), tmpPlayerColor))));
				}
			}
		}
		
		scene.setUIs(uis);
		scene.setPlanets(planets);
		scene.setBackground(ResourceStore.images.get("bcg_" + this.bcgIndex));		
	}
	
	
	// -- setters and getters
	
	public Scene getScene() {
		return this.scene;
	}

}
