package game.enemy;

import game.object.Planet;

/**
 * Command to attack for UI. 
 * 
 * @author Lukas Fessl
 *
 */
public class CommandToAttack {

	private Planet planetFrom;
	
	private Planet planetTo;
	
	private int timeWait;
	
	public CommandToAttack(Planet planetFrom, Planet planetTo, int timeWait) {
		this.planetFrom = planetFrom;
		this.planetTo = planetTo;
		this.timeWait = timeWait;
	}

	public Planet getPlanetFrom() {
		return planetFrom;
	}

	public void setPlanetFrom(Planet planetFrom) {
		this.planetFrom = planetFrom;
	}

	public Planet getPlanetTo() {
		return planetTo;
	}

	public void setPlanetTo(Planet planetTo) {
		this.planetTo = planetTo;
	}

	public int getTimeWait() {
		return timeWait;
	}

	public void setTimeWait(int timeWait) {
		this.timeWait = timeWait;
	}
	
}
