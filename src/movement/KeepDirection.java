package movement;

import entities.Entity;

/**
 * This movement moves an object to the set location and keeps it moving
 */
public class KeepDirection extends Move{
	
	public KeepDirection(Entity getOwner, double nposX, double nposY) {
		super(getOwner);
		this.nposX = nposX;
		this.nposY = nposY;
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
