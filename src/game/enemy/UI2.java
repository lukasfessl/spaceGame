package game.enemy;

import java.util.ArrayList;
import java.util.List;

import game.object.Owner;
import game.object.Planet;
import game.utils.Config;
import game.utils.Util;

/**
 * UI v2 implementation
 * 
 * @author Lukas Fessl
 *
 */
public class UI2 implements UI {

	private Owner player;
	
	private float smallestDistance = Config.windowWidth;
	private int currentTimeWait = 0;  
	private Planet planetFrom = null;
	private Planet planetTo = null;
	private String uiName = "UI 1";
	
	List<CommandToAttack> cta = new ArrayList<CommandToAttack>();
	
	public UI2(Owner owner) {
		this.player = owner;
	}
	
	public void update(List<Planet> planets) {
		
		 // Search all planets belongs to UI
		for(Planet planetUI : planets) {
			if (Util.compColor(planetUI.getOwnerColor(), player.getColor())) {

				planetFrom = planetUI;
				 // Search all planets does not belongs to UI
				for(Planet planet : planets) {
					// IF enemy
					if (!Util.compColor(planet.getOwnerColor(), player.getColor()) && planet.getOwnerTeam() != player.getTeam()) {
						int distanceBetweenPlanets = (int) planetFrom.getPosition().distance(planet.getPosition());
						
						if (smallestDistance > distanceBetweenPlanets) {
							if (planetFrom.getPopulation() > planet.getPopulation() / 2 ) {
								smallestDistance = distanceBetweenPlanets;
								planetTo = planet;
							}
						}
					// IF my planet	
					} else if(Util.compColor(planet.getOwnerColor(), player.getColor()) && planet.getOwnerTeam() == player.getTeam() && planetFrom.getId() != planet.getId()) {
						if (planetFrom.getPosition().distance(planet.getPosition()) < 300 && planet.getPopulation() < 5) {
							planetTo = planet;
							break;
						}
					}
				}
				
				
				
				if (planetFrom != null && planetTo != null) {
					if ((planetFrom.getPopulation() > planetFrom.getPopulationMax() / (Config.rs.nextInt(4) + 1)) || 
							((planetTo.getPopulation() < planetTo.getPopulationMaxConst() / 15) && (planetFrom.getPopulation() > planetFrom.getPopulationMaxConst() / 10 ) && (planetFrom.getPopulationToMove().size() < 5))) {
						sendShips(planets);
					}
				}
				
				
				planetFrom = null;
				planetTo = null;
				smallestDistance = Config.windowWidth;
			}
		}

	}
	
	private void sendShips(List<Planet> planets) {
		if (planetFrom != null || planetTo != null) {
			planetFrom.addPopulationToMove(planetTo, planetFrom.getPopulation()/2, planets.get(planetFrom.getId()).getOwner());
			planetFrom.setPopulation(planetFrom.getPopulation() - planetFrom.getPopulation()/2);
		}
	}
	
	public void setAgression(int agression) {

	}
	
	public void addCommandToAttack(CommandToAttack cta) {
		this.cta.add(cta);
	}
	
	@Override
	public String getUIName() {
		return this.uiName;
	}
}
