package up.mscs.cs140.galaga.objects;

import up.mscs.cs140.galaga.constants.Constants;

/**
 * Bullet Object
 */
public class Bullet {

	private String imagePath = Constants.BULLET_IMG_PATH;
	private int xCoor;
	private int yCoor;
	
	public Bullet(int xCoor, int yCoor) {
		this.setxCoor(xCoor);
		this.setyCoor(yCoor);
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	
}
