package Package;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class buttons implements ActionListener {
	ArrayList<String> ButtonNameList = new ArrayList<>(Arrays.asList("Arsenal_NewGun", "Arsenal_Gun1", "Arsenal_Gun2", "Arsenal_Gun3", "Arsenal_Gun4", "Arsenal_Gun5", "Arsenal_Gun6", "Arsenal_Gun7", "MainMenu_Play", "MainMenu_Arsenal"));
	ArrayList<ArrayList<Integer>> ButtonSpecificationsList = new ArrayList<>();
	ArrayList<JButton> ButtonList = new ArrayList<>();
	ArrayList<BufferedImage> imageList = new ArrayList<>();
	Main main;

	public buttons(Main main, Arsenal arsenal) {
		this.main = main;

		try {
			for (int i = 0; i < ButtonNameList.size(); i++) {
				BufferedImage image;
				InputStream input = getClass().getResourceAsStream("/" + ButtonNameList.get(i) + ".png");
				if (input != null) {
					image = ImageIO.read(input);
				} else {
					image = null;
				}
				imageList.add(image);
			}
		} catch (Exception e) {

		}

		// List<String> arsNames = Arrays.asList("Arsenal_NewGun", "Arsenal_Gun1",
		// "Arsenal_Gun2", "Arsenal_Gun3", "Arsenal_Gun4", "Arsenal_Gun5",
		// "Arsenal_Gun6", "Arsenal_Gun7");
		for (int i = 0; i < ButtonNameList.size(); i++) {
			if (ButtonNameList.get(i).split("_")[0].equals("Arsenal")) {
				ButtonMaker(ButtonNameList.get(i), main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, (main.width - main.arsButtonWidth) / 2, (i + 1) * main.height / 8 - main.arsButtonHeight / 2);
			} else {
				break;
			}
		}
		ButtonMaker("MainMenu_Play", main.mMButtonWidth, main.mMButtonHeight, main.mMButtonFontSize, (main.width - main.mMButtonWidth) / 2, (main.height - main.mMButtonHeight) / 2);
		ButtonMaker("MainMenu_Arsenal", main.mMButtonWidth, main.mMButtonHeight, main.mMButtonFontSize, (main.width - main.mMButtonWidth) / 2, 6 * main.height / 10 - main.mMButtonHeight / 2);
	}

	private void ButtonMaker(String name, int width, int height, int fontSize, int xPosition, int yPosition) {
		JButton button;
		button = new JButton(name);
		main.add(button);
		button.addActionListener(this);
		button.setVisible(false);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFont(new Font("Dialog", Font.PLAIN, fontSize));
		ButtonSpecificationsList.add(new ArrayList<>(Arrays.asList(width, height, fontSize, xPosition, yPosition)));
		BufferedImage image = imageList.get(ButtonNameList.indexOf(name));
		if (image != null) {
			button.setIcon(new ImageIcon(image.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT)));
		}
		// button.setBounds(xPosition, yPosition, width, height);
		ButtonList.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * if (e.getActionCommand().equals("Arsenal_NewGun") == true) {
		 * 
		 * // Go from arsenal to gun sprite selection main.arsenal = null;
		 * main.gunSpriteSelection = new GunSpriteSelection(main, this);
		 * main.addKeyListener(main.gunSpriteSelection); main.setFocusable(true);
		 * main.setFocusTraversalKeysEnabled(false); main.SetButtonVisibility("Arsenal",
		 * false);
		 * 
		 * } if(e.getActionCommand().equalsIgnoreCase("MainMenu_Play")) { //Go from
		 * MainMenu to Game main.mainMenu = null; main.game = new Game (main, this);
		 * main.addMouseListener(main.game); main.addKeyListener(main.game);
		 * main.setFocusable(true); main.setFocusTraversalKeysEnabled(false);
		 * main.SetButtonVisibility("MainMenu", false); }
		 * if(e.getActionCommand().equalsIgnoreCase("MainMenu_Arsenal")) {
		 * 
		 * //Go from main menu to arsenal main.SetButtonVisibility("Arsenal", true);
		 * main.mainMenu = null; main.arsenal = new Arsenal(main, this);
		 * main.addKeyListener(main.arsenal); main.setFocusable(true);
		 * main.setFocusTraversalKeysEnabled(false);
		 * main.SetButtonVisibility("MainMenu", false); }
		 */
		ChangeClass("Arsenal_NewGun", "GunSpriteSelection", e);
		ChangeClass("MainMenu_Play", "Game", e);
		ChangeClass("MainMenu_Arsenal", "Arsenal", e);
	}

	public void ChangeClass(String buttonName, String newClassName, ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(buttonName)) {
			
			main.SetButtonVisibility(buttonName.split("_")[0], false);
			main.SetButtonVisibility(newClassName, true);
			
			NullifyClass(buttonName);
			
			NewClass(newClassName);
		
			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);
		}

	}

	private void NullifyClass(String buttonName) {
		if (buttonName.split("_")[0].contentEquals("Arsenal")) {
			main.arsenal = null;
		}
		if (buttonName.split("_")[0].contentEquals("MainMenu")) {
			main.mainMenu = null;
		}
		if (buttonName.split("_")[0].contentEquals("GunCategorySelection")) {
			main.gunCategorySelection = null;
		}
		if (buttonName.split("_")[0].contentEquals("GunCreationScreen")) {
			main.gunCreationScreen = null;
		}
	}
	
	private void NewClass(String newClassName) {
		if (newClassName.contentEquals("Arsenal")) {
			main.arsenal = new Arsenal(main, this);
			main.addKeyListener(main.arsenal);
		}
		if (newClassName.contentEquals("Game")) {
			main.game = new Game(main, this);
			main.addMouseListener(main.game);
			main.addKeyListener(main.game);
		}
		if (newClassName.contentEquals("GunSpriteSelection")) {
			main.gunSpriteSelection = new GunSpriteSelection(main, this);
			main.addKeyListener(main.gunSpriteSelection);
		}
	}

	public void update() {
		for (int i = 0; i < ButtonNameList.size(); i++) {
			ArrayList<Integer> list = ButtonSpecificationsList.get(i);
			ButtonList.get(i).setBounds(list.get(3), list.get(4), list.get(0), list.get(1));
		}
	}

}
