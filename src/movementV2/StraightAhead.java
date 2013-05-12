package movementV2;

import entities.Entity;

/**
 * moves the object on the X axis whith the specified speed.
 */
public class StraightAhead extends Move{
	
	public StraightAhead(Entity getOwner, double speed) {
		super(getOwner);
		nposX = speed;
		nposY = 0;
	}
	
	/**
	 * Not needed in this movement.
	 * */
	protected void calculateMove(){
	}
	
	/**
	 * @see Move.makeMove()
	 * */
	protected void makeMove(){
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
