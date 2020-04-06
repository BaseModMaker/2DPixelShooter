package Package;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GunCategorySelection implements KeyListener, ActionListener {
	String Hi;
	Main main;
	int pannelWidth, pannelHeight;
	int buttonWidth = 100;
	int buttonHeight = 200;
	int buttonFontSize = 0;
	ArrayList<JButton> ButtonList = new ArrayList<>();
	ArrayList<String> ButtonNameList = new ArrayList<>(Arrays.asList("Rifle", "Shotgun", "Sniper", "Laser"));
	ArrayList<String> ImageNamesList = new ArrayList<>(
			Arrays.asList("/Frame.png", "/Frame.png", "/Frame.png", "/Frame.png"));
	ArrayList<BufferedImage> ImageList = new ArrayList<>();
	ArrayList<Icon> IconList = new ArrayList<>();
	String TitleMessage = "Choose a category";
	int TitleFontSize = 30;
	int CategoryFontSize = 15;

	public GunCategorySelection(Main main) {
		this.main = main;

		// Import images and add them to icon list
		try {
			for (int i = 0; i < ImageNamesList.size(); i++) {
				BufferedImage image = ImageIO.read(getClass().getResourceAsStream(ImageNamesList.get(i)));
				IconList.add(
						new ImageIcon(image.getScaledInstance(buttonWidth, buttonHeight, BufferedImage.SCALE_DEFAULT)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Initiate buttons
		for (int i = 0; i < ButtonNameList.size(); i++) {
			JButton button;
			button = new JButton(ButtonNameList.get(i));
			button.setIcon(IconList.get(i));
			main.add(button);
			button.addActionListener(this);
			button.setVisible(true);
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setFont(new Font("Dialog", Font.PLAIN, buttonFontSize));
			ButtonList.add(button);
		}

	}

	public void update() {
		// Get panel width and height
		pannelWidth = main.getWidth();
		pannelHeight = main.getHeight();

		// Adjust the borders of the buttons
		for (int i = 0; i < ButtonNameList.size(); i++) {
			ButtonList.get(i).setBounds((i + 1) * pannelWidth / 5 - buttonWidth / 2,
					pannelHeight / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
		}

	}

	public void draw(Graphics g, Main main) {
		g.setFont(new Font("Dialog", Font.BOLD, TitleFontSize));
		g.drawString(TitleMessage, pannelWidth / 2 - 140, pannelHeight / 2 - 130);

		g.setFont(new Font("Dialog", Font.BOLD, CategoryFontSize));
		for (int i = 0; i < ButtonNameList.size(); i++) {
			g.drawString(ButtonNameList.get(i), (i + 1) * pannelWidth / 5 - buttonWidth / 2, pannelHeight / 2);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ESCAPE:
			if (main.gunCategorySelection != null) {
				main.gunCategorySelection = null;
				main.gunSpriteSelection = new GunSpriteSelection(main);
				main.removeKeyListener(this);

				// Remove the buttons on screen;
				int length = ButtonList.size();
				for (int i = 0; i < length; i++) {

					ButtonList.get(0).setVisible(false);
					ButtonList.remove(0);

				}
				break;
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < ButtonNameList.size(); i++) {
			if (e.getActionCommand().equals(ButtonNameList.get(i)) == true) {
				main.GunCreation.add("Category=" + (ButtonNameList.get(i)));
				main.gunCategorySelection = null;
				main.rifleCreationScreen = new GunCreationScreen(main, ButtonNameList.get(i));
				main.addKeyListener(main.rifleCreationScreen);
				main.setFocusable(true);
				main.setFocusTraversalKeysEnabled(false);
			}
		}

		// Remove the buttons on screen;
		int length = ButtonList.size();
		for (int i = 0; i < length; i++) {

			ButtonList.get(0).setVisible(false);
			ButtonList.remove(0);

		}
	}

}
