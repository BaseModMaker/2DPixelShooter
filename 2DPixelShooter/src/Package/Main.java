package Package;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	String Hi;
	static JFrame f;
	static JPanel hoofdpaneel;

	public static void main(String[] args) {
		f = new JFrame();
		f.setSize(830, 830);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Pixel");
		hoofdpaneel = new Main();
		f.add(hoofdpaneel);
		f.setLocation(0, 0);
		f.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	public MainMenu mainMenu;
	public GunSpriteSelection gunSpriteSelection;
	public GunCreationScreen rifleCreationScreen;
	public Arsenal arsenal;
	public Game game;
	public GunCategorySelection gunCategorySelection;
	public ArrayList<Bullet> bullet;
	ArrayList<String> GunCreation = new ArrayList<String>();
	ArrayList<ArrayList<String>> gunList = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> ArsenalGuns = new ArrayList<ArrayList<String>>();
	int GunIndex = 0;

	public Main() {

		Timer t = new Timer();
		t.scheduleAtFixedRate(new UpdateTimerTask(), 0, 20);

		mainMenu = new MainMenu(this);

		bullet = new ArrayList<Bullet>();

		ArrayList<String> DefaultGun = new ArrayList<String>(Arrays.asList("Name=Default Gun", "Sprite=DefaultSprite", "Category=Sniper", "Ammo=10", "BulletSpeed=0"));
		ArsenalGuns.add(DefaultGun);

	}

	class UpdateTimerTask extends TimerTask {
		@Override
		public void run() {
			try {
				if (mainMenu != null) {
					mainMenu.update();
				}
				if (gunSpriteSelection != null) {
					gunSpriteSelection.update();
				}
				if (rifleCreationScreen != null) {
					rifleCreationScreen.update();
				}
				if (arsenal != null) {
					arsenal.update();
				}
				if (game != null) {
					game.update();
				}
				if (gunCategorySelection != null) {
					gunCategorySelection.update();
				}
				for (Bullet bullet : bullet) {
					bullet.update();
				}
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (mainMenu != null) {
			mainMenu.draw(g, this);
		}
		if (gunSpriteSelection != null) {
			gunSpriteSelection.draw(g, this);
		}
		if (rifleCreationScreen != null) {
			rifleCreationScreen.draw(g, this);
		}
		if (arsenal != null) {
			arsenal.draw(g, this);
		}
		if (game != null) {
			game.draw(g, this);
		}
		if (gunCategorySelection != null) {
			gunCategorySelection.draw(g, this);
		}
		try {
			for (Bullet bullet : bullet) {
				bullet.draw(g, this);
			}
		} catch (Exception e) {

		}
		repaint();
	}

}
