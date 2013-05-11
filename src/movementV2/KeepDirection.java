package movementV2;

import entities.Entity;

/**
 * moves an Moveable to the specified position and keeps it moving.
 */
public class KeepDirection extends Move{
	
	public KeepDirection(Entity getOwner, double nposX, double nposY) {
		super(getOwner);
		this.nposX = nposX;
		this.nposY = nposY;
	}
	
	/**
	 * does necessary calculations before moving anything.
	 * Not needed in this movement.
	 * */
	protected void calculateMove(){
	}
	
	/**
	 * sends the final movement command
	 * */
	protected void makeMove(){
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
