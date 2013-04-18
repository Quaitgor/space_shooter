package movement;

import moveables.Movable;

public class Straight extends Move{
	//private int testdelta = 0;
	
	public Straight(Movable getOwner, double moveX, double moveY) {
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
