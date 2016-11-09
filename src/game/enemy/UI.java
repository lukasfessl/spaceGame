package game.enemy;

import java.util.ArrayList;
import java.util.List;

import game.object.Planet;
import game.utils.Config;

/**
 * UI implementation
 * 
 * @author Lukas Fessl
 *
 */
public class UI {

	private int playerId;
	
	private float smallestDistance = -1;
	private int currentTimeWait = 0;  
	Planet planetFrom = null;
	Planet planetTo = null;
	
	List<CommandToAttack> cta = new ArrayList<CommandToAttack>();
	
	public UI(int id) {
		this.playerId = id;
	}
	
	public void update(List<Planet> planets) {

		if (!cta.isEmpty() && currentTimeWait > cta.get(0).getTimeWait()) {
			cta.get(0).getPlanetFrom().addPopulationToMove(cta.get(0).getPlanetTo(), (int)(cta.get(0).getPlanetFrom().getPopulation()/2), planets.get(cta.get(0).getPlanetFrom().getId()).getOwner());
			cta.get(0).getPlanetFrom().setPopulation(cta.get(0).getPlanetFrom().getPopulation() - (int)(cta.get(0).getPlanetFrom().getPopulation()/2));
			cta.clear();
		} else {
			currentTimeWait++;
		}

		if (cta.isEmpty()) {
		
			for(Planet planet : planets) {
				if (planet.getOwnerTeam() == playerId) {
					
					planetFrom = planet;
					for(Planet planetEnemy : planets) {
						if (planetEnemy.getOwnerTeam() != playerId) {
							// default select first enemy planet
							if (smallestDistance == -1) {
								smallestDistance = planet.getPosition().distance(planetEnemy.getPosition());
								planetTo = planetEnemy;
							} else {
								// looking for nearest planet
								if (planet.getPosition().distance(planetEnemy.getPosition()) < smallestDistance) {
									// pokud ma nepratelska planeta malou populaci, vybere se a uz se nic dalsiho nedela
									if (planetEnemy.getPopulation() < planet.getPopulation()/2) {
										smallestDistance = planet.getPosition().distance(planetEnemy.getPosition());
										planetTo = planetEnemy;
										if (Config.rs.nextInt(10) == 2) {
											break;
										}
									// looking for nearest planet
									} else {
										smallestDistance = planet.getPosition().distance(planetEnemy.getPosition());
										planetTo = planetEnemy;
									}
								}
							}
						// When my planet have small population send backup
						} else {
							if (planetEnemy.getPopulation() < 5 && planet.getPosition().distance(planetEnemy.getPosition()) < 300 && planet.getPopulation() > 15) {
								planetTo = planetEnemy;
								break;
							}
						}
					}
					
					// When planet is selected
					if (planetFrom != null && planetTo != null && planetFrom.getId() != planetTo.getId()) {
						// Attack planet with small population
						if (planetTo.getPopulation() <= Config.rs.nextInt(10) && planetFrom.getPopulation() > planetFrom.getPopulationMax()/6) {
							planetFrom.addPopulationToMove(planetTo, (int)(planetFrom.getPopulation()/1.1), planets.get(planetFrom.getId()).getOwner());
							planetFrom.setPopulation(planetFrom.getPopulation() - (int)(planetFrom.getPopulation()/1.1));
						// Send standard attack
						} else {
							if (planetFrom.getPopulation() > planetFrom.getPopulationMax()/(Config.rs.nextInt(3)+2)) {
								// Some time send larger attack
								if (Config.rs.nextInt(10) == 8) {
									planetFrom.addPopulationToMove(planetTo, (int)(planetFrom.getPopulation()/1.1), planets.get(planetFrom.getId()).getOwner());
									planetFrom.setPopulation(planetFrom.getPopulation() - (int)(planetFrom.getPopulation()/1.1));
								} else {
									planetFrom.addPopulationToMove(planetTo, planetFrom.getPopulation()/2, planets.get(planetFrom.getId()).getOwner());
									planetFrom.setPopulation(planetFrom.getPopulation() - planetFrom.getPopulation()/2);
								}
							}
						}
					}
	
					
					smallestDistance = -1;
					planetFrom = null;
					planetTo = null;
				}
			}
		}
	}
	
	public void addCommandToAttack(CommandToAttack cta) {
		this.cta.add(cta);
	}
}
