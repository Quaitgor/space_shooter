package movementV2;

import entities.Entity;

public class Straight extends Move{
	//private int testdelta = 0;
	
	public Straight(Entity getOwner, double moveX, double moveY) {
		super(getOwner);
		nposX = moveX;
		nposY = moveY;
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
