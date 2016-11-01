package game.object;

public class PopulationToMove {
	
	private PlanetI to;
	
	private int amount;
	
	private int timeToSent;
	
	private Owner owner;
	
	public PopulationToMove(PlanetI to, int amount, Owner owner) {
		this.to = to;
		this.amount = amount;
		timeToSent = 0;
		this.owner = owner;
	}
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public PlanetI getTo() {
		return to;
	}

	public void setTo(PlanetI to) {
		this.to = to;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTimeToSent() {
		return timeToSent;
	}

	public void setTimeToSent(int timeToSent) {
		this.timeToSent = timeToSent;
	}
	
	
}
