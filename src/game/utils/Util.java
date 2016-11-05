package game.utils;

import org.newdawn.slick.Color;

import game.object.Owner;

public class Util {

	public static boolean compColor(Color color1, Color color2) {
		if (color1.getRed() == color2.getRed() && color1.getGreen() == color2.getGreen() && color1.getBlue() == color2.getBlue()) {
			return true;
		}
		
		return false;
	}
	
	public static Color selectUiColor() {
		return Util.compColor(Color.green, Config.playerColor) ? Color.red : Color.green;
	}
	
}
