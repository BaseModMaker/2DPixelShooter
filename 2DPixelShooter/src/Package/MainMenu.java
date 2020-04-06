package Package;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainMenu implements ActionListener{
	String Hi;
	Main main;
	int pannelWidth,pannelHeight;
	int buttonWidth = 200;
	int buttonHeight = 60;
	int buttonFontSize = 0;
	ArrayList<JButton> ButtonList = new ArrayList<>();
	ArrayList<String> ButtonNameList = new ArrayList<>(Arrays.asList("Play","Arsenal"));
	ArrayList<String> ImageNamesList = new ArrayList<>(Arrays.asList("/PlayText.png","/MenuText.png"));
	ArrayList<BufferedImage> ImageList = new ArrayList<>();
	ArrayList<Icon> IconList = new ArrayList<>();
	
	public MainMenu(Main main) {
		
		//Import main
		this.main = main;
		
		
		//Import images and add them to icon list
		try {
			for (int i = 0; i < ImageNamesList.size(); i++) {
				BufferedImage image = ImageIO.read(getClass().getResourceAsStream(ImageNamesList.get(i)));
				IconList.add(new ImageIcon(image.getScaledInstance(buttonWidth, buttonHeight, BufferedImage.SCALE_DEFAULT)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Initiate buttons 
		for (int i = 0; i< ButtonNameList.size(); i++) {
			JButton button;
			button = new JButton(ButtonNameList.get(i));
			main.add(button);
			button.setIcon(IconList.get(i));
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
	}

	

	public void update() {
		//Get panel width and height
		pannelWidth = main.getWidth();
		pannelHeight = main.getHeight();
		
		
		//Adjust the bounds of play button if the screen width and height changes
		ButtonList.get(ButtonNameList.indexOf("Play")).setBounds((pannelWidth - buttonWidth)/2,(pannelHeight - buttonHeight)/2,buttonWidth,buttonHeight);
		
		
		//Adjust the bounds of menu button if the screen width and height changes
		ButtonList.get(ButtonNameList.indexOf("Arsenal")).setBounds((pannelWidth - buttonWidth)/2,6*pannelHeight/10 - buttonHeight/2,buttonWidth,buttonHeight);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
				
		//Check which button is pressed
		if(e.getActionCommand().equalsIgnoreCase("Play")) {
			
			//Go from MainMenu to Game
			main.mainMenu = null;
			main.game = new Game (main);
			main.addMouseListener(main.game);
			main.addKeyListener(main.game);
			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);
		}
		else{
			
			//Go from main menu to arsenal
			main.mainMenu = null;
			main.arsenal = new Arsenal(main);
			main.addKeyListener(main.arsenal);
			main.setFocusable(true);
			main.setFocusTraversalKeysEnabled(false);
		
		}
		
		
		//Remove the buttons on screen;
		int length = ButtonList.size();
		for(int i = 0; i<length;i++) {
			
			ButtonList.get(0).setVisible(false);
			ButtonList.remove(0);
			
		}
		
	}
	
}


