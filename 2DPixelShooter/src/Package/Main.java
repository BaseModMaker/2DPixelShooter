package Package;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	static JFrame f;
	static JPanel hoofdpaneel;

	private static final long serialVersionUID = 1L;
	public MainMenu mainMenu;
	public GunSpriteSelection gunSpriteSelection;
	public GunCreationScreen gunCreationScreen;
	public Arsenal arsenal;
	public Game game;
	public buttons buttons;
	public GunCategorySelection gunCategorySelection;
	public ArrayList<Bullet> bullet;
	ArrayList<String> GunCreation = new ArrayList<String>();
	ArrayList<ArrayList<String>> gunList = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> ArsenalGuns = new ArrayList<ArrayList<String>>();
	int GunIndex = 0;
	
	int width = 830;
	int height = 830;
	
	int arsButtonWidth = 200;
	int arsButtonHeight = 60;
	int arsButtonFontSize = 0;
	
	int mMButtonWidth = 200;
	int mMButtonHeight = 60;
	int mMButtonFontSize = 0;
	
	int gSSButtonWidth = 200;
	int gSSButtonHeight = 200;
	int gSSButtonFontSize = 0;
	
	int gCaSButtonWidth = 100;
	int gCaSButtonHeight = 200;
	int gCaSButtonFontSize = 0;
	
	public static void main(String[] args) {
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Pixel");
		hoofdpaneel = new Main();
		f.add(hoofdpaneel);
		f.setLocation(0, 0);
		f.setVisible(true);
	}

	public Main() {

		f.setSize(width, height);
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new UpdateTimerTask(), 0, 20);
		
		buttons = new buttons(this, arsenal);
		
		mainMenu = new MainMenu(this, buttons);
		SetButtonVisibility("MainMenu", true);

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
				if (gunCreationScreen != null) {
					gunCreationScreen.update();
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
				if (buttons != null) {
					buttons.update();
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
		if (gunCreationScreen != null) {
			gunCreationScreen.draw(g, this);
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
	
	public void SetButtonVisibility(String classToRemove, Boolean visibility) {
		for (int i = 0; i < buttons.ButtonNameList.size(); i++) {
			if (buttons.ButtonNameList.get(i).split("_")[0].equals(classToRemove)) {
				buttons.ButtonList.get(i).setVisible(visibility);
			}
		}
	}

}
