/**
 * 
 */
package up.mscs.cs140.galaga.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import up.mscs.cs140.galaga.constants.Constants;
import up.mscs.cs140.galaga.constants.GameState;
import up.mscs.cs140.galaga.panels.GameScreen;
import up.mscs.cs140.galaga.panels.InitScreen;

/**
 * Main Class
 */
public class GalagaGame extends JFrame implements KeyListener {

	private InitScreen initScreen;
	private GameScreen gameScreen;
	
	public GalagaGame() {
		super(Constants.GAME_NAME);
		prepareInitialScreen();
		prepareGameScreen();
		
		setLayout(new BorderLayout());
		setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
		addKeyListener(this);
		repaint();
		add(initScreen, BorderLayout.CENTER);
		requestFocus();
	}
	
	public void prepareInitialScreen() {
		initScreen = new InitScreen();
		initScreen.setLayout(new BorderLayout());
		initScreen.setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		initScreen.setFocusable(false);
	}
	
	public void prepareGameScreen() {
		gameScreen = new GameScreen();
		gameScreen.setLayout(new BorderLayout());
		gameScreen.setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		gameScreen.setFocusable(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			remove(initScreen);
			setFocusable(false);
			repaint();
			add(gameScreen);
			gameScreen.requestFocus();
			gameScreen.startGame();
			repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
