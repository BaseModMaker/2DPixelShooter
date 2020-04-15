package Package;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GunSpriteSelection implements KeyListener{
	Main main;
	buttons buttons;
	BufferedImage FrameImage;
	int pannelWidth, pannelHeight;

	public GunSpriteSelection(Main main, buttons buttons) {
		this.main = main;

	}

	public void draw(Graphics g, Main main) {

	}

	public void update() {
		// Get panel width and height
		pannelWidth = main.getWidth();
		pannelHeight = main.getHeight();
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

				break;
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
