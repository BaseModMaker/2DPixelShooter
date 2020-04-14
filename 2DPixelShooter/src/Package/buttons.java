package Package;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class buttons implements ActionListener {
	ArrayList<String> ButtonNameList = new ArrayList<>(Arrays.asList("Arsenal_NewGun", "Arsenal_Gun1", "Arsenal_Gun2", "Arsenal_Gun3", "Arsenal_Gun4", "Arsenal_Gun5", "Arsenal_Gun6", "Arsenal_Gun7"));
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
				// imageList.add(new ImageIcon(image.getScaledInstance(arsWidth, arsHeight,
				// BufferedImage.SCALE_DEFAULT)));
			}
		} catch (Exception e) {

		}
		
		List<String> arsNames = Arrays.asList("Arsenal_NewGun", "Arsenal_Gun1", "Arsenal_Gun2", "Arsenal_Gun3", "Arsenal_Gun4", "Arsenal_Gun5", "Arsenal_Gun6", "Arsenal_Gun7");
		for (int i = 0; i < ButtonNameList.size(); i++) {
			if (ButtonNameList.get(i).split("_")[0].equals("Arsenal")) {
				ButtonMaker(arsNames.get(i), main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, (main.getWidth() - main.arsButtonWidth) / 2, (i + 1) * main.getHeight() / 8 - main.arsButtonHeight / 2);
			} else {
				break;
			}
		}
		/*ButtonMaker("Arsenal_NewGun", main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, main.arsButtonXPos, main.arsButtonYPos);
		ButtonMaker("Arsenal_Gun1", main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, main.arsButtonXPos, main.arsButtonYPos);
		ButtonMaker("Arsenal_Gun2", main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, main.arsButtonXPos, main.arsButtonYPos);
		ButtonMaker("Arsenal_Gun3", main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, main.arsButtonXPos, main.arsButtonYPos);
		ButtonMaker("Arsenal_Gun4", main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, main.arsButtonXPos, main.arsButtonYPos);
		ButtonMaker("Arsenal_Gun5", main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, main.arsButtonXPos, main.arsButtonYPos);
		ButtonMaker("Arsenal_Gun6", main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, main.arsButtonXPos, main.arsButtonYPos);
		ButtonMaker("Arsenal_Gun7", main.arsButtonWidth, main.arsButtonHeight, main.arsButtonFontSize, main.arsButtonXPos, main.arsButtonYPos);*/

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
		button.setBounds(xPosition, yPosition, width, height);
		ButtonList.add(button);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Arsenal_NewGun") == true) {

			// Go from arsenal to gun sprite selection
			main.arsenal = null;
			main.gunSpriteSelection = new GunSpriteSelection(main, this);
			main.addKeyListener(main.gunSpriteSelection);
			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);
			main.SetButtonVisibility("Arsenal", false);

		}
	}

	public void update() {
		for (int i = 0; i < ButtonNameList.size(); i++) {
			if (ButtonNameList.get(i).split("_")[0].equals("Arsenal")) {
				ButtonList.get(i).setBounds((main.getWidth() - main.arsButtonWidth) / 2, (i + 1) * main.getHeight() / 8 - main.arsButtonHeight / 2, main.arsButtonWidth, main.arsButtonHeight);
			}
		}
	}

}
