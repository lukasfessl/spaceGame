package game.utils;

import java.io.Serializable;
import java.util.HashMap;

public class DataStore implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String> data;
	
	public DataStore() {
		this.data = new HashMap<String, String>();
	}
	
	public void put(String key, String value) {
		this.data.put(key, value);
	}
	
	public void put(String key, int value) {
		this.data.put(key, Integer.toString(value));
	}
	
	public void put(String key, boolean value) {
		this.data.put(key, Boolean.toString(value));
	}
	
	public String get(String key) {
		return this.data.get(key);
	}
	
	
}
