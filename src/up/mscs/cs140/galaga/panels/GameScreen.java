package up.mscs.cs140.galaga.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import up.mscs.cs140.galaga.constants.Constants;
import up.mscs.cs140.galaga.constants.Coordinate;
import up.mscs.cs140.galaga.objects.Alien;
import up.mscs.cs140.galaga.objects.BattleShip;

public class GameScreen extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	private Image bgImage;
	
	private BattleShip battleShip;
	
	private List<Alien> aliens = new ArrayList<Alien>();
	private enum AlienMovement {LEFT, RIGHT, FORWARD};
	private AlienMovement currMovement;
	private AlienMovement prevMovement;
	private int alienSpeed = 200;
	
	private enum GameState {ONGOING, END, REPEAT};
	private GameState currState;
	
	public GameScreen() {
		loadGameBoard();
		addKeyListener(this);
	}
	
	private void loadGameBoard() {
		//Background
	    bgImage = new ImageIcon(getClass().getClassLoader().getResource(Constants.GAME_SCREEN_BG)).getImage();
	    
	    //Battle Ship
	    battleShip = new BattleShip(new Coordinate(Constants.BATTLE_SHIP_INIT_X, Constants.BATTLE_SHIP_INIT_Y));
	    
	    //Aliens
	    loadAliens();
	}
	
	public void startGame() {
		currState = GameState.ONGOING;
		currMovement = AlienMovement.LEFT;
		prevMovement = currMovement;
		
		Thread alienThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(GameState.ONGOING.equals(currState)) {
					try {
						Thread.sleep(alienSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					switch(currMovement) {
						case LEFT:
							if (canAlienMoveToLeft()) {
								moveAliensLeft();
								repaint();
							} else {
								currMovement = AlienMovement.FORWARD;
							}
							break;
						case RIGHT:
							if (canAlienMoveToRight()) {
								moveAliensRight();
								repaint();
							} else {
								currMovement = AlienMovement.FORWARD;
							}
							break;
						case FORWARD:
							moveAliensForward();
							currMovement = (AlienMovement.LEFT.equals(prevMovement)) ? AlienMovement.RIGHT : AlienMovement.LEFT;
							prevMovement = currMovement;
							repaint();
							break;
					}
				}
			}
			
		});
		alienThread.start();
	}
	
	private boolean canAlienMoveToLeft() {
		for (Alien alien : aliens) {
			System.out.println(alien.getCoordinate().getX());
			System.out.println(alien.canMoveLeft());
			if (!alien.canMoveLeft()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean canAlienMoveToRight() {
		for (Alien alien : aliens) {
			if (!alien.canMoveRight()) {
				return false;
			}
		}
		return true;
	}
	
	private void moveAliensLeft() {
		for (Alien alien : aliens) {
			alien.moveLeft();
		}
	}
	
	private void moveAliensRight() {
		for (Alien alien : aliens) {
			alien.moveRight();
		}
	}
	
	private void moveAliensForward() {
		for (Alien alien : aliens) {
			alien.moveForward();
		}
	}
	
	private void loadAliens() {
		int x = 130;
        int y = 10;
        for (int i = 0; i < 5; i++) {
        	aliens.add(new Alien(new Coordinate(x, y)));
        	x+=110;
        }
        x = 240;
        y = 45;
        for (int i = 0; i < 3; i++) {
        	aliens.add(new Alien(new Coordinate(x, y)));
        	x+=110;
        }
        x = 350;
        y = 80;
        aliens.add(new Alien(new Coordinate(x, y)));
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, Constants.WIDTH, Constants.HEIGHT, this);
        
        for (Alien alien : aliens) {
        	g.drawImage(alien.getImagePath(), alien.getCoordinate().getX(), alien.getCoordinate().getY(), Constants.ALIEN_WIDTH, Constants.ALIEN_HEIGHT, this);
        }
    	
    	g.drawImage(battleShip.getImagePath(), battleShip.getCoordinate().getX(), battleShip.getCoordinate().getY(), Constants.BATTLE_SHIP_WIDTH, Constants.BATTLE_SHIP_HEIGHT, this);
    }

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println(battleShip.getCoordinate().getX());
		if (Constants.VALID_LEFT_KEYS.contains(key) && battleShip.canMoveLeft()) {
			battleShip.moveLeft();
			repaint();
		} else if (Constants.VALID_RIGHT_KEYS.contains(key) && battleShip.canMoveRight()) {
			battleShip.moveRight();
			repaint();
		} else if (Constants.VALID_FIRE_KEYS.contains(key)) {
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
