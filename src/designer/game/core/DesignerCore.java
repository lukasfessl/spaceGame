package designer.game.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import designer.game.gui.PropertiesWindow;
import game.object.Owner;
import game.object.Planet;
import game.object.Planet;
import game.store.LevelDataStore;
import game.store.PlanetDataStore;
import game.store.PlanetMapper;
import game.utils.Config;
import game.utils.KeyWord;
import game.utils.ResourceStore;



public class DesignerCore extends BasicGame {

	private PropertiesWindow pw;
	private List<Planet> planets;
	private	Image bcg;
	private int bcgIndex;
	private Vector2f mousePosition;
	private Vector2f tmpMousePosition;
	private boolean mousePress = false;
	private int Planetndex = -1;
	private int planetSelected = -1;
	private boolean planetMove = false;
	
	public DesignerCore(String title) {
		super(title);
		this.planets = new ArrayList<Planet>();
		this.pw = new PropertiesWindow(this);
		this.mousePosition = new Vector2f();
		this.tmpMousePosition = new Vector2f();
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		ResourceStore.init();
		this.bcgIndex = 0;
		this.bcg = ResourceStore.backgrounds.get(bcgIndex);
		pw.textFieldBackground.setText(Integer.toString(bcgIndex));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		mousePosition.set(gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
		
		//LEFT BUTTON - add planet
		if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if (!mousePress) {
				Planetndex = -1;
				for (int i = 0; i < planets.size(); i++) {
					if (planets.get(i).checkCollision(mousePosition)) {
						Planetndex = i;
						planetSelected = i;
					}
				}
				
				if (Planetndex == -1) {
					Planet p = new Planet(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 70, planets.size(), 1, 100, 20);
					p.setOwner(new Owner(Color.gray, 0));
					planets.add(p);
					setPropertiesWindow(p);
					planetSelected = planets.size() - 1;
				} else {
					tmpMousePosition.set(gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
				}
			}
			mousePress = true;
		// release	
		} else {
			if (mousePress) {
				mousePress = false;
				planetMove = false;
				Planetndex = -1;
			}
		}
		
		// move with planet
		if (Planetndex > -1 && mousePress && Planetndex < planets.size()) {
			Planet p = planets.get(Planetndex);
			if (tmpMousePosition.distance(mousePosition) > 10 && planetMove == false) {
				planetMove = true;
			}
			setPropertiesWindow(p);
		}
		
		
		// RIGHT BUTTON - REMOVE PLANET
		if (gc.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			//remove
			for (int i = planets.size() - 1; i >= 0; i--) {
				if (planets.get(i).checkCollision(mousePosition)) {
					planets.remove(i);
					break;
				}
			}
			// reindex
			for (int i = 0; i < planets.size(); i++) {
				planets.get(i).setId(i);
			}
			//update data to select planet
			if (planetSelected != -1 && planetSelected < planets.size()) {
				setPropertiesWindow(planets.get(planetSelected));
			} else {
				pw.clearData();
			}

		}	
	}


	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (bcg != null) {
			g.drawImage(bcg, 0, 0, 1600, 1226, 0, 0, 1600, 1226);
		}
		
		if (planets.size() > 0) {
			for (Planet planet : planets) {
				planet.renderMore(gc, g, true);
			}
		}
		
		if (planetSelected != -1 && planetSelected < planets.size()) {
			Planet p = planets.get(planetSelected);
			g.setColor(Color.red);
			g.fill(new Circle(p.getPositionX(), p.getPositionY(), 5));
		}
	}
	
	
	private void setPropertiesWindow(Planet p) {
		if (planetMove) {
			p.setPosition(new Vector2f(mousePosition));
		}
		pw.textFieldX.setText(Integer.toString(p.getPositionX()));
		pw.textFieldY.setText(Integer.toString(p.getPositionY()));
		pw.textFieldId.setText(Integer.toString(p.getId()));
		pw.textFieldRadius.setText(Integer.toString(p.getRadius()));
		pw.textFieldSpeedUp.setText(Integer.toString(p.getPopulationSpeedUp()));
		pw.textFieldPopulation.setText(Integer.toString(p.getPopulation()));
		pw.textFieldPopulationMax.setText(Integer.toString(p.getPopulationMaxConst()));
		pw.textFieldPopulationSmallMax.setText(Integer.toString(p.getPopulationSmallMaxConst()));
		pw.textFieldType.setText(Integer.toString(p.getPlanetType()));
	}
	
	
	public void updatePlanet(int id, KeyWord key, int value) {
		if (planetSelected != -1) {
			for (Planet planet : planets) {
				if (planet.getId() == id) {
					if (key == KeyWord.POSITION_X) {
						planet.setPosition(new Vector2f(value, planet.getPositionY()));
					}
					if (key == KeyWord.POSITION_Y) {
						planet.setPosition(new Vector2f(planet.getPositionX(), value));
					}
					if (key == KeyWord.SIZE) {
						planet.setRadius(value);
					}
					if (key == KeyWord.TYPE) {
						planet.setPlanetType(value);
					}
					if (key == KeyWord.SPEED_UP) {
						planet.setPopulationSpeedUp(value);
					}
					if (key == KeyWord.POPULATION_SMALL_MAX) {
						planet.setPopulationSmallMaxConst(value);
					}
					if (key == KeyWord.POPULATION_MAX) {
						planet.setPopulationMaxConst(value);
					}
					if (key == KeyWord.POPULATION) {
						planet.setPopulation(value);
					}
					break;
				}
			}
		}
	}
	
	public void update(KeyWord key, int value) {
		if (key == KeyWord.BACKGROUND &&  ResourceStore.backgrounds.size() > value) {
			this.bcg = ResourceStore.backgrounds.get(value);
			this.bcgIndex = value;
		}
	}
	
	public LevelDataStore getLevelDataStore() {
		LevelDataStore lds = new LevelDataStore();
		lds.setPlanets(PlanetMapper.planetToPlanetDataStore(this.planets));
		lds.setBcgIndex(this.bcgIndex);
		return lds;
	}
	
	public void loadLevelDataStore(LevelDataStore lds) {
		this.planets = PlanetMapper.planetDataStoreToPlanet(lds.getPlanets());
		this.update(KeyWord.BACKGROUND, lds.getBcgIndex());
		pw.textFieldBackground.setText(Integer.toString(lds.getBcgIndex()));
	}
}
