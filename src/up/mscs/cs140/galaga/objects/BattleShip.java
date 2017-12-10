package up.mscs.cs140.galaga.objects;

import up.mscs.cs140.galaga.constants.Constants;

/**
 * Battle Ship Object.
 */
public class BattleShip {

	private String imagePath = Constants.BATTLESHIP_IMG_PATH;
	private int xCoor;
	private int yCoor;
	
	public BattleShip(int xCoor, int yCoor) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
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

}
