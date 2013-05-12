package movementV2;

import entities.Entity;

/**
 * Does not move at all.
 */
public class Nothing extends Move{
	
	public Nothing(Entity getOwner) {
		super(getOwner);
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
	}
}
