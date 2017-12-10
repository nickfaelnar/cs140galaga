package up.mscs.cs140.galaga.objects;

import up.mscs.cs140.galaga.constants.Constants;

/**
 * Enemy Alien Object.
 */
public class Alien {
	
	private String imagePath = Constants.ALIEN_IMG_PATH;
	private int xCoor = 0;
	private int yCoor = 0;
	
	public Alien(int xCoor, int yCoor) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
	}
	
	public int getPositionX() {
		return xCoor;
	}
	
	public void setPositionX(int positionX) {
		this.xCoor = positionX;
	}
	
	public int getPositionY() {
		return yCoor;
	}
	
	public void setPositionY(int positionY) {
		this.yCoor = positionY;
	}

}
