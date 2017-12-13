package up.mscs.cs140.galaga.constants;

import java.awt.Image;

public abstract class GameObject {
	
	protected Image imagePath;
	protected Coordinate coordinate;
	protected int deltaX;
	protected int deltaY;
	
	public GameObject(Coordinate coordinate, int deltaX, int deltaY) {
		this.coordinate = coordinate;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		loadImage();
	}
	
	protected abstract void loadImage();
	
	public abstract void moveLeft();
	
	public abstract void moveRight();
	
	public abstract void moveForward();
	
	public abstract void moveBackward();

	public Image getImagePath() {
		return imagePath;
	}

	public void setImagePath(Image imagePath) {
		this.imagePath = imagePath;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

}
