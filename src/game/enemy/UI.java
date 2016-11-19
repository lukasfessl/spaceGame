package game.enemy;

import java.util.List;

import game.object.Planet;

public interface UI {

	public void update(List<Planet> planets);
	
	public void addCommandToAttack(CommandToAttack cta);
	
	public void setAgression(int agression);
	
	public String getUIName();
}
