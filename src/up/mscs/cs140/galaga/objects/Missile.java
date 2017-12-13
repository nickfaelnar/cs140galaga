package up.mscs.cs140.galaga.objects;

import javax.swing.ImageIcon;

import up.mscs.cs140.galaga.constants.Constants;
import up.mscs.cs140.galaga.constants.Coordinate;
import up.mscs.cs140.galaga.constants.GameObject;

/**
 * Bullet Object
 */
public class Missile extends GameObject {

	public Missile(Coordinate coordinate) {
		super(coordinate, Constants.MISSILE_DELTA_X, Constants.MISSILE_DELTA_Y);
	}

	@Override
	protected void loadImage() {
		this.imagePath = new ImageIcon(getClass().getClassLoader().getResource(Constants.MISSILE_SPRITE)).getImage();
	}

	@Override
	public void moveLeft() {}

	@Override
	public void moveRight() {}

	@Override
	public void moveForward() {
		this.coordinate.setY(this.coordinate.getY() - this.deltaY);
	}

	@Override
	public void moveBackward() {}
	
}
