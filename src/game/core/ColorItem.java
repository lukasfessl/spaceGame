package game.core;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * @author Lukas Fessl
 *
 */
public class ColorItem {

	private Rectangle rectangle;
	private Color color;
	
	public ColorItem(float x, float y, int width, int height, Color color) {
		this.rectangle = new Rectangle(x, y, width, height);
		this.color = color;
	}
	
	
	// -- setters and getters
	
	public int getX() {
		return (int)this.rectangle.getX();
	}
	
	public int getY() {
		return (int)this.rectangle.getY();
	}
	
	public int getWidth() {
		return (int)this.rectangle.getWidth();
	}
	
	public int getHeight() {
		return (int)this.rectangle.getHeight();
	}
	
	public Color getColor() {
		return this.color;
	}

	public Rectangle getRect() {
		return this.rectangle;
	}
}
