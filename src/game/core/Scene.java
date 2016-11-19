package game.core;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import game.enemy.UI;
import game.enemy.UI1;
import game.object.Explosion;
import game.object.Owner;
import game.object.Planet;
import game.object.PopulationToMove;
import game.object.Ship;
import game.utils.Config;
import game.utils.GamePosition;
import game.utils.ResourceStore;
import game.utils.ScreenManager;

/**
 * 
 * @author Lukas Fessl
 *
 */
public class Scene {

	private int playerId = 1;
	private Owner player = null;
	private Image bcg;
	private int levelId;
	
	List<Planet> planets = new ArrayList<Planet>();
	List<Ship> ships = new ArrayList<Ship>();
	List<Ship> shipsToRemove = new ArrayList<Ship>();
	List<Explosion> explosions = new ArrayList<Explosion>();
	List<UI> uis = new ArrayList<UI>();
	
	private Planet startPlanet;
	private Vector2f mousePosition = new Vector2f();
	private boolean mousePress = false;
	private int gameStateTimer = 0;
	private boolean lose = false;
	private boolean win = false;
	
	public Scene(int levelId) {
		this.levelId = levelId;
	}

	public void init(GameContainer gc) throws SlickException {

	}
	

	public void update(GameContainer gc, int delta) throws SlickException {
		mousePosition.set(gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
		for (Planet planet : planets) {
			planet.update(gc, delta);	
			
			// add ships to travel
			if (planet.getPopulationToMove().size() != 0 ) {
				for (PopulationToMove populationToMove : planet.getPopulationToMove()) {
					if (populationToMove.getTimeToSent() == 0) {
						ships.add(new Ship(planet, populationToMove.getTo(), populationToMove.getOwner()));
						populationToMove.setTimeToSent(7);
						populationToMove.setAmount(populationToMove.getAmount() - 1);
					}
					populationToMove.setTimeToSent(populationToMove.getTimeToSent() - 1);
					
					if (populationToMove.getAmount() <= 0) {
						planet.getPopulationToMove().remove(populationToMove);
						break;
					}
				}

			}
			
			// ship reach planet
			for (Ship ship : ships) {
				if (ship.getCountOfSteps() - 10 <= ship.getCurrentStep()) {
					if (planet.getId() == ship.getTo().getId()) {
						if (planet.getOwnerTeam() == ship.getOwnerTeam()) {
							planet.setPopulation(planet.getPopulation() + 1);
						} else {
							planet.setPopulation(planet.getPopulation() - 1);
							if (planet.getPopulation() <= 0) {
								planet.setPopulation(0);
								planet.setOwner(ship.getOwner());
							}
						}
						shipsToRemove.add(ship);
						if (planet.getOwnerTeam() != ship.getOwnerTeam()) {
							explosions.add(new Explosion(ship.getCurrentPossition()));
						}
					}
				}
			}
		}
				
		for (Ship ship : ships) {
			ship.update(gc, delta);
		}
		
		// collision with enemy Ship // todo fix me
		for (Ship ship : ships) {
			for (Ship shipX : ships) {
				if (ship.getOwnerTeam() != shipX.getOwnerTeam() && ship.getCurrentPossition().distance(shipX.getCurrentPossition()) < 10) {
					shipsToRemove.add(ship);
					explosions.add(new Explosion(ship.getCurrentPossition()));
				}
			}
		}
		
		for (Ship ship : shipsToRemove) {
			ships.remove(ship);
		}

		for (Explosion explosion : explosions) {
			explosion.update(gc, delta);
			if (explosion.readyToDelete()) {
				explosions.remove(explosion);
				break;
			}
		}
		
		// mouse action
		if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			for (Planet planet : planets) {
				// select from planet
				if (planet.checkCollision(mousePosition) && !mousePress && planet.getOwnerTeam() == playerId) {
//				if (planet.checkCollision(mousePosition) && !mousePress) {
					startPlanet = planet;
//					System.out.println(planet.getPosition().toString());
				} else {
				}
			}
			mousePress = true;
		//release
		} else {
			if (mousePress) {
				mousePress = false;
				for (Planet planet : planets) {
					if (planet.checkCollision(mousePosition)) {
						// if planet from was selected
						if (startPlanet != null && startPlanet.getId() != planet.getId()) {
							planets.get(startPlanet.getId()).addPopulationToMove(planet, planets.get(startPlanet.getId()).getPopulation() / 2, planets.get(startPlanet.getId()).getOwner());
							planets.get(startPlanet.getId()).setPopulation(
									planets.get(startPlanet.getId()).getPopulation() - planets.get(startPlanet.getId()).getPopulation() / 2);
						}
					}
				}
				startPlanet = null;					
			}
		}

		for (UI ui: uis) {
			ui.update(planets);
		}	
		
		if (!win && !lose ) {
			win = checkVictory();
			lose = checkLose();
//			lose = true;
//			win = true;
		}

		if (win || lose) {
			gameStateTimer++;
			if (gameStateTimer > 200) {
				if (ScreenManager.gamePosition.toString().startsWith(GamePosition.GAME_LEVEL.toString())) {
					ScreenManager.gamePosition = GamePosition.MENU_CAMPAIGN_SELECT;
					if (win) {
						ScreenManager.tmpPosition = GamePosition.FINISHED_LEVEL;
					} else {
						ScreenManager.tmpPosition = GamePosition.MENU_CAMPAIGN_SELECT;
					}
				} else {
					ScreenManager.tmpPosition = GamePosition.MENU_MAIN;
				}
			}
		}
	}

	
	// check if player win
	private boolean checkVictory() {
		boolean victory = false;
		for (Planet planet : planets) {
			if (planet.getOwnerTeam() == playerId) {
				victory = true;
			} else if (planet.getOwnerTeam() != 0) {	// check against gray - neutral
				victory = false;
				break;
			}
		}

		if (victory) {
			for (Ship ship : ships) {
				if (ship.getOwnerTeam() == playerId) {
					victory = true;
				} else {
					victory = false;
					break;
				}
			}
		}
		return victory;
	}
	
