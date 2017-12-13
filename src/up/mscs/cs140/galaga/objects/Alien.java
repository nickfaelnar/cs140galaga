package up.mscs.cs140.galaga.objects;

import javax.swing.ImageIcon;

import up.mscs.cs140.galaga.constants.AlienState;
import up.mscs.cs140.galaga.constants.Constants;
import up.mscs.cs140.galaga.constants.Coordinate;
import up.mscs.cs140.galaga.constants.GameObject;

/**
 * Enemy Alien Object.
 */
public class Alien extends GameObject {
	
	private AlienState currState = AlienState.ALIVE;
	
	public Alien(Coordinate coordinate) {
		super(coordinate, Constants.ALIEN_DELTA_X, Constants.ALIEN_DELTA_Y);
	}

	@Override
	protected void loadImage() {
		this.imagePath = new ImageIcon(getClass().getClassLoader().getResource(Constants.ALIEN_SPRITE)).getImage();
	}

	@Override
	public void moveLeft() {
		this.coordinate.setX(this.coordinate.getX() - this.deltaX);
		
	}

	@Override
	public void moveRight() {
		this.coordinate.setX(this.coordinate.getX() + this.deltaX);
		
	}
	
	@Override
	public void moveForward() {
		this.coordinate.setY(this.coordinate.getY() + this.deltaY);
	}
	
	@Override
	public void moveBackward() {		}
	
	public boolean canMoveLeft() {
		return (this.coordinate.getX() > 10);
	}
	
	public boolean canMoveRight() {
		return (this.coordinate.getX() < 740);
	}

	public AlienState getCurrState() {
		return currState;
	}

	public void setCurrState(AlienState currState) {
		this.currState = currState;
	}

}
