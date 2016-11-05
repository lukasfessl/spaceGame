package game.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.object.Owner;
import game.store.SettingsDataStore;

public class Config {

	
	public static int windowWidth = 1366;
	
	public static int windowHeight = 768;
	
	// random ship position
	public static Random rs = new Random();
	
	public static int unlockedLevel = 1;
	
	public static boolean manualyLevelUnlock = false;
	
	public static boolean fullscreen = false;
	
	public static boolean sound = true;
		
	public static Color playerColor = Color.blue;

	public static String alphabet = "abcdefghijklmnopqrstuvwxyzěščřžýáíéúůňďťABCDEFGHIJKLMNOPQRSTUVWXYZĚŠČŘŽÝÁÍÉ1234567890";

	
	public static void save() {
		File f = new File("data");
		if (!f.exists()) {
			f.mkdirs();
		}
		
		SettingsDataStore ds = new SettingsDataStore();
		ds.put("fullscreen", fullscreen);
		ds.put("sound", sound);
		ds.put("unlockedLevel", unlockedLevel);
		ds.put("playerColor", playerColor.getRed()+","+playerColor.getGreen()+","+playerColor.getBlue());
		try {
			FileOutputStream fos = new FileOutputStream("data/user_settings.conf");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ds);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		File f = new File("data/user_settings.conf");
		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				SettingsDataStore ds = (SettingsDataStore)ois.readObject();
				fullscreen = Boolean.parseBoolean(ds.get("fullscreen"));
				sound = Boolean.parseBoolean(ds.get("sound"));
				String color[] = ds.get("playerColor").split(",");
				playerColor = new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]));
				unlockedLevel = Integer.parseInt(ds.get("unlockedLevel"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
