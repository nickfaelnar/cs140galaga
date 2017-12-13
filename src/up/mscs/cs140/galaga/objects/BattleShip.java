package up.mscs.cs140.galaga.objects;

import java.awt.Image;

import javax.swing.ImageIcon;

import up.mscs.cs140.galaga.constants.Constants;
import up.mscs.cs140.galaga.constants.Coordinate;
import up.mscs.cs140.galaga.constants.GameObject;

/**
 * Battle Ship Object.
 */
public class BattleShip extends GameObject {
	
	public BattleShip(Coordinate coordinate) {
		super(coordinate, Constants.BATTLE_SHIP_DELTA_X, Constants.BATTLE_SHIP_DELTA_Y);
	}

	@Override
	protected void loadImage() {
		this.imagePath = new ImageIcon(getClass().getClassLoader().getResource(Constants.BATTLE_SHIP_SPRITE)).getImage();
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
	public void moveForward() {}

	@Override
	public void moveBackward() {}
	
	public boolean canMoveLeft() {
		return (this.coordinate.getX() > 10);
	}
	
	public boolean canMoveRight() {
		return (this.coordinate.getX() < 740);
	}

}
