package game.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.object.Owner;

public class ResourceStore {
	
	public static Image planets;
	public static ArrayList<int[]> planetPositionOnImage;
	
	public static Image ship;
	
	public static List<Image> backgrounds;
	
	public static HashMap<String, Image> items;

	public static int progress = 0;
	public static boolean loaded = false;
	
	public static HashMap<Color, Owner> players;
	
	public static void init() {
		backgrounds = new ArrayList<Image>();
		items = new HashMap<String, Image>();
		players = new HashMap<Color, Owner>();
		try {
			planets = new Image("resource/planets3.png");
			ship = new Image("resource/ship.png");
			backgrounds.add(new Image("resource/bcg01.png"));
			backgrounds.add(new Image("resource/bcg01.jpg"));
			items.put("check" ,new Image("resource/check.png"));
			items.put("lock", new Image("resource/lock.png"));
			progress = 50;

			planetPositionOnImage = new ArrayList<int[]>();
			
			planetPositionOnImage.add(new int[]{0, 0, 250, 250});
			planetPositionOnImage.add(new int[]{250, 0, 500, 250});
			planetPositionOnImage.add(new int[]{500, 0, 750, 250});
			planetPositionOnImage.add(new int[]{750, 0, 1000, 250});
			
			planetPositionOnImage.add(new int[]{0, 250, 250, 500});
			planetPositionOnImage.add(new int[]{250, 250, 500, 500});
			planetPositionOnImage.add(new int[]{500, 250, 750, 500});
			planetPositionOnImage.add(new int[]{750, 250, 1000, 500});
			
			planetPositionOnImage.add(new int[]{0, 500, 250, 750});
			planetPositionOnImage.add(new int[]{250, 500, 500, 750});
			planetPositionOnImage.add(new int[]{500, 500, 750, 750});
			planetPositionOnImage.add(new int[]{750, 500, 1000, 750});
			
			planetPositionOnImage.add(new int[]{0, 750, 250, 1000});
			planetPositionOnImage.add(new int[]{250, 750, 500, 1000});
			planetPositionOnImage.add(new int[]{500, 750, 750, 1000});
			planetPositionOnImage.add(new int[]{750, 750, 1000, 1000});
			
			players.put(Color.gray, new Owner(Color.gray, 0));
			players.put(Color.blue,new Owner(Color.blue, 1));
			players.put(Color.red, new Owner(Color.red, 2));
			players.put(Color.green, new Owner(Color.green, 3));
			players.put(Color.yellow,new Owner(Color.yellow, 4));
			players.put(Color.orange, new Owner(Color.orange, 5));
			players.put(Color.pink, new Owner(Color.pink, 6));
			
			progress = 100;
			loaded = true;
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
