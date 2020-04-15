package Package;

import java.awt.Graphics;


public class MainMenu {
	Main main;
	buttons buttons;
	int pannelWidth,pannelHeight;
	
	public MainMenu(Main main, buttons buttons) {
		
		//Import main
		this.main = main;
		
		this.buttons = buttons;
		
	}

	

	public void draw(Graphics g, Main main) {
	}

	

	public void update() {
		//Get panel width and height
		pannelWidth = main.getWidth();
		pannelHeight = main.getHeight();
		
	}
	
}


