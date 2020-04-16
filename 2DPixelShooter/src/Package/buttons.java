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
	ArrayList<String> ButtonNameList = new ArrayList<>(
			Arrays.asList("Arsenal_NewGun", "Arsenal_Gun1", "Arsenal_Gun2", "Arsenal_Gun3", "Arsenal_Gun4", "Arsenal_Gun5", "Arsenal_Gun6", "Arsenal_Gun7", "GunSpriteSelection_Gun1", "GunSpriteSelection_Gun2", "GunSpriteSelection_Gun3", "GunSpriteSelection_Gun4", "GunSpriteSelection_Gun5",
					"GunSpriteSelection_Gun6", "GunSpriteSelection_Gun7", "GunSpriteSelection_Gun8", "GunSpriteSelection_Gun9", "GunCategorySelection_Rifle", "GunCategorySelection_Shotgun", "GunCategorySelection_Sniper", "GunCategorySelection_Laser", "MainMenu_Play", "MainMenu_Arsenal"));
	ArrayList<ArrayList<Integer>> ButtonSpecificationsList = new ArrayList<>();
	ArrayList<JButton> ButtonList = new ArrayList<>();
	ArrayList<BufferedImage> imageList = new ArrayList<>();
	Main main;

	public buttons(Main main, Arsenal arsenal) {
		this.main = main;

		try {
			for (int i = 0; i < ButtonNameList.size(); i++) {
				BufferedImage image = null;
				String name = ButtonNameList.get(i).split("_")[0];
				if (name.equals("GunSpriteSelection") || name.equals("GunCategorySelection")) {
					if (i == 8) {
						image = ImageIO.read(getClass().getResourceAsStream("/gun.png"));
					} else {
						image = ImageIO.read(getClass().getResourceAsStream("/Frame.png"));
					}
				} else {
					InputStream input = getClass().getResourceAsStream("/" + ButtonNameList.get(i) + ".png");
					if (input != null) {
						image = ImageIO.read(input);
					} else {
						image = null;
					}

				}
				imageList.add(image);
			}
		} catch (Exception e) {

		}
		int j = 1, a = 1, u = 1;
		for (int i = 0; i < ButtonNameList.size(); i++) {
			String name = ButtonNameList.get(i).split("_")[0];
			if (name.equals("Arsenal")) {
				ButtonMaker(ButtonNameList.get(i), main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, (main.width - main.arsButtonWidth) / 2, (i + 1) * main.height / 8 - main.arsButtonHeight / 2);
			}
			if (name.equals("GunSpriteSelection")) {
				ButtonMaker(ButtonNameList.get(i), main.gSSButtonWidth, main.gSSButtonHeight, main.gSSButtonFontSize, j * main.width / 4 - main.gSSButtonWidth / 2, a * main.height / 4 - main.gSSButtonHeight / 2);
				j += 1;
				if (j >= 4) {
					j = 1;
					a += 1;
				}
			}
			if (name.equals("GunCategorySelection")) {
				ButtonMaker(ButtonNameList.get(i), main.gCaSButtonWidth, main.gCaSButtonHeight, main.gCaSButtonFontSize, u * main.width / 5 - main.gCaSButtonWidth / 2, (main.height - main.gCaSButtonHeight) / 2);
				u+=1;
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
		ButtonList.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ChangeClass("Arsenal_NewGun", "GunSpriteSelection", null, e);
		ChangeClass("MainMenu_Play", "Game", null, e);
		ChangeClass("MainMenu_Arsenal", "Arsenal", null, e);
		ChangeClass("GunSpriteSelection_Gun1", "GunCategorySelection", "Sprite=DefaultSprite", e);
		ChangeClass("GunCategorySelection_Rifle", "GunCreationScreen", "Category=Rifle", e);
		ChangeClass("GunCategorySelection_Sniper", "GunCreationScreen", "Category=Sniper", e);
		ChangeClass("GunCategorySelection_Shotgun", "GunCreationScreen", "Category=Shotgun", e);
	}

	public void ChangeClass(String buttonName, String newClassName, String gunAddon, ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(buttonName)) {

			main.SetButtonVisibility(buttonName.split("_")[0], false);
			main.SetButtonVisibility(newClassName, true);

			NullifyClass(buttonName);

			NewClass(newClassName, buttonName);

			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);

			if (gunAddon != null) {
				main.GunCreation.add(gunAddon);
			}
		}

	}

	private void NullifyClass(String buttonName) {
		String name = buttonName.split("_")[0];
		if (name.contentEquals("Arsenal")) {
			main.arsenal = null;
		}
		if (name.contentEquals("MainMenu")) {
			main.mainMenu = null;
		}
		if (name.contentEquals("GunCategorySelection")) {
			main.gunCategorySelection = null;
		}
		if (name.contentEquals("GunCreationScreen")) {
			main.gunCreationScreen = null;
		}
	}

	private void NewClass(String newClassName, String buttonName) {
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
		if (newClassName.contentEquals("GunCategorySelection")) {
			main.gunCategorySelection = new GunCategorySelection(main, this);
			main.addKeyListener(main.gunCategorySelection);
		}
		if (newClassName.contentEquals("GunCreationScreen")) {
			main.gunCreationScreen = new GunCreationScreen(main, buttonName.split("_")[1], this);
			main.addKeyListener(main.gunCreationScreen);
		}
	}

	public void update() {
		for (int i = 0; i < ButtonNameList.size(); i++) {
			ArrayList<Integer> list = ButtonSpecificationsList.get(i);
			ButtonList.get(i).setBounds(list.get(3), list.get(4), list.get(0), list.get(1));
		}
	}

}
