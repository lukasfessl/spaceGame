package game.object;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import game.utils.Config;
import game.utils.ResourceStore;

public class Planet extends AbstractPlanet{
	
	private int populationCurrenTimer;
	private int populationMaxTimer;
	private int populationMax;
	private float speedUp;
	private final int populationMaxConst;	// max population
	private final int populationSmallMaxConst; // max population for empty player (default player 0, gray)
	private float selectOpacity;
	
	private Image image;
	private int planetType;
	
	public Planet(int postionX, int positionY, int radius, int id, int planetType, int populationMaxConst, int populationSmallMaxConst) {
		super(postionX, positionY, radius, id);
		this.population = 15;
		// population max timer and speed up are dependent
		this.populationMaxTimer = 1000;
		this.speedUp = 1;
		this.populationCurrenTimer = 0;
		image = ResourceStore.planets;	
		this.planetType = planetType;
		this.populationMaxConst = populationMaxConst;
		this.populationSmallMaxConst = populationSmallMaxConst;
		selectOpacity = 0.4f;
	}
	
	public Planet clone() {
		Planet p = new Planet((int)position.getX(), (int)position.getY(), radius, id, planetType,
				populationMaxConst, populationSmallMaxConst);
		p.setOwner(this.getOwner());
		return p;
	}
	
	public void init(GameContainer gc) throws SlickException {
	
	}
	

	public void update(GameContainer gc, int delta) {
		populationCurrenTimer += delta;
		if (populationMaxTimer < populationCurrenTimer) {
			populationCurrenTimer = 0;
			if (population < populationMax) {
				population++;
			}
		}
		
		// mouse hover to planet
		if (position.distance(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY())) < radius) {
			selectOpacity = 0.7f;
		} else {
			selectOpacity = 0.4f;
		}
	}


	public void render(GameContainer gc, Graphics g) throws SlickException {
		// mouse hover to planet
		g.setColor(new Color(owner.getColor().r, owner.getColor().g, owner.getColor().b, selectOpacity));
		
		g.fill(new Circle(position.getX(), position.getY(), radius+5, 55));
		drawPlanet(g);
		g.setColor(owner.getColor());
		g.drawString(population + "/"+ populationMax, position.getX() - radius - 10, getPositionY() - 30 - radius);
		g.drawString("+" + (int)speedUp, position.getX() - radius - 10, getPositionY() - 10 - radius);
		g.drawString("TOS:"+(this.getPopulationToMove().size() > 0 ? this.getPopulationToMove().get(0).getAmount() : 0), 
					position.getX() - radius + 60, getPositionY() - 30 - radius);
	}

	
	private void drawPlanet(Graphics g) {
		if (ResourceStore.planetPositionOnImage.size() >= planetType + 1) {
			int position[] = ResourceStore.planetPositionOnImage.get(planetType);
			g.drawImage(image, getPositionX() - radius, getPositionY() - radius, 
					getPosition().getX() + radius, getPosition().getY() + radius, 
					position[0], position[1], position[2], position[3]);
		}
	}
	
	public void renderMore(GameContainer gc, Graphics g, boolean more) throws SlickException {
		render(gc, g);
		if (more) {
			
		}
	}
	
	public int getPopulationMax() {
		return populationMax;
	}
	
	public void setPopulationMaxTimer(int time) {
		this.populationMaxTimer = time;
	}
	
	public int getPopulationMaxTimer() {
		return this.populationMaxTimer;
	}
	
	public void setPopulationSpeedUp(float speedUp) {
		this.populationMaxTimer = (int)(1000f/speedUp);
		this.speedUp = speedUp;
	}
	
	public float getPopulationSpeedUp() {
		return this.speedUp;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
		if (owner.getTeam() == 0) {
			populationMax = populationSmallMaxConst;
		} else {
			populationMax = populationMaxConst;
		}
	}


}
