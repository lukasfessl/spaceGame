package designer.game.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import game.utils.ResourceStore;

/**
 * 
 * @author Lukas Fessl
 *
 */
public class PropertiesWindow extends JFrame {

	private DesignerCore dc;
	private File pathToLastDir;
	private File lastSavedFile;
	private JLabel labelNameContent;

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

	public JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public PropertiesWindow(DesignerCore dc) {
		this.dc = dc;
		setTitle(ResourceStore.trans("properties"));
		setVisible(true);

		init();
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 263, 618);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu(ResourceStore.trans("designer.menu"));
		menuBar.add(mnNewMenu);

		JMenuItem menuItemSave = new JMenuItem(ResourceStore.trans("designer.save"));
		menuItemSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				save();
			}
		});

		JMenuItem mntmNov = new JMenuItem(ResourceStore.trans("designer.new"));
		mntmNov.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				newMap();
			}
		});
		mnNewMenu.add(mntmNov);
		mnNewMenu.add(menuItemSave);

		JMenuItem menuItemLoad = new JMenuItem(ResourceStore.trans("designer.load"));
		menuItemLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				load();
			}
		});

		JMenuItem mntmNewMenuItem = new JMenuItem(ResourceStore.trans("designer.saveAs"));
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				saveAs();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		mnNewMenu.add(menuItemLoad);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel labelId = new JLabel(ResourceStore.trans("designer.id"));
		labelId.setBounds(10, 42, 219, 14);
		panel.add(labelId);

		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(10, 57, 163, 20);
		panel.add(textFieldId);

		textFieldY = new JTextField();
		textFieldY.setColumns(10);
		textFieldY.setBounds(10, 147, 163, 20);
		textFieldY.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
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
		textFieldX.setBounds(10, 102, 163, 20);
		textFieldX.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				updatePlanet(textFieldId.getText(), KeyWord.POSITION_X, textFieldX.getText());
			}
		});
		textFieldX.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldX.setText(Integer.toString(Integer.parseInt(textFieldX.getText()) + e.getWheelRotation()));
				updatePlanet(textFieldId.getText(), KeyWord.POSITION_X, textFieldX.getText());
			}
		});

		JLabel labelX = new JLabel(ResourceStore.trans("designer.x"));
		labelX.setBounds(10, 86, 219, 14);
		panel.add(labelX);
		panel.add(textFieldX);

		JLabel labelY = new JLabel(ResourceStore.trans("designer.y"));
		labelY.setBounds(10, 131, 219, 14);
		panel.add(labelY);
		panel.add(textFieldY);

		textFieldPopulation = new JTextField();
		textFieldPopulation.setColumns(10);
		textFieldPopulation.setBounds(10, 234, 163, 20);
		textFieldPopulation.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION, textFieldPopulation.getText());
			}
		});
		textFieldPopulation.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldPopulation.setText(
						Integer.toString(Integer.parseInt(textFieldPopulation.getText()) + e.getWheelRotation()));
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION, textFieldPopulation.getText());
			}
		});

		textFieldRadius = new JTextField();
		textFieldRadius.setColumns(10);
		textFieldRadius.setBounds(10, 190, 163, 20);
		textFieldRadius.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				updatePlanet(textFieldId.getText(), KeyWord.SIZE, textFieldRadius.getText());
			}
		});
		textFieldRadius.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldRadius
						.setText(Integer.toString(Integer.parseInt(textFieldRadius.getText()) + e.getWheelRotation()));
				updatePlanet(textFieldId.getText(), KeyWord.SIZE, textFieldRadius.getText());
			}
		});

		JLabel labelRadius = new JLabel(ResourceStore.trans("designer.planetSize"));
		labelRadius.setBounds(10, 175, 219, 14);
		panel.add(labelRadius);
		panel.add(textFieldRadius);

		JLabel labelPopulation = new JLabel(ResourceStore.trans("designer.startPopulation"));
		labelPopulation.setBounds(10, 218, 219, 14);
		panel.add(labelPopulation);
		panel.add(textFieldPopulation);

		JLabel labelSpeedUp = new JLabel(ResourceStore.trans("designer.speedUp"));
		labelSpeedUp.setBounds(10, 263, 219, 14);
		panel.add(labelSpeedUp);

		textFieldSpeedUp = new JTextField();
		textFieldSpeedUp.setColumns(10);
		textFieldSpeedUp.setBounds(10, 279, 163, 20);
		textFieldSpeedUp.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				updatePlanet(textFieldId.getText(), KeyWord.SPEED_UP, textFieldSpeedUp.getText());
			}
		});
		textFieldSpeedUp.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldSpeedUp
						.setText(Integer.toString(Integer.parseInt(textFieldSpeedUp.getText()) + e.getWheelRotation()));
				updatePlanet(textFieldId.getText(), KeyWord.SPEED_UP, textFieldSpeedUp.getText());
			}
		});
		panel.add(textFieldSpeedUp);

		textFieldPopulationSmallMax = new JTextField();
		textFieldPopulationSmallMax.setColumns(10);
		textFieldPopulationSmallMax.setBounds(10, 323, 163, 20);
		textFieldPopulationSmallMax.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION_SMALL_MAX,
						textFieldPopulationSmallMax.getText());
			}
		});
		textFieldPopulationSmallMax.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldPopulationSmallMax.setText(Integer
						.toString(Integer.parseInt(textFieldPopulationSmallMax.getText()) + e.getWheelRotation()));
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION_SMALL_MAX,
						textFieldPopulationSmallMax.getText());
			}
		});

		JLabel labelPopulationSmallMax = new JLabel(ResourceStore.trans("designer.startSmallMaxPopulation"));
		labelPopulationSmallMax.setBounds(10, 307, 219, 14);
		panel.add(labelPopulationSmallMax);
		panel.add(textFieldPopulationSmallMax);

		textFieldPopulationMax = new JTextField();
		textFieldPopulationMax.setColumns(10);
		textFieldPopulationMax.setBounds(10, 368, 163, 20);
		textFieldPopulationMax.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION_MAX, textFieldPopulationMax.getText());
			}
		});
		textFieldPopulationMax.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldPopulationMax.setText(
						Integer.toString(Integer.parseInt(textFieldPopulationMax.getText()) + e.getWheelRotation()));
				updatePlanet(textFieldId.getText(), KeyWord.POPULATION_MAX, textFieldPopulationMax.getText());
			}
		});

		JLabel labelPopulationMax = new JLabel(ResourceStore.trans("designer.startMaxPopulation"));
		labelPopulationMax.setBounds(10, 351, 219, 14);
		panel.add(labelPopulationMax);
		panel.add(textFieldPopulationMax);

		JLabel labelType = new JLabel(ResourceStore.trans("designer.planetType"));
		labelType.setBounds(10, 396, 219, 14);
		panel.add(labelType);

		textFieldType = new JTextField();
		textFieldType.setBounds(10, 413, 163, 20);
		panel.add(textFieldType);
		textFieldType.setColumns(10);

		textFieldBackground = new JTextField();
		textFieldBackground.setText("0");
		textFieldBackground.setBounds(10, 505, 163, 20);
		panel.add(textFieldBackground);
		textFieldBackground.setColumns(10);
		textFieldBackground.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				update(KeyWord.BACKGROUND, textFieldBackground.getText());
			}
		});
		textFieldBackground.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldBackground.setText(
						Integer.toString(Integer.parseInt(textFieldBackground.getText()) + e.getWheelRotation()));
				update(KeyWord.BACKGROUND, textFieldBackground.getText());
			}
		});

		JLabel labelBackground = new JLabel(ResourceStore.trans("designer.background"));
		labelBackground.setBounds(10, 490, 219, 14);
		panel.add(labelBackground);

		String[] items = new String[] {ResourceStore.trans("designer.player0"), ResourceStore.trans("designer.player1"),
				ResourceStore.trans("designer.player2"), ResourceStore.trans("designer.player3"),
				ResourceStore.trans("designer.player4"), ResourceStore.trans("designer.player5")};
		comboBox = new JComboBox(items);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					updatePlanet(textFieldId.getText(), KeyWord.PLAYER, Integer.toString(comboBox.getSelectedIndex()));
				}
			}
		});
		comboBox.setBounds(10, 459, 163, 20);
		panel.add(comboBox);

		JLabel labelPlayer = new JLabel(ResourceStore.trans("designer.player"));
		labelPlayer.setBounds(10, 444, 219, 14);
		panel.add(labelPlayer);

		JLabel labelName = new JLabel(ResourceStore.trans("designer.fileName"));
		labelName.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelName.setBounds(10, 11, 114, 14);
		panel.add(labelName);

		labelNameContent = new JLabel(ResourceStore.trans("designer.dash"));
		labelNameContent.setBounds(110, 10, 117, 14);
		panel.add(labelNameContent);
		textFieldType.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				updatePlanet(textFieldId.getText(), KeyWord.TYPE, textFieldType.getText());
			}
		});
		textFieldType.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				textFieldType
						.setText(Integer.toString(Integer.parseInt(textFieldType.getText()) + e.getWheelRotation()));
				updatePlanet(textFieldId.getText(), KeyWord.TYPE, textFieldType.getText());
			}
		});

		panel.updateUI();
	}

	/**
	 * Send to core hint to update planet by ID.
	 * 
	 * @param planetId
	 *            {@link String} Id planet which should be updated
	 * @param key
	 *            {@link KeyWord} key set what in planet should be changed
	 * @param value
	 *            {@link String} value to change
	 */
	private void updatePlanet(String planetId, KeyWord key, String value) {
		int tmpVal = Integer.parseInt(value) < 0 ? 0 : Integer.parseInt(value);
		dc.updatePlanet(Integer.parseInt(planetId), key, tmpVal);
	}

	/**
	 * Send to core hint to do something. What is set by key
	 * 
	 * @param key
	 *            {@link KeyWord} key set what should be changed
	 * @param value
	 *            {@link String} value to change
	 */
	private void update(KeyWord key, String value) {
		int tmpVal = Integer.parseInt(value) < 0 ? 0 : Integer.parseInt(value);
		dc.update(key, tmpVal);
	}

	/**
	 * Remove texts from textFields
	 */
	public void clearPlanetData() {
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

	// ------------- action menu -------------------

	/**
	 * Remove planet from list, set default background, clear texFields
	 */
	private void newMap() {
		dc.clearData();
		this.clearPlanetData();
		this.lastSavedFile = null;
		this.labelNameContent.setText("-");
		this.textFieldBackground.setText(Integer.toString(0));
		this.comboBox.setSelectedIndex(0);
	}

	/**
	 * When first time is level saved JFileChooser is shown. Next saving will be
	 * automatically
	 */
	private void save() {
		if (lastSavedFile != null) {
			processFile(lastSavedFile);
		} else {
			JFileChooser chooser = new JFileChooser(pathToLastDir);
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				pathToLastDir = chooser.getCurrentDirectory();
				File file = new File(chooser.getSelectedFile().getAbsolutePath());
				processFile(file);
				lastSavedFile = file;
				labelNameContent.setText(chooser.getSelectedFile().getName());
			} else {
			}
		}
	}

	/**
	 * Show JFileChooser to save
	 */
	private void saveAs() {
		JFileChooser chooser = new JFileChooser(pathToLastDir);
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			pathToLastDir = chooser.getCurrentDirectory();
			File file = new File(chooser.getSelectedFile().getAbsolutePath());
			processFile(file);
			lastSavedFile = file;
			labelNameContent.setText(chooser.getSelectedFile().getName());
		} else {
		}
	}

	/**
	 * Process object to xml file and save it
	 */
	private void processFile(File file) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(LevelDataStore.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			// output pretty printed
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(dc.getLevelDataStore(), file);
			marshaller.marshal(dc.getLevelDataStore(), System.out);
			JOptionPane.showMessageDialog(null, "Level byl uložen", "Success", JOptionPane.PLAIN_MESSAGE);
		} catch (JAXBException e) {
			JOptionPane.showMessageDialog(null, "Level se nepodařilo uložit", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Load xml file and create level. Location is select via JFileChooser
	 */
	private void load() {
		JFileChooser chooser = new JFileChooser(pathToLastDir);
		int retrival = chooser.showOpenDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			pathToLastDir = chooser.getCurrentDirectory();
			try {
				File file = new File(chooser.getSelectedFile().getAbsolutePath());
				JAXBContext jc = JAXBContext.newInstance(LevelDataStore.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				LevelDataStore lds = (LevelDataStore) unmarshaller.unmarshal(file);
				lastSavedFile = file;
				dc.loadLevelDataStore(lds);
				labelNameContent.setText(chooser.getSelectedFile().getName());
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} else {
		}
	}

}
