package Package;

import java.awt.Color;
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

public class Arsenal implements KeyListener, ActionListener {
    String lol = "hi";
	Main main;
	BufferedImage FrameImage;
	int pannelWidth, pannelHeight;
	int buttonWidth = 200;
	int buttonHeight = 60;
	int buttonFontSize = 0;
	ArrayList<JButton> ButtonList = new ArrayList<>();
	ArrayList<String> ButtonNameList = new ArrayList<>(Arrays.asList("NewGun", "Gun1", "Gun2", "Gun3", "Gun4", "Gun5", "Gun6", "Gun7"));
	ArrayList<String> ImageNamesList = new ArrayList<>(Arrays.asList("/NewGunText.png"));
	ArrayList<BufferedImage> ImageList = new ArrayList<>();
	ArrayList<Icon> IconList = new ArrayList<>();

	public Arsenal(Main main) {
		// Import main
		this.main = main;

		// Import images and add them to icon list
		try {
			FrameImage = ImageIO.read(getClass().getResourceAsStream("/Frame.png"));
			for (int i = 0; i < ImageNamesList.size(); i++) {
				BufferedImage image = ImageIO.read(getClass().getResourceAsStream(ImageNamesList.get(i)));
				IconList.add(new ImageIcon(image.getScaledInstance(buttonWidth, buttonHeight, BufferedImage.SCALE_DEFAULT)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Initiate buttons
		for (int i = 0; i < ButtonNameList.size(); i++) {
			JButton button;
			button = new JButton(ButtonNameList.get(i));
			if (i == 0) {
				button.setIcon(IconList.get(i));
			}
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

	public void draw(Graphics g, Main main) {
		int counter = 2;
		for (int i = 0; i < main.ArsenalGuns.size(); i++) {
			ArrayList<String> list = main.ArsenalGuns.get(i);
			String[] name = list.get(0).split("=");
			String[] category = list.get(2).split("=");
			g.setFont(new Font("Dialog", Font.BOLD, 13));
			g.setColor(Color.BLACK);
			g.drawImage(FrameImage, (pannelWidth - buttonWidth) / 2, counter * pannelHeight / 8 - buttonHeight / 2, buttonWidth, buttonHeight, null);
			g.drawString("Name: " + name[1], (pannelWidth - buttonWidth) / 2 + 10, counter * pannelHeight / 8 - buttonHeight / 2 + 20);
			g.drawString("Category: " + category[1], (pannelWidth - buttonWidth) / 2 + 10, counter * pannelHeight / 8 - buttonHeight / 2 + 40);
			counter += 1;
		}
	}

	public void update() {
		// Get panel width and height
		pannelWidth = main.getWidth();
		pannelHeight = main.getHeight();

		// Adjust the bounds of play button if the screen width and height changes
		AdjustButtonBounds();

	}

	private void AdjustButtonBounds() {
		for (int i = 0; i < ButtonNameList.size(); i++) {
			ButtonList.get(i).setBounds((pannelWidth - buttonWidth) / 2, (i + 1) * pannelHeight / 8 - buttonHeight / 2, buttonWidth, buttonHeight);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ESCAPE:
			// Remove the buttons on screen;
			for (int i = 0; i < ButtonList.size(); i++) {

				ButtonList.get(0).setVisible(false);
				ButtonList.remove(0);

			}
			if (main.arsenal != null) {
				main.arsenal = null;
				main.mainMenu = new MainMenu(main);
				main.removeKeyListener(this);

				break;

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NewGun") == true) {

			// Go from arsenal to gun sprite selection
			main.arsenal = null;
			main.gunSpriteSelection = new GunSpriteSelection(main);
			main.addKeyListener(main.gunSpriteSelection);
			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);

		}

		// Remove the buttons on screen;
		for (int i = 0; i < ButtonList.size(); i++) {
			ButtonList.get(0).setVisible(false);
			ButtonList.remove(0);

		}
	}

}
