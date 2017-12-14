package up.mscs.cs140.galaga.constants;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

public class Constants {

	public static String GAME_NAME = "CS140 Galaga Game";

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	
	public static final String INIT_SCREEN_BG = "resources/init_bg.gif";
	public static final String INIT_SCREEN_TITLE = "resources/init_title.png";
	
	public static final String GAME_SCREEN_BG = "resources/game_bg.gif";
	
	public static final String ALIEN_SPRITE = "resources/alien.gif";
	public static final int ALIEN_WIDTH = 50;
	public static final int ALIEN_HEIGHT = 25;
	public static final int ALIEN_DELTA_X = 20;
	public static final int ALIEN_DELTA_Y = 20;
	public static final int ALIEN_INIT_SPEED = 200;
	public static final int ALIEN_SPEED_DELTA = 20;
	public static final int ALIEN_EXPLOSION_DURATION = 500;
	public static final AlienMovement ALIEN_INIT_MOVEMENT = AlienMovement.LEFT;
	
	public static final String BATTLE_SHIP_SPRITE = "resources/fighter.gif";
	public static final int BATTLE_SHIP_WIDTH = 50;
	public static final int BATTLE_SHIP_HEIGHT = 60;
	public static final int BATTLE_SHIP_DELTA_X = 20;
	public static final int BATTLE_SHIP_DELTA_Y = 0;
	public static final int BATTLE_SHIP_INIT_X = 350;
	public static final int BATTLE_SHIP_INIT_Y = 500;
	public static final int BATTLE_SHIP_SPEED_DELTA = 10;
	
	public static final String MISSILE_SPRITE = "resources/missile.gif";
	public static final int MISSILE_WIDTH = 10;
	public static final int MISSILE_HEIGHT = 20;
	public static final int MISSILE_DELTA_X = 10;
	public static final int MISSILE_DELTA_Y = 10;
	public static final int MISSILE_INIT_SPEED = 50;
	public static final int MISSILE_SPEED_DELTA = 15;
	
	public static final String EXPLOSION_SPRITE = "resources/explosion.gif";
	
	public static final int GAME_STATE_CHECK_INTERVAL = 500;
	
	public static final List<Integer> VALID_LEFT_KEYS = Arrays.asList(KeyEvent.VK_LEFT, KeyEvent.VK_A);
	public static final List<Integer> VALID_RIGHT_KEYS = Arrays.asList(KeyEvent.VK_RIGHT, KeyEvent.VK_D);
	public static final List<Integer> VALID_FIRE_KEYS = Arrays.asList(KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_SPACE);
	
}
