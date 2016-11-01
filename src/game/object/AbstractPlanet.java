package game.object;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class AbstractPlanet implements PlanetI {

	protected Vector2f position;
	protected int radius;
	protected int id;
	protected int population;
	protected List<PopulationToMove> populationToMove;
	protected Owner owner;

	public AbstractPlanet(int positionX, int positionY, int radius, int id) {
		position = new Vector2f(positionX, positionY);
		this.radius = radius;
		this.id = id;
		this.populationToMove = new ArrayList<PopulationToMove>();

	}


	@Override
	public void render(GameContainer gc, Graphics arg1) throws SlickException {		
	}

	@Override
	public void renderMore(GameContainer gc, Graphics g, boolean more) throws SlickException {
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {	
	}

	@Override
	public void update(GameContainer gc, int delta) {
	}

	@Override
	public boolean checkCollision(Vector2f mousePosition) {
		if (position.distance(mousePosition) < radius) {
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public int getRadius() {
		return this.radius;
	}


	@Override
	public void setStartLine(GameContainer gc, int delta, Vector2f startLine) throws SlickException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setPosition(Vector2f position) {
		this.position = position;
	}


	@Override
	public Vector2f getPosition() {
		return this.position;
	}
	
	@Override
	public int getPositionX() {
		return (int) this.position.getX();
	}
	
	@Override
	public int getPositionY() {
		return (int) this.position.getY();
	}


	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getPopulation() {
		return population;
	}

	@Override
	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public List<PopulationToMove> getPopulationToMove() {
		return populationToMove;
	}
	
	@Override
	public void addPopulationToMove(PlanetI to, int amount, Owner owner) {
		boolean wasSet = false;
		for (PopulationToMove populationToMove : populationToMove) {
			// if already ships was sent and ships which was send are same as I want send so join
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


	@Override
	public void setPopulationToMove(int populationToMove) {

	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	@Override
	public Color getOwnerColor() {
		return owner.getColor();
	}


	@Override
	public int getOwnerTeam() {
		return owner.getTeam();
	}


	@Override
	public Owner getOwner() {
		return owner.clone();
	}
	
	@Override
	public int getPopulationMax() {
		return 0;
	}


	@Override
	public void setPopulationMaxTimer(int time) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getPopulationMaxTimer() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setPopulationSpeedUp(float speedUp) {
		
	}
	
	@Override
	public float getPopulationSpeedUp() {
		return 0;
	}
	
	@Override
	public Planet clone() {
		return null;
	}
	
}
