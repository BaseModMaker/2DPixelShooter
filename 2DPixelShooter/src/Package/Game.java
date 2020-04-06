package Package;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game implements MouseListener, KeyListener {
	String Hi;
	int MouseXValue;
	int MouseYValue;
	Main main;
	int SniperShootTime;

	public Game(Main main) {
		this.main = main;
	}

	public void draw(Graphics g, Main main) {
		if (SniperShootTime > 0) {
			g.drawLine(main.getWidth() / 2, main.getHeight() / 2, MouseXValue, MouseYValue);
			SniperShootTime -= 1;
		}
	}

	public void update() {
		// Get the x and y value of the cursor
		MouseXValue = MouseInfo.getPointerInfo().getLocation().x - Main.f.getX();
		MouseYValue = MouseInfo.getPointerInfo().getLocation().y - Main.f.getY() - 23;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Calculate the angle at which the bullet needs to be shot
		int height = MouseYValue - main.getHeight() / 2;
		int width = MouseXValue - main.getWidth() / 2;
		double radius = Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2));
		double angle = Math.acos(width / radius);
		if (height < 0) {
			angle = -Math.acos(width / radius);
		}

		// Create a new bullet to be shot by the gun
		String[] bulletAttribute = main.ArsenalGuns.get(main.GunIndex).get(4).split("="); // bulletSpeed or bulletAmount depends on the category
		String GunCategory = main.ArsenalGuns.get(main.GunIndex).get(2);
		if (GunCategory.equals("Category=Shotgun") == true) {
			for (int i = 0; i < Integer.parseInt(bulletAttribute[1]); i++) {
				Double ShotgunAngle;
				if ( (i & 1) == 0 ) {
					ShotgunAngle = angle+i*0.1;
				}else {
					ShotgunAngle = angle-i*0.1;
				}
				main.bullet.add(new Bullet(main, GunCategory, main.getWidth() / 2, main.getHeight() / 2, ShotgunAngle, 5));
			}
		} else {
			main.bullet.add(new Bullet(main, GunCategory, main.getWidth() / 2, main.getHeight() / 2, angle, Integer.parseInt(bulletAttribute[1])));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ESCAPE:

			main.game = null;
			main.mainMenu = new MainMenu(main);
			main.removeKeyListener(this);
			main.removeMouseListener(this);

			break;
		case KeyEvent.VK_M:

			main.GunIndex += 1;
			if (main.GunIndex >= main.ArsenalGuns.size()) {
				main.GunIndex = 0;
			}

			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
