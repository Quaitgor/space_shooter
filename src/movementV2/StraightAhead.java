package movementV2;

import java.util.Random;

import entities.Entity;

/**
 * Moves straight ahead.
 * @author philipp
 *
 */
public class StraightAhead extends Move{
	
	public StraightAhead(Entity getOwner, double speed) {
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
