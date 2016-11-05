package game.store;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="planet")
@XmlAccessorType (XmlAccessType.FIELD)
public class PlanetDataStore {

	private int id;
	private int positionX;
	private int positionY;
	private int radius;
	
	private int speedUp;
	private int population;
	private int populationMaxConst;
	private int populationSmallMaxConst;
	
	private int planetType;
	private int owner;	// -1 neutral, 0 player, >= 1 cpu

	public PlanetDataStore() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getSpeedUp() {
		return speedUp;
	}

	public void setSpeedUp(int speedUp) {
		this.speedUp = speedUp;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getPopulationMaxConst() {
		return populationMaxConst;
	}

	public void setPopulationMaxConst(int populationMaxConst) {
		this.populationMaxConst = populationMaxConst;
	}

	public int getPopulationSmallMaxConst() {
		return populationSmallMaxConst;
	}

	public void setPopulationSmallMaxConst(int populationSmallMaxConst) {
		this.populationSmallMaxConst = populationSmallMaxConst;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getPlanetType() {
		return planetType;
	}

	public void setPlanetType(int planetType) {
		this.planetType = planetType;
	}
	
	
	
}
