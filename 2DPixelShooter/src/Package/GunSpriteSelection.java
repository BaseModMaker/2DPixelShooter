package Package;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GunSpriteSelection implements KeyListener, ActionListener {
	Main main;
	buttons buttons;
	BufferedImage FrameImage;
	int pannelWidth, pannelHeight;
	/*
	ArrayList<JButton> ButtonList = new ArrayList<>();
	ArrayList<String> ButtonNameList = new ArrayList<>(Arrays.asList("Gun1", "Gun2", "Gun3", "Gun4", "Gun5", "Gun6", "Gun7", "Gun8", "Gun9"));
	ArrayList<String> ImageNamesList = new ArrayList<>(Arrays.asList("/gun.png", "/Frame.png", "/Frame.png", "/Frame.png", "/Frame.png", "/Frame.png", "/Frame.png", "/Frame.png", "/Frame.png"));
	ArrayList<BufferedImage> ImageList = new ArrayList<>();
	ArrayList<Icon> IconList = new ArrayList<>();*/

	public GunSpriteSelection(Main main, buttons buttons) {
		this.main = main;

		// Import images and add them to icon list
		/*try {
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
			// if (i == 0) {
			button.setIcon(IconList.get(i));
			// }
			main.add(button);
			button.addActionListener(this);
			button.setVisible(true);
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setFont(new Font("Dialog", Font.PLAIN, buttonFontSize));
			ButtonList.add(button);
		}*/

	}

	public void draw(Graphics g, Main main) {

	}

	public void update() {
		// Get panel width and height
		pannelWidth = main.getWidth();
		pannelHeight = main.getHeight();
		/*int j = 1, a = 1;
		for (int i = 0; i < ButtonNameList.size(); i++) {
			ButtonList.get(i).setBounds(j*pannelWidth/4 - buttonWidth/ 2, a * pannelHeight / 4 - buttonHeight / 2, buttonWidth, buttonHeight);
			j+=1;
			if(j>=4) {
				j=1;
				a+=1;
			}
		}*/
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ESCAPE:
			if (main.gunSpriteSelection != null) {
				main.gunSpriteSelection = null;
				main.arsenal = new Arsenal(main, buttons);
				main.SetButtonVisibility("Arsenal", true);
				main.removeKeyListener(this);

				// Remove the buttons on screen;
				/*int length = ButtonList.size();
				for (int i = 0; i < length ; i++) {

					ButtonList.get(0).setVisible(false);
					ButtonList.remove(0);

				}*/
				break;
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.GunCreation = new ArrayList<String>();
		if(e.getActionCommand().equals("Gun1") == true) {
			main.GunCreation.add("Sprite=DefaultSprite");
			main.gunSpriteSelection = null;
			main.gunCategorySelection = new GunCategorySelection(main, buttons);
			main.addKeyListener(main.gunCategorySelection);
			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);
		}
		
		// Remove the buttons on screen;
		/*int length = ButtonList.size();
		for (int i = 0; i < length; i++) {

			ButtonList.get(0).setVisible(false);
			ButtonList.remove(0);

		}*/
	}
}
