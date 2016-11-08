package game.gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import game.utils.Config;
import game.utils.ResourceStore;

public class Button {

	private Rectangle mask;
	private Shape button;
	
	private Rectangle mouse;
	private ActionHandler anctionHandler;
	private boolean clicked;
	private boolean hover;
	private Color border;
	private Color fill;
	private Color borderHover;
	private Color fillHover;
	private int borderSize;
	private boolean disabled;
	
	private String label;
	private Vector2f labelPosition;
	private UnicodeFont font;
	
	public Button(int x, int y, int width, int height, String label, int labelX, int labelY) {
		this.mask = new Rectangle(x, y, width, height);
		this.button = new Rectangle(x, y, width, height);
		initConstruct(label, labelX, labelY);
	}
	
	public Button(int x, int y, int width, int height, float points[], String label, int labelX, int labelY) {
		this.mask = new Rectangle(x, y, width, height);
		for(int i = 0; i < points.length; i++) {
			if (i%2 == 0) {
				points[i] += x;		
			} else {
				points[i] += y;
			}
		}
		this.button = new Polygon(points);
		initConstruct(label, labelX, labelY);
	}
	
	private void initConstruct(String label, int labelX, int labelY) {
		this.mouse = new Rectangle(0, 0, 1, 1);
		this.clicked = false;
		this.hover = false;
		this.disabled = false;
		this.label = label;
		this.border = Color.white;
		this.fill = new Color(0,0,0,0.6f);
		this.borderSize = 1;
		this.labelPosition = new Vector2f(labelX, labelY);
		try {
			font = new UnicodeFont(new Font("Calibri", Font.PLAIN ,  18));
			font.getEffects().add(new ColorEffect(java.awt.Color.white));
			font.addGlyphs(Config.alphabet);
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	public void update(GameContainer gc, int delta) throws SlickException {
		if (anctionHandler != null && !disabled) {
			mouse.setX(gc.getInput().getMouseX());
			mouse.setY(gc.getInput().getMouseY());
			if (mask.intersects(mouse)) {
				if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					if (!clicked) {
						anctionHandler.onAction();
						clicked = true;
					}
				} else if (clicked) {
					clicked = false;
				}
				if (!hover) {
					hover = true;
				}
			} else {
				if (hover) {
					hover = false;
				}
			}
		}
	}
	
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setFont(font);
		g.setLineWidth(borderSize);
		if (!hover || disabled) {
			if (fill != null) {
				g.setColor(fill);
				g.fill(this.button);
			}
			if (border != null) {
				g.setColor(border);
				g.draw(this.button);
			}
		} else if(!disabled) {
			if (fillHover != null) {
				g.setColor(fillHover);
				g.fill(this.button);
			} else if (fill != null) {
				g.setColor(fill);
				g.fill(this.button);
			}
			if (borderHover != null) {
				g.setColor(borderHover);
				g.draw(this.button);
			} else if(border != null) {
				g.setColor(border);
				g.draw(this.button);
			}
		}
		
		if (this.label != null) {
			g.drawString(ResourceStore.trans(label), button.getX() + labelPosition.getX(), button.getY() + labelPosition.getY());
		}
		
		g.resetLineWidth();
	}
	
	public void setActionHandler(ActionHandler ah) {
		anctionHandler = ah;
	}
	
	public void setColors(Color border, Color fill, Color borderHover, Color fillHover) {
		this.border = border;
		this.fill = fill;
		this.borderHover = borderHover;
		this.fillHover = fillHover;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Rectangle getMask() {
		return mask;
	}

	public void setMask(Rectangle mask) {
		this.mask = mask;
	}

	public Color getFill() {
		return fill;
	}

	public void setFill(Color fill) {
		this.fill = fill;
	}

	public Color getBorder() {
		return border;
	}

	public void setBorder(Color border) {
		this.border = border;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	
	
}
