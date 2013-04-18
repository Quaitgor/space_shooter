package movementV2;

import entities.*;

/**
 * @author  vmadmin
 */
public abstract class Move {
	/**
	 * @uml.property  name="owner"
	 * @uml.associationEnd  
	 */
	protected Entity owner;
	protected double nposX;
	protected double nposY;
	
	Move(Entity getOwner){
		this.owner = getOwner;
	}
	public void move(){
		calculateMove();
		makeMove();
	};
	
	protected abstract void calculateMove();
	protected abstract void makeMove();
}
