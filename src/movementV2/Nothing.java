package movementV2;

import java.util.Random;

import entities.Entity;
import graphics.GS;

/**
 * Does not move at all.
 * @author philipp
 *
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
