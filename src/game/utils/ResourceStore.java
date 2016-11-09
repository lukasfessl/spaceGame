package game.utils;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import game.object.Owner;
import game.trans.Lang;

public class ResourceStore {
	
	public static String imgPath = "data/resource/";
	public static String soundPath = "data/sound/";
	
	public static int progress = 0;
	public static boolean loaded = false;
		
	public static Image planets;
	public static ArrayList<int[]> planetPositionOnImage;
	
	public static Image ship;
	
	public static List<Image> backgrounds;
	
	public static HashMap<String, Image> items;
	public static HashMap<String, Music> music;
	public static HashMap<String, Sound> sound;
	public static Music currentMusic;
	
	public static HashMap<String, UnicodeFont> fonts;
	private static String alphabet = "_abcdefghijklmnopqrstuvwxyzěščřžýáíéúůňďťABCDEFGHIJKLMNOPQRSTUVWXYZĚŠČŘŽÝÁÍÉ1234567890";
	
	public static HashMap<Color, Owner> players;
	
	public static ResourceBundle tr;
	public static Lang lang = Lang.ENGLISH;
	
	public static void init() {
		backgrounds = new ArrayList<Image>();
		items = new HashMap<String, Image>();
		music = new HashMap<String, Music>();
		sound = new HashMap<String, Sound>();
		fonts = new HashMap<String, UnicodeFont>();
		players = new HashMap<Color, Owner>();
		
		try {
			// images
			initImage();
			// sound
			initSound();
			// translation
			initTranslation();
			// fonts
			initFont();
			
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
	
	private static void initImage() throws SlickException {
		planets = new Image(imgPath + "planets3.png");
		ship = new Image(imgPath + "ship.png");
		backgrounds.add(new Image(imgPath + "bcg01.png"));
		backgrounds.add(new Image(imgPath + "bcg01.jpg"));
		items.put("check" ,new Image(imgPath + "check.png"));
		items.put("lock", new Image(imgPath + "lock.png"));
	}
	
	private static void initSound() throws SlickException {
		sound.put("click", new Sound(soundPath + "click3.wav"));
		music.put("menuSound", new Music(soundPath + "daybreak.ogg"));
	}
	
	public static void initTranslation() {
		if (lang == Lang.CZECH) {
			tr = ResourceBundle.getBundle("game.trans.Messages_cs");
		} else if (lang == Lang.ENGLISH) {
			tr = ResourceBundle.getBundle("game.trans.Messages_en");
		}		
	}
	
	public static String trans(String key) {
		try {
			return tr.getString(key);
		} catch (Exception e) {
			return key;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void initFont() {
		try {
			UnicodeFont font = new UnicodeFont(new Font("Calibri", Font.PLAIN, 18));
			font.getEffects().add(new ColorEffect(java.awt.Color.white));
			font.addGlyphs(alphabet);
			font.loadGlyphs();
			fonts.put("classic", font);
			
			UnicodeFont fontB = new UnicodeFont(new Font("Calibri", Font.PLAIN, 36));
			fontB.getEffects().add(new ColorEffect(java.awt.Color.white));
			fontB.addGlyphs(alphabet);
			fontB.loadGlyphs();
			fonts.put("big", fontB);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
