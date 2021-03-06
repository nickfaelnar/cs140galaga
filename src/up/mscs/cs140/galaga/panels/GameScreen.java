package up.mscs.cs140.galaga.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import up.mscs.cs140.galaga.constants.AlienMovement;
import up.mscs.cs140.galaga.constants.AlienState;
import up.mscs.cs140.galaga.constants.Constants;
import up.mscs.cs140.galaga.constants.Coordinate;
import up.mscs.cs140.galaga.constants.GameState;
import up.mscs.cs140.galaga.objects.Alien;
import up.mscs.cs140.galaga.objects.BattleShip;
import up.mscs.cs140.galaga.objects.Missile;

public class GameScreen extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	private Image bgImage;
	
	private BattleShip battleShip;
	
	private List<Alien> aliens;
	private AlienMovement currMovement;
	private AlienMovement prevSideMovement;
	private int alienSpeed = Constants.ALIEN_INIT_SPEED;
	
	private List<Missile> missiles;
	private int missileSpeed = Constants.MISSILE_INIT_SPEED;
	
	private GameState currState;
	
	Thread alienThread;
	Thread missileThread;
	
	public GameScreen() {
		addKeyListener(this);
	}
	
	public void startGame() {
		loadGameBoard();
		
		currState = GameState.ACTIVE;
		currMovement = Constants.ALIEN_INIT_MOVEMENT;
		prevSideMovement = currMovement;
		
		alienThread.start();
		missileThread.start();
	}
	
	
	private void loadGameBoard() {
		removeAll();
		revalidate();
		
		//Background
	    bgImage = new ImageIcon(getClass().getClassLoader().getResource(Constants.GAME_SCREEN_BG)).getImage();
	    
	    //Battle Ship
	    battleShip = new BattleShip(new Coordinate(Constants.BATTLE_SHIP_INIT_X, Constants.BATTLE_SHIP_INIT_Y));
	    
	    //Aliens
	    aliens = Collections.synchronizedList(new ArrayList<Alien>());
	    loadAliens();
	    
	    //Missiles
	    missiles = Collections.synchronizedList(new ArrayList<Missile>());
	    
	    prepareAlienThread();
	    prepareMissileThread();
	}
	
	private void prepareAlienThread() {
		alienThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(GameState.ACTIVE.equals(currState)) {
					try {
						Thread.sleep(alienSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					switch(currMovement) {
						case LEFT:
						case RIGHT:
							if (canAlienMoveToSide(currMovement)) {
								moveAliens(currMovement);
								repaint();
							} else {
								currMovement = AlienMovement.FORWARD;
							}
							break;
						case FORWARD:
							moveAliens(AlienMovement.FORWARD);
							currMovement = (AlienMovement.LEFT.equals(prevSideMovement)) ? AlienMovement.RIGHT : AlienMovement.LEFT;
							prevSideMovement = currMovement;
							repaint();
							break;
					}
				}
			}
			
		});
	}
	
	private void prepareMissileThread() {
		missileThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(GameState.ACTIVE.equals(currState)) {
					try {
						Thread.sleep(missileSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					moveMissilesForward();
				}
			}
			
		});
	}
	
	private void checkAliens() {
		if (aliens.isEmpty()) {
			System.out.println("You killed all the aliens! Hoorah!");
			endGame();
			
//			currState = GameState.REPEAT;
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			alienSpeed -= Constants.ALIEN_SPEED_DELTA;
//			missileSpeed -= Constants.MISSILE_SPEED_DELTA;
//			battleShip.setDeltaX(battleShip.getDeltaX() - Constants.BATTLE_SHIP_SPEED_DELTA);
//			
//			startGame();
		}
	}
	
	private void endGame() {
		currState = GameState.END;
		System.out.println("Game Ended");
	}
	private boolean canAlienMoveToSide(AlienMovement movement) {
		synchronized (aliens) {
			for (Alien alien : aliens) {
				boolean canMove = (AlienMovement.LEFT.equals(movement)) ? alien.canMoveLeft() : alien.canMoveRight();
				if (!canMove && alien.isAlive()) {
					return false;
				}
			}
			return true;
		}
	}
	
	private void moveAliens(AlienMovement alienMovement) {
		boolean toEndGame = false;
		synchronized (aliens) {
			for (Alien alien : aliens) {
				if (!hasAlienReachedEnd(alien) && !hasHitBattleShip(alien)) {
					switch (alienMovement) {
						case LEFT:
							alien.moveLeft();
							break;
						case RIGHT:
							alien.moveRight();
							break;
						case FORWARD:
							alien.moveForward();
							break;
					}
				} else {
					toEndGame = true;
					if (hasAlienReachedEnd(alien)) { 
						System.out.println("You failed to stop all aliens!");
					} else if (hasHitBattleShip(alien)) {
						System.out.println("You've been hit!");
						System.out.println(battleShip.getCoordinate().toString());
						System.out.println(alien.getCoordinate().toString());
					}
					break;
				}
			}
		}
		
		if (toEndGame) {
			endGame();
		}
		
	}
	
	private void moveMissilesForward() {
		for (Iterator<Missile> it = missiles.iterator(); it.hasNext();) {
			Missile missile = it.next();
			if (!hasHitAlien(missile) && !isOutOfScreen(missile)) {
				missile.moveForward();
			} else {
				synchronized (missiles) {
					it.remove();
				}			
			}
		}
	}
	
	private boolean hasHitAlien(Missile missile) {
		int missileX = missile.getCoordinate().getX();
		int missileY = missile.getCoordinate().getY();
		for (Iterator<Alien> it = aliens.iterator(); it.hasNext();) {
			Alien alien = it.next();
			if (alien.isAlive() && missileX >= alien.getCoordinate().getX() && missileX <= (alien.getCoordinate().getX() + Constants.ALIEN_WIDTH)
					&& missileY > alien.getCoordinate().getY() && missileY < (alien.getCoordinate().getY() + Constants.ALIEN_HEIGHT)) {
					synchronized (aliens) {
						it.remove();
						checkAliens();
					}
				return true;
			}
		}
		return false;
	}
	
	private boolean hasAlienReachedEnd(Alien alien) {
		return alien.isAlive() && (alien.getCoordinate().getY() + Constants.ALIEN_HEIGHT) >= Constants.WINDOW_HEIGHT;
	}
	
	private boolean hasHitBattleShip(Alien alien) {
		return alien.isAlive() 
				&& (alien.getCoordinate().getY() + Constants.ALIEN_HEIGHT) >= battleShip.getCoordinate().getY() && (alien.getCoordinate().getY() + Constants.ALIEN_HEIGHT) <= (battleShip.getCoordinate().getY() + Constants.BATTLE_SHIP_HEIGHT)
				&& alien.getCoordinate().getX() >= (battleShip.getCoordinate().getX() + 20) && alien.getCoordinate().getX() >= (battleShip.getCoordinate().getX() + Constants.BATTLE_SHIP_WIDTH - 20);
	}
	
	private boolean isOutOfScreen(Missile missile) {
		return (missile.getCoordinate().getY() + Constants.MISSILE_HEIGHT) < 0;
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
        g.drawImage(bgImage, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, this);
        
    	g.drawImage(battleShip.getImagePath(), battleShip.getCoordinate().getX(), battleShip.getCoordinate().getY(), Constants.BATTLE_SHIP_WIDTH, Constants.BATTLE_SHIP_HEIGHT, this);
        
    	synchronized (aliens) {
	        for (Alien alien : aliens) {
	        	g.drawImage(alien.getImagePath(), alien.getCoordinate().getX(), alien.getCoordinate().getY(), Constants.ALIEN_WIDTH, Constants.ALIEN_HEIGHT, this);
	        }
    	}
        
        synchronized (missiles) {
	        for (Missile missile : missiles) {
	        	g.drawImage(missile.getImagePath(), missile.getCoordinate().getX(), missile.getCoordinate().getY(), Constants.MISSILE_WIDTH, Constants.MISSILE_HEIGHT, this);
	        }
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println(key);
		if (Constants.VALID_LEFT_KEYS.contains(key) && battleShip.canMoveLeft()) {
			battleShip.moveLeft();
			repaint();
		} else if (Constants.VALID_RIGHT_KEYS.contains(key) && battleShip.canMoveRight()) {
			battleShip.moveRight();
			repaint();
		} else if (Constants.VALID_FIRE_KEYS.contains(key)) {
			int missileX = battleShip.getCoordinate().getX() + (Constants.BATTLE_SHIP_WIDTH / 2);
			int missileY = battleShip.getCoordinate().getY() - (Constants.MISSILE_HEIGHT);
			missiles.add(new Missile(new Coordinate(missileX, missileY)));
			repaint();
		} else if (key == KeyEvent.VK_F11) {
			alienSpeed -= Constants.ALIEN_SPEED_DELTA;
			System.out.println("Increased Alien Speed to " + alienSpeed + "ms");
		} else if (key == KeyEvent.VK_F10) {
			alienSpeed += Constants.ALIEN_SPEED_DELTA;
			System.out.println("Decreased Alien Speed to " + alienSpeed + "ms");
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public GameState getGameState() {
		return currState;
	}
}
