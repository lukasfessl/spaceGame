package game.object;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public interface PlanetI {

	public void render(GameContainer gc, Graphics arg1) throws SlickException;
	
	public void renderMore(GameContainer gc, Graphics g, boolean more) throws SlickException;
	
	public void init(GameContainer gc) throws SlickException;
	
	public void update(GameContainer gc, int delta) throws SlickException;
	
	public void setStartLine(GameContainer gc, int delta, Vector2f startLine) throws SlickException;
		
	public void setRadius(int radius);

	public int getRadius();
	
	public void setPosition(Vector2f position);
	
	public Vector2f getPosition();
	
	public int getPositionX();
	
	public int getPositionY();

	public int getId();

	public void setId(int id);
	
	public int getPopulation();

	public void setPopulation(int population);
	
	public void setPopulationToMove(int populationToMove);

	public List<PopulationToMove> getPopulationToMove();
	
	public void addPopulationToMove(Planet to, int amount, Owner owner);
	
	public void setOwner(Owner owner);
	
	public Owner getOwner();
	
	public Color getOwnerColor();
	
	public int getOwnerTeam();
	
	public int getPopulationMax();
	
	public void setPopulationMaxTimer(int time);
	
	public int getPopulationMaxTimer();
	
	public void setPopulationSpeedUp(int speedUp);
	
	public int getPopulationSpeedUp();
	
	public Planet clone();
	
	public boolean checkCollision(Vector2f mousePosition);
	
	public int getPopulationMaxConst();
	
	public void setPopulationMaxConst(int populationMaxConst);
	
	public int getPopulationSmallMaxConst();

	public void setPopulationSmallMaxConst(int populationSmallMaxConst);
	
	public int getPlanetType();

	public void setPlanetType(int planetType);
}
