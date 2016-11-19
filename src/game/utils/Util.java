package game.utils;

import org.newdawn.slick.Color;

public class Util {

	public static boolean compColor(Color color1, Color color2) {
		if (color1.getRed() == color2.getRed() && color1.getGreen() == color2.getGreen() && color1.getBlue() == color2.getBlue()) {
			return true;
		}
		
		return false;
	}
	
	public static Color selectUi1Color() {
		return Util.compColor(Color.green, Config.playerColor) ? Color.red : Color.green;
	}
	
	public static Color selectUi2Color() {
		return Util.compColor(Color.blue, Config.playerColor) ? Color.yellow : Color.blue;
	}
	
	public static Color selectUiColor(Color uiColor, Color replacementColor) {
		return Util.compColor(uiColor, Config.playerColor) ? replacementColor : uiColor;
	}
	
}
