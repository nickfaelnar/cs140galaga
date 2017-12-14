package up.mscs.cs140.galaga.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import up.mscs.cs140.galaga.constants.Constants;

public class InitScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Image bgImage;
	private Image titleImage;
	
	public InitScreen() {
		loadImage();
	}
	
	private void loadImage() {
	     bgImage = new ImageIcon(getClass().getClassLoader().getResource(Constants.INIT_SCREEN_BG)).getImage();
	     titleImage = new ImageIcon(getClass().getClassLoader().getResource(Constants.INIT_SCREEN_TITLE)).getImage();
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, this);
        g.drawImage(titleImage, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, this);
    }

}
