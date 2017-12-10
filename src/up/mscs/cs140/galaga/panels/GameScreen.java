package up.mscs.cs140.galaga.panels;

import java.awt.Image;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import up.mscs.cs140.galaga.constants.Constants;

public class GameScreen extends JPanel {

	private Image bgImage;
	
	public GameScreen() {
		loadImage();
	}
	
	private void loadImage() {
	     bgImage = new ImageIcon(getClass().getClassLoader().getResource(Constants.INIT_SCREEN_BG)).getImage();
	}
	
}
