package game.object;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import game.utils.Config;
import game.utils.ResourceStore;

/**
 * Draw explosion of ship
 * 
 * @author Lukas Fessl
 *
 */
public class Explosion {

	private Vector2f position;
	private float explosionRadius;
	private float opacity;
	private boolean readyToDelete;
	
	private Sound explosion;
	
	public Explosion(Vector2f position) {
		this.position = position;
		explosionRadius = 0;
		opacity = 1;
		readyToDelete = false;
		if (Config.sound) {
//			explosion = ResourceStore.sound.get("explosion");
//			explosion.play();
		}
	}

	public void update(GameContainer gc, int delta) {
		explosionRadius += 0.3;
		if (explosionRadius > 5) {
			opacity -= 0.1;
			if (opacity <= 0.2) {
				readyToDelete = true;
			}
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setLineWidth(10);
		g.setColor(new Color(Color.orange.r, Color.orange.g, Color.orange.b, opacity));
		g.draw(new Circle(position.getX(), position.getY(), explosionRadius));
		g.setColor(new Color(Color.red.r, Color.red.g, Color.red.b, opacity/2));
		g.draw(new Circle(position.getX(), position.getY(), explosionRadius/2));
		g.setColor(new Color(Color.magenta.r, Color.magenta.g, Color.magenta.b, opacity/6));
		g.draw(new Circle(position.getX(), position.getY(), explosionRadius/4));
	}


	// -- setters and getters
	
	public float getExplosionRadius() {
		return this.explosionRadius;
	}
	
	public boolean readyToDelete() {
		return this.readyToDelete;
	}
}
