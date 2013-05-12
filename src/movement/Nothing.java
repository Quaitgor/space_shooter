package movement;

import entities.Entity;

/**
 * Does not move at all.
 * Used when an object has to move absolutly nothing.
 * Used for testing purposes.
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
