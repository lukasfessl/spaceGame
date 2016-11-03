package game.enemy;

import java.util.ArrayList;
import java.util.List;

import game.object.Planet;
import game.utils.Config;

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
							// defaultne se nastavi prvni nepratelskou planeta
							if (smallestDistance == -1) {
								smallestDistance = planet.getPosition().distance(planetEnemy.getPosition());
								planetTo = planetEnemy;
							} else {
								// hledam nejblizsi planetu
								if (planet.getPosition().distance(planetEnemy.getPosition()) < smallestDistance) {
									// pokud ma nepratelska planeta malou populaci, vybere se a uz se nic dalsiho nedela
									if (planetEnemy.getPopulation() < planet.getPopulation()/2) {
										smallestDistance = planet.getPosition().distance(planetEnemy.getPosition());
										planetTo = planetEnemy;
										if (Config.rs.nextInt(10) == 2) {
											break;
										}
									// hledam nejblizsi planetu	
									} else {
										smallestDistance = planet.getPosition().distance(planetEnemy.getPosition());
										planetTo = planetEnemy;
									}
								}
							}
						// pokud je blizka planeta s malou velikosti populace poslu posily
						} else {
							if (planetEnemy.getPopulation() < 10 && planet.getPosition().distance(planetEnemy.getPosition()) < 300 && planet.getPopulation() > 15) {
								planetTo = planetEnemy;
								break;
							}
						}
					}
					
					// pokud je vybrana planeta
					if (planetFrom != null && planetTo != null && planetFrom.getId() != planetTo.getId()) {
						// napadnu planetu s malou populaci poslu hodne
						if (planetTo.getPopulation() <= Config.rs.nextInt(10) && planetFrom.getPopulation() > planetFrom.getPopulationMax()/6) {
							planetFrom.addPopulationToMove(planetTo, (int)(planetFrom.getPopulation()/1.1), planets.get(planetFrom.getId()).getOwner());
							planetFrom.setPopulation(planetFrom.getPopulation() - (int)(planetFrom.getPopulation()/1.1));
						// poslu standardni utok
						} else {
							if (planetFrom.getPopulation() > planetFrom.getPopulationMax()/(Config.rs.nextInt(2)+2)) {
								// jednou za cas poslu veci utok
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
		
//		for(Planet planet : planets) {
//			for(Planet planetx : planets) {
//				
//				if (planet.getOwnerTeam() != planetx.getOwnerTeam() && planet.getOwnerTeam() == playerId) {
//				
//					if (smallestDistance == 0) {
//						smallestDistance = planet.getPosition().distance(planetx.getPosition());
//					} else {
//						if (planet.getPosition().distance(planetx.getPosition()) < smallestDistance) {
//							smallestDistance = planet.getPosition().distance(planetx.getPosition());
//							PlanetdFrom = planet.getId();
//							PlanetdTo = planetx.getId();
//						}
//					}
//				}
//			}
//		}	
//		
//		if (planets.get(PlanetdFrom).getPopulation() > planets.get(PlanetdFrom).getPopulationMax()/4) {
//			planets.get(PlanetdFrom).addPopulationToMove(planets.get(PlanetdTo), planets.get(PlanetdFrom).getPopulation()/4);
//			planets.get(PlanetdFrom).setPopulation(planets.get(PlanetdFrom).getPopulation() - planets.get(PlanetdFrom).getPopulation()/4);
//		}
		}
	}
	
	public void addCommandToAttack(CommandToAttack cta) {
		this.cta.add(cta);
	}
}
