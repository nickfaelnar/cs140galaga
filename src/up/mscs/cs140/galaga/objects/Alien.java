package up.mscs.cs140.galaga.objects;

import javax.swing.ImageIcon;

import up.mscs.cs140.galaga.constants.Constants;
import up.mscs.cs140.galaga.constants.Coordinate;
import up.mscs.cs140.galaga.constants.GameObject;

/**
 * Enemy Alien Object.
 */
public class Alien extends GameObject {
	
	public Alien(Coordinate coordinate) {
		super(coordinate, Constants.ALIEN_DELTA_X, Constants.ALIEN_DELTA_Y);
	}

	@Override
	protected void loadImage() {
		this.imagePath = new ImageIcon(getClass().getClassLoader().getResource(Constants.ALIEN_SPRITE)).getImage();
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}
	
	public void moveForward() {
		
	}

}
