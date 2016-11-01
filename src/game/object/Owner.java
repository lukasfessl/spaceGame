package game.object;

import org.newdawn.slick.Color;

public class Owner {

	private Color color;
	
	private int team;
	
	public Owner (Color color, int team) {
		this.color = color;
		this.team = team;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
	
	public Owner clone() {
		return new Owner(new Color(color.r, color.g, color.b), team);
	}
	
}
