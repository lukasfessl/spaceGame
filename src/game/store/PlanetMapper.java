package game.store;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import game.object.Owner;
import game.object.Planet;

public class PlanetMapper {

	
	public static List<PlanetDataStore> planetToPlanetDataStore(List<Planet> planets) {
		List<PlanetDataStore> pds = new ArrayList<PlanetDataStore>();
		
		for (Planet planet : planets) {	
			PlanetDataStore planetDataStore = new PlanetDataStore();
			planetDataStore.setPositionX(planet.getPositionX());
			planetDataStore.setPositionY(planet.getPositionY());
			planetDataStore.setRadius(planet.getRadius());
			
			planetDataStore.setPopulation(planet.getPopulation());
			planetDataStore.setSpeedUp(planet.getPopulationSpeedUp());
			planetDataStore.setPopulationMaxConst(planet.getPopulationMaxConst());
			planetDataStore.setPopulationSmallMaxConst(planet.getPopulationSmallMaxConst());
			
			planetDataStore.setPlanetType(planet.getPlanetType());
			planetDataStore.setOwner(-1); // TODO:
			
			pds.add(planetDataStore);
		}
		
		return pds;
	}
	
	
	public static List<Planet> planetDataStoreToPlanet(List<PlanetDataStore> pds) {
		List<Planet> planets = new ArrayList<Planet>();
		for (PlanetDataStore ds : pds) {	
			Planet p = new Planet(ds.getPositionX(), ds.getPositionY(), ds.getRadius(), planets.size(), ds.getPlanetType(), 
					ds.getPopulationMaxConst(), ds.getPopulationSmallMaxConst());
			p.setPopulationSpeedUp(ds.getSpeedUp());
			p.setPopulation(ds.getPopulation());
			p.setOwner(new Owner(Color.gray, 0));
			planets.add(p);
		}
	
		return planets;
	}
}
