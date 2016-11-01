package game.gui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class CheckboxGroup {
	
	private List<Checkbox> checkboxes;
	private boolean unique;
	
	public CheckboxGroup() {
		checkboxes = new ArrayList<Checkbox>();
		this.unique = false;
	}

	public void update(GameContainer gc) {
		for (Checkbox chbox : checkboxes) {
			if (chbox.clicked(gc)) {
				for (Checkbox chboxOther : checkboxes) {
					if (!chbox.equals(chboxOther) && this.unique) {
						chboxOther.setState(false);
					}
				}
				break;
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (Checkbox chbox : checkboxes) {
			chbox.render(gc, g);
		}
	}
	
	
	public List<Checkbox> getCheckboxes() {
		return checkboxes;
	}

	public void setCheckboxes(List<Checkbox> checkboxes) {
		this.checkboxes = checkboxes;
	}
	
	public void addCheckbox(Checkbox checkbox) {
		this.checkboxes.add(checkbox);
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	
}
