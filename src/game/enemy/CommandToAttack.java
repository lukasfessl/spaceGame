package game.enemy;

import game.object.PlanetI;

public class CommandToAttack {

	private PlanetI planetFrom;
	
	private PlanetI planetTo;
	
	private int timeWait;
	
	public CommandToAttack(PlanetI planetFrom, PlanetI planetTo, int timeWait) {
		this.planetFrom = planetFrom;
		this.planetTo = planetTo;
		this.timeWait = timeWait;
	}

	public PlanetI getPlanetFrom() {
		return planetFrom;
	}

	public void setPlanetFrom(PlanetI planetFrom) {
		this.planetFrom = planetFrom;
	}

	public PlanetI getPlanetTo() {
		return planetTo;
	}

	public void setPlanetTo(PlanetI planetTo) {
		this.planetTo = planetTo;
	}

	public int getTimeWait() {
		return timeWait;
	}

	public void setTimeWait(int timeWait) {
		this.timeWait = timeWait;
	}
	
}
