package designer.game.core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import designer.game.gui.PropertiesWindow;
import game.object.Owner;
import game.object.Planet;
import game.object.PlanetI;
import game.utils.KeyWord;
import game.utils.ResourceStore;

public class DesignerCore extends BasicGame {

	PropertiesWindow pw;
	List<PlanetI> planets;
	Vector2f mousePosition;
	Vector2f tmpMousePosition;
	boolean mousePress = false;
	int planetIndex = -1;
	int planetSelected = -1;
	boolean planetMove = false;
	
	public DesignerCore(String title) {
		super(title);
		planets = new ArrayList<PlanetI>();
		pw = new PropertiesWindow(this);
		mousePosition = new Vector2f();
		tmpMousePosition = new Vector2f();
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		ResourceStore.init();
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		mousePosition.set(gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
		
		//LEFT BUTTON - add planet
		if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if (!mousePress) {
				planetIndex = -1;
				for (int i = 0; i < planets.size(); i++) {
					if (planets.get(i).checkCollision(mousePosition)) {
						planetIndex = i;
						planetSelected = i;
					}
				}
				
				if (planetIndex == -1) {
					Planet p = new Planet(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 70, planets.size(), 1, 100, 20);
					p.setOwner(new Owner(Color.gray, 0));
					planets.add(p);
					setPropertiesWindow(p);
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
				planetIndex = -1;
			}
		}
		
		// move with planet
		if (planetIndex > -1 && mousePress && planetIndex < planets.size()) {
			PlanetI p = planets.get(planetIndex);
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
		if (planets.size() > 0) {
			for (PlanetI planet : planets) {
				planet.renderMore(gc, g, true);
			}
		}
		
		if (planetSelected != -1 && planetSelected < planets.size()) {
			PlanetI p = planets.get(planetSelected);
			g.setColor(Color.red);
			g.fill(new Circle(p.getPositionX(), p.getPositionY(), 5));
		}
	}
	
	
	private void setPropertiesWindow(PlanetI p) {
		if (planetMove) {
			p.setPosition(new Vector2f(mousePosition));
		}
		pw.positionX.setText(Integer.toString(p.getPositionX()));
		pw.positionY.setText(Integer.toString(p.getPositionY()));
		pw.planetId.setText(Integer.toString(p.getId()));
	}
	
	
	public void updatePlanet(int id, KeyWord key, int value) {
		if (planetIndex != -1) {
			for (PlanetI planet : planets) {
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
					break;
				}
			}
		}
	}
}