	// check if player lost
	private boolean checkLose() {
		boolean lostGame = false;
		for (Planet planet : planets) {
			if (planet.getOwnerTeam() == playerId) {
				lostGame = false;
				break;
			} else if (planet.getOwnerTeam() != 0) {	// check against gray - neutral
				lostGame = true;
			}
		}

		if (lostGame) {
			for (Ship ship : ships) {
				if (ship.getOwnerTeam() == playerId) {
					lostGame = false;
					break;
				} else {
					lostGame = true;
				}
			}
		}
		return lostGame;
	}
	
	
	
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setAntiAlias(true);
		if (bcg != null) {
			g.drawImage(bcg, 0, 0, 1600, 1226, 0, 0, 1600, 1226);
		}
		
		for (Planet planet : planets) {
			planet.render(gc, g);
		}
		
		for (Ship ship : ships) {
			ship.render(gc, g);
		}
		
		for (Explosion explosion : explosions) {
			explosion.render(gc, g);
		}
		
		if (startPlanet != null) {
			g.setColor(startPlanet.getOwnerColor());
			g.setLineWidth(2);
			g.drawLine(startPlanet.getPositionX(), startPlanet.getPositionY(),gc.getInput().getMouseX(), gc.getInput().getMouseY());
			g.setLineWidth(4);
			g.setColor(new Color(startPlanet.getOwnerColor().r, startPlanet.getOwnerColor().g, startPlanet.getOwnerColor().b, 0.3f));
			g.drawLine(startPlanet.getPositionX(), startPlanet.getPositionY(),gc.getInput().getMouseX(), gc.getInput().getMouseY());

			
			//			int distance = 0;
//			while (startPlanet.getPosition().distance(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY())) > distance) {
//				double angle = Math.asin((gc.getInput().getMouseX() - startPlanet.getPositionX())/
//						(startPlanet.getPosition().distance(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY()))));
//				angle = Math.toDegrees(angle);
//				if (gc.getInput().getMouseY() >= startPlanet.getPositionY() && gc.getInput().getMouseX() >= startPlanet.getPositionX()) {
//					angle = 90 + (90 - angle);
//				} else if (gc.getInput().getMouseY() >= startPlanet.getPositionY() && gc.getInput().getMouseX() <= startPlanet.getPositionX()) {
//					angle = 180  - angle;
//				} else if (gc.getInput().getMouseY() <= startPlanet.getPositionY() && gc.getInput().getMouseX() <= startPlanet.getPositionX()) {
//					angle = 270 + (90 + angle);
//				}
//				
//				
//				System.out.println((angle));
////				System.out.println((yDistance));
//				
//				float xDistance = (float) (Math.cos(Math.toRadians(angle - 90))*(selectOffset - 20 + distance)) + startPlanet.getPositionX();
//				float yDistance = (float) (Math.sin(Math.toRadians(angle - 90))*(selectOffset - 20 + distance)) + startPlanet.getPositionY();
//				float xDistance2 = (float) (Math.cos(Math.toRadians(angle - 90))*selectOffset) + startPlanet.getPositionX();
//				float yDistance2 = (float) (Math.sin(Math.toRadians(angle - 90))*selectOffset) + startPlanet.getPositionY();
//				g.drawLine(xDistance, yDistance,xDistance2, yDistance2);
//				distance += 20;
//			}
//			
//			
//			
//			if (startPlanet.getPosition().distance(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY())) < selectOffset) {
//				selectOffset = 0;
//			}
		}
		
		// victory message
		if (win || lose) {
			g.setColor(player.getColor());
			String state = ResourceStore.trans("victory");
			if (lose) {
				state = ResourceStore.trans("lost");
			}
			g.setColor(new Color(0, 0, 0, 0.7f));
			g.fillRect(0f, Config.windowHeight/3f, Config.windowWidth, Config.windowHeight/3);
			g.setColor(Color.white);
			g.setFont(ResourceStore.fonts.get("big"));
			g.drawString(state, Config.windowWidth/2 - 75, Config.windowHeight/2 - 18);
		}
	}

	
	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
	
	public void setUIs(List<UI> uis) {
		this.uis = uis;
	}
	
	public void setUser(Owner player) {
		this.playerId = player.getTeam();
		this.player = player;
	}

	public void setBackground(Image bcg) {
		this.bcg = bcg;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	

}
