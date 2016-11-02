package designer.game.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import designer.game.core.DesignerCore;
import game.utils.KeyWord;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PropertiesWindow extends JFrame {

	private JPanel contentPane;
	public JTextField textFieldId;
	public JTextField textFieldY;
	public JTextField textFieldX;
	public JTextField textFieldPopulation;
	public JTextField textFieldRadius;
	public JTextField textFieldSpeedUp;
	public JTextField textFieldPopulationSmallMax;
	public JTextField textFieldPopulationMax;

	private DesignerCore dc;
	
	/**
	 * Create the frame.
	 */
	public PropertiesWindow(DesignerCore dc) {
		this.dc = dc;
		setTitle("Properties");
		setVisible(true);
		
		init();
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 495);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItemSave = new JMenuItem("Uložit");
		menuItemSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("released");
			}
		});
		mnNewMenu.add(menuItemSave);
		
		JMenuItem menuItemLoad = new JMenuItem("Načíst");
		mnNewMenu.add(menuItemLoad);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel labelId = new JLabel("Id:");
		labelId.setBounds(10, 12, 46, 14);
		panel.add(labelId);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(76, 9, 86, 20);
		panel.add(textFieldId);
		
		textFieldY = new JTextField();
		textFieldY.setColumns(10);
		textFieldY.setBounds(76, 68, 86, 20);
		textFieldY.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(textFieldId.getText(), KeyWord.POSITION_Y, textFieldY.getText());
			}
		});
		
		textFieldX = new JTextField();
		textFieldX.setColumns(10);
		textFieldX.setBounds(76, 40, 86, 20);
		textFieldX.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(textFieldId.getText(), KeyWord.POSITION_X, textFieldX.getText());
			}
		});
		
		JLabel labelX = new JLabel("X:");
		labelX.setBounds(10, 40, 46, 14);
		panel.add(labelX);
		panel.add(textFieldX);
		
		JLabel labelY = new JLabel("Y:");
		labelY.setBounds(10, 71, 46, 14);
		panel.add(labelY);
		panel.add(textFieldY);
		
		textFieldPopulation = new JTextField();
		textFieldPopulation.setColumns(10);
		textFieldPopulation.setBounds(76, 130, 86, 20);
		textFieldPopulation.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION, textFieldPopulation.getText());
			}
		});
		
		textFieldRadius = new JTextField();
		textFieldRadius.setColumns(10);
		textFieldRadius.setBounds(76, 99, 86, 20);
		textFieldRadius.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(textFieldId.getText(), KeyWord.SIZE, textFieldRadius.getText());
			}
		});
		
		JLabel labelRadius = new JLabel("Velikost");
		labelRadius.setBounds(10, 102, 46, 14);
		panel.add(labelRadius);
		panel.add(textFieldRadius);
		
		JLabel labelPopulation = new JLabel("Populace:");
		labelPopulation.setBounds(10, 133, 56, 14);
		panel.add(labelPopulation);
		panel.add(textFieldPopulation);
		
		JLabel labelSpeedUp = new JLabel("Přirůstek:");
		labelSpeedUp.setBounds(10, 164, 56, 14);
		panel.add(labelSpeedUp);
		
		textFieldSpeedUp = new JTextField();
		textFieldSpeedUp.setColumns(10);
		textFieldSpeedUp.setBounds(76, 161, 86, 20);
		textFieldSpeedUp.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(textFieldId.getText(), KeyWord.SPEED_UP, textFieldSpeedUp.getText());
			}
		});
		panel.add(textFieldSpeedUp);
		
		textFieldPopulationSmallMax = new JTextField();
		textFieldPopulationSmallMax.setColumns(10);
		textFieldPopulationSmallMax.setBounds(76, 192, 86, 20);
		textFieldPopulationSmallMax.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION_SMALL_MAX, textFieldPopulationSmallMax.getText());
			}
		});
		
		JLabel labelPopulationSmallMax = new JLabel("Poč. max. p.");
		labelPopulationSmallMax.setBounds(10, 198, 86, 14);
		panel.add(labelPopulationSmallMax);
		panel.add(textFieldPopulationSmallMax);
		
		textFieldPopulationMax = new JTextField();
		textFieldPopulationMax.setColumns(10);
		textFieldPopulationMax.setBounds(76, 226, 86, 20);
		textFieldPopulationMax.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION_MAX, textFieldPopulationMax.getText());
			}
		});
		
		JLabel labelPopulationMax = new JLabel("Max pop.");
		labelPopulationMax.setBounds(10, 229, 56, 14);
		panel.add(labelPopulationMax);
		panel.add(textFieldPopulationMax);
		
		panel.updateUI();
	}
	
	private void updatePlanet(String planetId, KeyWord key, String value) {
		dc.updatePlanet(Integer.parseInt(planetId), key, Integer.parseInt(value));
	}
	
	public void clearData() {
		textFieldId.setText("");
		textFieldX.setText("");
		textFieldY.setText("");
		textFieldRadius.setText("");
		textFieldSpeedUp.setText("");
		textFieldPopulation.setText("");
		textFieldPopulationMax.setText("");
		textFieldPopulationSmallMax.setText("");
	}
}
