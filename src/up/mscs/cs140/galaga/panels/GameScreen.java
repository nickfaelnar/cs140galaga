package up.mscs.cs140.galaga.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import up.mscs.cs140.galaga.constants.Constants;

public class GameScreen extends JPanel {

	private Image bgImage;
	private Image alienImage;
	private Image fighterImage;
	
	public GameScreen() {
		loadImage();
	}
	
	private void loadImage() {
	     bgImage = new ImageIcon(getClass().getClassLoader().getResource(Constants.GAME_SCREEN_BG)).getImage();
	     alienImage = new ImageIcon(getClass().getClassLoader().getResource(Constants.ALIEN_SPRITE)).getImage();
	     fighterImage = new ImageIcon(getClass().getClassLoader().getResource(Constants.FIGHTER_SPRITE)).getImage();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, Constants.WIDTH, Constants.HEIGHT, this);
        
        int x = 130;
        int y = 10;
        for (int i = 0; i < 5; i++) {
        	g.drawImage(alienImage, x, y, Constants.ALIEN_WIDTH, Constants.ALIEN_HEIGHT, this);
        	x+=110;
        }
        x = 240;
        y = 45;
        for (int i = 0; i < 3; i++) {
        	g.drawImage(alienImage, x, y, Constants.ALIEN_WIDTH, Constants.ALIEN_HEIGHT, this);
        	x+=110;
        }
        x = 350;
        y = 80;
    	g.drawImage(alienImage, x, y, Constants.ALIEN_WIDTH, Constants.ALIEN_HEIGHT, this);
    	
    	g.drawImage(fighterImage, 350, 500, Constants.FIGHTER_WIDTH, Constants.FIGHTER_HEIGHT, this);
    }
	
}
