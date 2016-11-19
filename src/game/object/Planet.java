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

import game.utils.ResourceStore;

/**
 * 
 * @author Lukas Fessl
 *
 */
public class Planet implements PlanetI {
	
	protected int populationCurrenTimer;
	protected int populationMaxTimer;
	protected int populationMax;
	protected int speedUp;
	protected Vector2f position;
	protected int radius;
	protected int id;
	protected int population;
	protected List<PopulationToMove> populationToMove;
	protected Owner owner;
	protected int populationMaxConst;	// max population
	protected int populationSmallMaxConst; // max population for empty player (default player 0, gray)
	
	private float selectOpacity;
	
	
	private Image image;
	private int planetType;
	
	public Planet(int positionX, int positionY, int radius, int id, int planetType, int populationMaxConst, int populationSmallMaxConst) {
		position = new Vector2f(positionX, positionY);
		this.radius = radius;
		this.id = id;
		this.populationToMove = new ArrayList<PopulationToMove>();
		this.population = 15;
		// population max timer and speed up are dependent
		this.populationMaxTimer = 1000;
		this.speedUp = 1;
		this.populationCurrenTimer = 0;
		image = ResourceStore.images.get("planets");
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
	
	public boolean checkCollision(Vector2f mousePosition) {
		if (position.distance(mousePosition) < radius) {
			return true;
		}
		return false;
	}
	
	public void addPopulationToMove(Planet to, int amount, Owner owner) {
		boolean wasSet = false;
		for (PopulationToMove populationToMove : populationToMove) {
			// If already ships was sent and ships which was send are same as I want send so join
			if (populationToMove.getTo().getId() == to.getId() && populationToMove.getOwner().getTeam() == owner.getTeam()) {
				populationToMove.setAmount(populationToMove.getAmount() + amount);
				wasSet = true;
				break;
			}
		}
		
		if (!wasSet) {
			this.populationToMove.add(new PopulationToMove(to, amount, owner));
		}
	}
	
	
	// -- setters and getters
	
	public int getPopulationMax() {
		return populationMax;
	}
	
	public void setPopulationMaxTimer(int time) {
		this.populationMaxTimer = time;
	}
	
	public int getPopulationMaxTimer() {
		return this.populationMaxTimer;
	}
	
	public void setPopulationSpeedUp(int speedUp) {
		this.populationMaxTimer = (int)(1000f/(float)speedUp);
		this.speedUp = speedUp;
	}
	
	public int getPopulationSpeedUp() {
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

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getRadius() {
		return this.radius;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getPosition() {
		return this.position;
	}
	
	public int getPositionX() {
		return (int) this.position.getX();
	}
	
	public int getPositionY() {
		return (int) this.position.getY();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public List<PopulationToMove> getPopulationToMove() {
		return populationToMove;
	}

	public void setPopulationToMove(int populationToMove) {

	}

	public Color getOwnerColor() {
		return owner.getColor();
	}

	public int getOwnerTeam() {
		return owner.getTeam();
	}

	public Owner getOwner() {
		return owner.clone();
	}
	
	public int getPopulationMaxConst() {
		return populationMaxConst;
	}

	public void setPopulationMaxConst(int populationMaxConst) {
		this.populationMaxConst = populationMaxConst;
		this.populationMax = this.populationMaxConst;
	}

	public int getPopulationSmallMaxConst() {
		return populationSmallMaxConst;
	}

	public void setPopulationSmallMaxConst(int populationSmallMaxConst) {
		this.populationSmallMaxConst = populationSmallMaxConst;
		this.populationMax = this.populationSmallMaxConst;
	}

	@Override
	public void setStartLine(GameContainer gc, int delta, Vector2f startLine) throws SlickException {
		// TODO Auto-generated method stub
	}

	public int getPlanetType() {
		return planetType;
	}

	public void setPlanetType(int planetType) {
		this.planetType = planetType;
	}
	
	
}
