package movementV2;

import java.util.Random;

import entities.Entity;

public class Straight extends Move{
	
	public Straight(Entity getOwner, double speed) {
		super(getOwner);
		nposX = speed;
		nposY = 0;
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
