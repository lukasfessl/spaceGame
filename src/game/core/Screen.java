package game.core;

/**
 * 
 * @author Lukas Fessl
 *
 */
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import game.gui.Button;

public class Screen {

	private List<Button> buttons;
	
	public Screen() {
		buttons = new ArrayList<Button>();
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		for (Button button : buttons) {
			button.update(gc, delta);
		}
	}
	
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (Button button : buttons) {
			button.render(gc, g);
		}
	}
	
	public void addButton(Button button) {
		this.buttons.add(button);
	}
	
	public List<Button> getButtons() {
		return this.buttons;
	}
	
	public void removeButton(int index) {
		this.buttons.remove(index);
	}
	
}
