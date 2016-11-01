package game.gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import game.utils.Config;

public class Checkbox {

	private boolean state;
	private Vector2f position;
	private boolean mousePress;
	private String label;
	private Rectangle mask;
	private Rectangle mouseMask;
	private UnicodeFont font;
	private Color color;
	
	public Checkbox(Vector2f position, String label, Color color) {
		this.color = color;
		this.state = false;
		this.label = label;
		this.position = position;
		this.mask = new Rectangle(position.getX(), position.getY(), 25, 25);
		this.mouseMask = new Rectangle(0, 0, 1, 1);
		this.mousePress = false;
		try {
			font = new UnicodeFont(new Font("Calibri", Font.PLAIN ,  18));
			font.getEffects().add(new ColorEffect(java.awt.Color.white));
			font.addGlyphs(Config.alphabet);
			font.loadGlyphs();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean clicked(GameContainer gc) {
		mouseMask.setX(gc.getInput().getMouseX());
		mouseMask.setY(gc.getInput().getMouseY());
		
		if (mask.intersects(mouseMask)) {
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				if (!this.mousePress) {
					state = !state;
					System.out.println("testt");
					mousePress = true;
					return true;
				}
			} else {
				if (this.mousePress) {
					this.mousePress = false;
				}
			}
		}
		return false;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setFont(font);
		g.setColor(Color.black);
		g.fill(new Rectangle(mask.getX(), mask.getY(), mask.getWidth(), mask.getHeight()));
		g.setColor(Color.white);
		g.drawRect(mask.getX(), mask.getY(), mask.getWidth(), mask.getHeight());
		
		
		if (this.state) {
			g.drawString("x", mask.getCenterX() - 3, mask.getCenterY() - 10);
		}
		if (this.label != null) {
			g.drawString(label, mask.getX() + 10 + mask.getWidth(), mask.getY() + 5);
		}
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	
}
