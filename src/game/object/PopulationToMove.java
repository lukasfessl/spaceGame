package game.object;

/**
 * 
 * @author Lukas Fessl
 *
 */
public class PopulationToMove {
	
	private Planet to;
	
	private int amount;
	
	private int timeToSent;
	
	private Owner owner;
	
	public PopulationToMove(Planet to, int amount, Owner owner) {
		this.to = to;
		this.amount = amount;
		timeToSent = 0;
		this.owner = owner;
	}
	
	
	// -- setters and getters
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Planet getTo() {
		return to;
	}

	public void setTo(Planet to) {
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
