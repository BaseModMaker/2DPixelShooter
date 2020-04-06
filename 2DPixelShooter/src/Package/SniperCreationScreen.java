package Package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SniperCreationScreen implements ActionListener, KeyListener {
	
	public SniperCreationScreen(Main main) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
/*
	Main main;
	int pannelWidth, pannelHeight;
	int buttonWidth = 60;
	int buttonHeight = 60;
	int buttonFontSize = 30;
	ArrayList<JButton> ButtonList = new ArrayList<>();
	ArrayList<String> ButtonNameList = new ArrayList<>(Arrays.asList("Ammo_+", "Ammo_-", "BulletSpeed_+", "BulletSpeed_-", "Create"));
	String AmmoTitle = "Ammo";
	String BulletSpeedTitle = "BulletSpeed";
	int AmmoCounter = 1;
	int BulletSpeedCounter = 1;

	public SniperCreationScreen(Main main) {

		// Import main
		this.main = main;

		// Initiate buttons
		for (int i = 0; i < ButtonNameList.size(); i++) {
			JButton button;
			String buttonName = String.valueOf(ButtonNameList.get(i));
			button = new JButton(buttonName);
			main.add(button);
			button.addActionListener(this);
			button.setVisible(true);
			button.setOpaque(true);
			button.setContentAreaFilled(true);
			button.setBorderPainted(true);
			button.setFont(new Font("Dialog", Font.PLAIN, buttonFontSize));
			ButtonList.add(button);
		}

	}

	public void draw(Graphics g, Main main) {
		g.drawString(AmmoTitle, pannelWidth / 2 - 300, pannelHeight / 2 - buttonHeight);
		g.drawString(BulletSpeedTitle, pannelWidth / 2 - 300, pannelHeight / 2 + buttonHeight);
		g.drawString(Integer.toString(AmmoCounter), pannelWidth / 2 + 300, pannelHeight / 2 - buttonHeight);
		g.drawString(Integer.toString(BulletSpeedCounter), pannelWidth / 2 + 300, pannelHeight / 2 + buttonHeight);

	}

	public void update() {
		// Get panel width and height
		pannelWidth = main.getWidth();
		pannelHeight = main.getHeight();

		// Adjust the bounds of play button if the screen width and height changes
		for (int i = 0; i <= ButtonNameList.size() - 1; i++) {

			int p = -1;
			if (i == 0 || i == 2) {
				p = 1;
			}

			int h = -1;
			if (i < 2) {
				h = 1;
			}

			ButtonList.get(i).setBounds(pannelWidth / 2 + (p * buttonWidth), pannelHeight / 2 - (h * buttonHeight), buttonWidth, buttonHeight);
		}
		ButtonList.get(ButtonList.size() - 1).setBounds(pannelWidth / 2, pannelHeight / 2 + 300, buttonWidth * 3, buttonHeight);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Check which button is pressed
		if (e.getActionCommand().equalsIgnoreCase("Ammo_+")) {

			AmmoCounter += 1;

		}

		if (e.getActionCommand().equalsIgnoreCase("Ammo_-")) {

			AmmoCounter -= 1;

		}

		if (e.getActionCommand().equalsIgnoreCase("BulletSpeed_+")) {

			BulletSpeedCounter += 1;

		}

		if (e.getActionCommand().equalsIgnoreCase("BulletSpeed_-")) {

			BulletSpeedCounter -= 1;

		}

		if (e.getActionCommand().equalsIgnoreCase("Create")) {

			// add made rifle to arsenal
			main.GunCreation.add("Ammo=" + Integer.toString(AmmoCounter));
			main.GunCreation.add("BulletSpeed=" + Integer.toString(BulletSpeedCounter));
			main.GunCreation.add(0, "Name=NewGun");
			main.ArsenalGuns.add(main.GunCreation);
			main.GunCreation = null;

			// go from rifle creation screen to arsenal
			main.rifleCreationScreen = null;
			main.arsenal = new Arsenal(main);
			main.addKeyListener(main.arsenal);
			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);

			// Remove the buttons on screen;
			int length = ButtonList.size();
			for (int i = 0; i < length; i++) {

				ButtonList.get(0).setVisible(false);
				ButtonList.remove(0);

			}
		}

		if (BulletSpeedCounter < 1) {
			BulletSpeedCounter = 1;
		}
		if (BulletSpeedCounter > 10) {
			BulletSpeedCounter = 10;
		}
		if (AmmoCounter < 1) {
			AmmoCounter = 1;
		}
		if (AmmoCounter > 10) {
			AmmoCounter = 10;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
*/