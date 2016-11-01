package game.object;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import game.utils.Config;
import game.utils.ResourceStore;

public class Ship {

	private PlanetI from;
	private PlanetI to;
	private Vector2f currentPossition;
	private Owner owner;
	
	// step size x and y
	private Vector2f sizeStep;
	private float countOfSteps;
	private int currentStep = 0;
	private Image image;
	private int size = 16;

	public Ship(PlanetI from, PlanetI to, Owner owner) {
		this.from = from;
		this.to = to;
		this.owner = owner;
		
		// 2 is length of step
		countOfSteps = from.getPosition().distance(to.getPosition()) / 2;
		sizeStep = new Vector2f((to.getPositionX() - from.getPositionX()) / countOfSteps, (to.getPositionY() - from.getPositionY()) / countOfSteps);
		currentPossition = new Vector2f(from.getPositionX() + Config.rs.nextInt(10), from.getPositionY() + Config.rs.nextInt(10));
		
		// ship does not start from center but from border of the planet
		currentPossition.set(currentPossition.getX() + sizeStep.getX() * (from.getRadius() / 4), currentPossition.getY() + sizeStep.getY() * (from.getRadius() / 4));
		currentStep = (from.getRadius() / 4) + (to.getRadius() / 4); 
		
		image = ResourceStore.ship.copy(); 
		double degrees = Math.acos(((float)to.getPositionX() - (float)from.getPositionX()) / (from.getPosition().distance(to.getPosition())));

		if ((float)to.getPositionY() < (float)from.getPositionY()) {
			image.setRotation(-(float)Math.toDegrees(degrees) + 90);
		}
		else { 
			image.setRotation((float)Math.toDegrees(degrees) + 90);
		}
	}
	

	public void update(GameContainer gc, int delta) throws SlickException {
		currentPossition.set(currentPossition.getX() + sizeStep.getX(), currentPossition.getY() + sizeStep.getY());	
		currentStep++;
		image.setCenterOfRotation(size / 2, size / 2);
	}

	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (owner != null) {
			g.setColor(owner.getColor());
		}

		// sometimes is no set center
		if (image.getCenterOfRotationX() != 100) {
			g.drawImage(image, currentPossition.getX() - size / 2, currentPossition.getY() - size / 2, 
						currentPossition.getX() + size / 2 ,currentPossition.getY() + size / 2 , 0, 0, 200, 200, owner.getColor());
		}
	}

	public Vector2f getCurrentPossition() {
		return currentPossition;
	}

	public void setCurrentPossition(Vector2f currentPossition) {
		this.currentPossition = currentPossition;
	}

	public float getCountOfSteps() {
		return countOfSteps;
	}

	public void setCountOfSteps(float countOfSteps) {
		this.countOfSteps = countOfSteps;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public PlanetI getFrom() {
		return from;
	}

	public void setFrom(PlanetI from) {
		this.from = from;
	}

	public PlanetI getTo() {
		return to;
	}

	public void setTo(PlanetI to) {
		this.to = to;
	}

	public Vector2f getSizeStep() {
		return sizeStep;
	}

	public void setSizeStep(Vector2f sizeStep) {
		this.sizeStep = sizeStep;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public Owner getOwner() {
		return this.owner;
	}
	
	public int getOwnerTeam() {
		return owner.getTeam();
	}
	
	public Color getOwnerColor() {
		return owner.getColor();
	}
	
	
}
