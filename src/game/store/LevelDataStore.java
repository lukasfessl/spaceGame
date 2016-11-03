package game.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class LevelDataStore implements Serializable {

	@XmlElement(name = "planets")
	private List<PlanetDataStore> planets;
	private int bcgIndex;
	
	public LevelDataStore() {
		planets = new ArrayList<PlanetDataStore>();
	}
	
	public LevelDataStore(List<PlanetDataStore> planets, int bcgIndex) {
		this.planets = planets;
		this.bcgIndex = bcgIndex;
	}

	public List<PlanetDataStore> getPlanets() {
		return planets;
	}

	public void setPlanets(List<PlanetDataStore> planets) {
		this.planets = planets;
	}

	public int getBcgIndex() {
		return bcgIndex;
	}

	public void setBcgIndex(int bcgIndex) {
		this.bcgIndex = bcgIndex;
	}

	
}
