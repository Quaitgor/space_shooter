package movementV2;

import entities.Entity;

public class StraightTrigonometry extends Move{
	
	public StraightTrigonometry(Entity getOwner, double speed, double angle) {
		super(getOwner);
		angle = (angle/180)*Math.PI;
		nposX = Math.cos(angle)*speed;
		nposY = Math.sin(angle)*speed;
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
