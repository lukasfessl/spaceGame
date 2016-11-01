package designer.game.gui;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import designer.game.core.DesignerCore;
import game.utils.KeyWord;

public class PropertiesWindow extends JFrame {

	public TextField positionX;
	public TextField positionY;
	public TextField planetId;
	public DesignerCore dc;
	
	public PropertiesWindow(DesignerCore dc) {
		this.dc = dc;
		this.setSize(250, 600);
		this.setTitle("Properties");
		this.setVisible(true);
		initGui();
	}
	
	private void initGui() {
		this.setLayout(null);
		JPanel layout = new JPanel();
		layout.setBackground(Color.red);
		layout.setSize(200, 400);
		this.add(layout);
		
		
		positionX = new TextField(20);
		positionX.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(planetId.getText(), KeyWord.POSITION_X, positionX.getText());
			}
		});
		layout.add(positionX);
		
		
		positionY = new TextField(20);
		positionY.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(planetId.getText(), KeyWord.POSITION_Y, positionY.getText());
			}
		});
		layout.add(positionY);
		
		planetId = new TextField(20);
		layout.add(planetId);
		
		layout.updateUI();
	}
	
	
	private void updatePlanet(String planetId, KeyWord key, String value) {
		dc.updatePlanet(Integer.parseInt(planetId), key, Integer.parseInt(value));
	}
	
	public void clearData() {
		planetId.setText("");
		positionX.setText("");
		positionY.setText("");
	}
	
}
