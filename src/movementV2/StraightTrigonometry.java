package movementV2;

import entities.Entity;

/**
 * Moves straight into the direction determined by the specified angle.
 */
public class StraightTrigonometry extends Move{
	
	public StraightTrigonometry(Entity getOwner, double speed, double angle) {
		super(getOwner);
		angle = (angle/180)*Math.PI;
		nposX = Math.cos(angle)*speed;
		nposY = Math.sin(angle)*speed;
	}
	
	/**
	 * This method makes necessary recalulation before moving anything.
	 * Not needed in this Movement
	 * */
	protected void calculateMove(){
	}
	
	/**
	 * This method sends the final movement command
	 * */
	protected void makeMove(){
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
