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

import javax.imageio.ImageIO;

public class Arsenal implements KeyListener, ActionListener {
   
	Main main;
	BufferedImage FrameImage;
	buttons buttons;
	
	int pannelWidth, pannelHeight;
	

	public Arsenal(Main main, buttons buttons) {
		// Import main
		this.main = main;
		this.buttons = buttons;

		// Import images and add them to icon list
		try {
			FrameImage = ImageIO.read(getClass().getResourceAsStream("/Frame.png"));
		} catch (IOException e) {
			e.printStackTrace();
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
			g.drawImage(FrameImage, (pannelWidth - main.arsButtonWidth) / 2, counter * pannelHeight / 8 - main.arsButtonHeight / 2, main.arsButtonWidth, main.arsButtonHeight, null);
			g.drawString("Name: " + name[1], (pannelWidth - main.arsButtonWidth) / 2 + 10, counter * pannelHeight / 8 - main.arsButtonHeight / 2 + 20);
			g.drawString("Category: " + category[1], (pannelWidth - main.arsButtonWidth) / 2 + 10, counter * pannelHeight / 8 - main.arsButtonHeight / 2 + 40);
			counter += 1;
		}
	}

	public void update() {
		// Get panel width and height
		pannelWidth = main.getWidth();
		pannelHeight = main.getHeight();


	}


	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ESCAPE:
			// Remove the buttons on screen;
			
			if (main.arsenal != null) {
				main.arsenal = null;
				main.mainMenu = new MainMenu(main, buttons);
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
			main.gunSpriteSelection = new GunSpriteSelection(main, buttons);
			main.addKeyListener(main.gunSpriteSelection);
			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);

		}

		// Remove the buttons on screen;
		
	}

}
