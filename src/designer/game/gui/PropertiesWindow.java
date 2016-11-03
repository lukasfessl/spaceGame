package designer.game.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import designer.game.core.DesignerCore;
import game.store.LevelDataStore;
import game.utils.KeyWord;

public class PropertiesWindow extends JFrame {

	private DesignerCore dc;
	private File pathToLastDir;
	
	private JPanel contentPane;
	public JTextField textFieldId;
	public JTextField textFieldY;
	public JTextField textFieldX;
	public JTextField textFieldPopulation;
	public JTextField textFieldRadius;
	public JTextField textFieldSpeedUp;
	public JTextField textFieldPopulationSmallMax;
	public JTextField textFieldPopulationMax;
	public JTextField textFieldType;
	public JTextField textFieldBackground;
	
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
				save();
			}
		});
		mnNewMenu.add(menuItemSave);
		
		JMenuItem menuItemLoad = new JMenuItem("Načíst");
		menuItemLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				load();
			}
		});
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
		textFieldY.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldY.setText(Integer.toString(Integer.parseInt(textFieldY.getText()) + e.getWheelRotation()));	
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
		textFieldX.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldX.setText(Integer.toString(Integer.parseInt(textFieldX.getText()) + e.getWheelRotation()));	
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
		textFieldPopulation.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldPopulation.setText(Integer.toString(Integer.parseInt(textFieldPopulation.getText()) + e.getWheelRotation()));	
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
		textFieldRadius.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldRadius.setText(Integer.toString(Integer.parseInt(textFieldRadius.getText()) + e.getWheelRotation()));
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
		textFieldSpeedUp.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldSpeedUp.setText(Integer.toString(Integer.parseInt(textFieldSpeedUp.getText()) + e.getWheelRotation()));
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
		textFieldPopulationSmallMax.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldPopulationSmallMax.setText(Integer.toString(Integer.parseInt(textFieldPopulationSmallMax.getText()) + e.getWheelRotation()));	
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
		textFieldPopulationMax.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldPopulationMax.setText(Integer.toString(Integer.parseInt(textFieldPopulationMax.getText()) + e.getWheelRotation()));	
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION_MAX, textFieldPopulationMax.getText());
			}
		});
		
		JLabel labelPopulationMax = new JLabel("Max pop.");
		labelPopulationMax.setBounds(10, 229, 56, 14);
		panel.add(labelPopulationMax);
		panel.add(textFieldPopulationMax);
		
		JLabel labelType = new JLabel("Typ:");
		labelType.setBounds(10, 260, 46, 14);
		panel.add(labelType);
		
		textFieldType = new JTextField();
		textFieldType.setBounds(76, 257, 86, 20);
		panel.add(textFieldType);
		textFieldType.setColumns(10);
		
		textFieldBackground = new JTextField();
		textFieldBackground.setText("0");
		textFieldBackground.setBounds(76, 360, 86, 20);
		panel.add(textFieldBackground);
		textFieldBackground.setColumns(10);
		textFieldBackground.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				update(KeyWord.BACKGROUND, textFieldBackground.getText());
			}
		});
		textFieldBackground.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldBackground.setText(Integer.toString(Integer.parseInt(textFieldBackground.getText()) + e.getWheelRotation()));	
				update(KeyWord.BACKGROUND, textFieldBackground.getText());
			}
		});		
		
		JLabel labelBackground = new JLabel("Pozadí:");
		labelBackground.setBounds(10, 363, 46, 14);
		panel.add(labelBackground);
		textFieldType.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke){
				updatePlanet(textFieldId.getText(), KeyWord.TYPE, textFieldType.getText());
			}
		});
		textFieldType.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldType.setText(Integer.toString(Integer.parseInt(textFieldType.getText()) + e.getWheelRotation()));	
				updatePlanet(textFieldId.getText(), KeyWord.TYPE, textFieldType.getText());
			}
		});
		
		panel.updateUI();
	}
	
	private void updatePlanet(String Planetd, KeyWord key, String value) {
		int tmpVal = Integer.parseInt(value) < 0 ? 0 : Integer.parseInt(value);
		dc.updatePlanet(Integer.parseInt(Planetd), key, tmpVal);
	}
	
	private void update(KeyWord key, String value) {
		int tmpVal = Integer.parseInt(value) < 0 ? 0 : Integer.parseInt(value);
		dc.update(key, tmpVal);
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
		textFieldType.setText("");
	}
	
	private void save() {
		JFileChooser chooser = new JFileChooser(pathToLastDir);
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
        	pathToLastDir = chooser.getCurrentDirectory();
        	try {
				JAXBContext jaxbContext = JAXBContext.newInstance(LevelDataStore.class);
				Marshaller marshaller = jaxbContext.createMarshaller();
				// output pretty printed
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(dc.getLevelDataStore(), new File(chooser.getSelectedFile().getAbsolutePath()));
				marshaller.marshal(dc.getLevelDataStore(), System.out);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	    } else {
	    }
	}
	
	private void load() {
		JFileChooser chooser = new JFileChooser(pathToLastDir);
		int retrival = chooser.showOpenDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
        	pathToLastDir = chooser.getCurrentDirectory();
        	try {
        		JAXBContext jc = JAXBContext.newInstance(LevelDataStore.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                LevelDataStore lds = (LevelDataStore) unmarshaller.unmarshal(new File(chooser.getSelectedFile().getAbsolutePath()));
                dc.loadLevelDataStore(lds);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	    } else {
	    }
	}
}
