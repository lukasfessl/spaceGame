package game.utils;

import java.awt.Font;
import java.io.IOException;
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
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

import game.object.Owner;
import game.trans.Lang;

public class ResourceStore {
	
	public static int progress = 0;
	
	public static String imgPath = "resource/img/";
	public static String soundPath = "resource/sound/";
	public static String levelPath = "resource/level/";

	public static HashMap<String, Image> images = new HashMap<String, Image>();
	public static ArrayList<int[]> planetPositionOnImage = new ArrayList<int[]>();
	
	public static HashMap<String, Music> music = new HashMap<String, Music>();
	public static HashMap<String, Sound> sound = new HashMap<String, Sound>();
	public static Music currentMusic;
	
	public static HashMap<String, UnicodeFont> fonts = new HashMap<String, UnicodeFont>();
	private static String alphabet = "_abcdefghijklmnopqrstuvwxyzěščřžýáíéúůňďťABCDEFGHIJKLMNOPQRSTUVWXYZĚŠČŘŽÝÁÍÉ1234567890";
	
	public static HashMap<Color, Owner> players = new HashMap<Color, Owner>();
	
	public static ResourceBundle tr;
	public static List<Lang> languages = new ArrayList<Lang>();
	public static HashMap<Lang, String> repositories = new HashMap<Lang, String>();
	
	public static void init(boolean soundInit) {
		// translation
		initTranslation();
		// fonts
		initFont();
		// init planets position in image
		initPlanetPosition();
		// init players
		initPlayers();
		// init image
		initImage();
		// init sound
		if (soundInit) {
			initSound();
		}
		progress = 100;
	}
	
	public static void initImage() {
		try {
			images.put("check" ,new Image(imgPath + "check.png"));
			images.put("lock", new Image(imgPath + "lock.png"));
			images.put("logo", new Image(imgPath + "logo.png"));
			images.put("ship", new Image(imgPath + "ship.png"));
			images.put("planets", new Image(imgPath + "planets3.png"));
			
			images.put("bcg_0", new Image(imgPath + "background_0.jpg"));
			images.put("bcg_1", new Image(imgPath + "background_1.jpg"));
			images.put("bcg_2", new Image(imgPath + "background_2.jpg"));
			images.put("bcg_3", new Image(imgPath + "background_3.jpg"));
			images.put("bcg_4", new Image(imgPath + "background_4.jpg"));
			images.put("bcg_5", new Image(imgPath + "background_5.jpg"));
			images.put("bcg_6", new Image(imgPath + "background_6.jpg"));
			images.put("bcg_7", new Image(imgPath + "background_7.jpg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public static void initSound() {
		try {
			sound.put("click", new Sound(soundPath + "click3.wav"));
			sound.put("explosion", new Sound(soundPath + "explosion1.wav"));
			music.put("menuSound", new Music(soundPath + "daybreak.ogg"));

			music.put("gameMusic1", new Music(soundPath + "di-evantile_one-click-blues.ogg"));
			music.put("gameMusic2", new Music(soundPath + "ironhorse.ogg"));

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void initTranslation() {
		languages.add(Lang.ENGLISH);
		repositories.put(Lang.ENGLISH, "game.trans.Messages_en");
		languages.add(Lang.CZECH);
		repositories.put(Lang.CZECH, "game.trans.Messages_cs");

		initResourceBundle(Config.currentLangIndex);	
	}
	
	public static void initResourceBundle(int currentLangIndex) {
		tr = ResourceBundle.getBundle(repositories.get(languages.get(currentLangIndex)));	
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
	
	public static void initPlanetPosition() {	
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
	}
	
	public static void initPlayers() {
		players.put(Color.gray, new Owner(Color.gray, 0));
		players.put(Color.blue,new Owner(Color.blue, 1));
		players.put(Color.red, new Owner(Color.red, 2));
		players.put(Color.green, new Owner(Color.green, 3));
		players.put(Color.yellow,new Owner(Color.yellow, 4));
		players.put(Color.orange, new Owner(Color.orange, 5));
		players.put(Color.pink, new Owner(Color.pink, 6));
	}
}
