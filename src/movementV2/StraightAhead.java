package movementV2;

import entities.Entity;

/**
 * This movement moves the object on the X axis on a set speed.
 */
public class StraightAhead extends Move{
	
	public StraightAhead(Entity getOwner, double speed) {
		super(getOwner);
		nposX = speed;
		nposY = 0;
	}
	
	/**
	 * This method makes necessary recalulation before moving anything.
	 * Not needed in this movement.
	 * */
	protected void calculateMove(){
	}
	
	/**
	 * This method sends the final movement command
	 * */
	protected void makeMove(){
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
