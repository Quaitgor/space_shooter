package movementV2;

import entities.Entity;

/**
 * calculates and realizes the movement pattern of its owner.
 * Instances are used as strategies within a Moveable, therefore the movement
 * pattern of any Moveable is replaceable.
 */
public abstract class Move {
	public Entity owner;
	protected double nposX;
	protected double nposY;
	
	protected Move(Entity getOwner){
		this.owner = getOwner;
	}
	
	/**
	 * Called upon every update of the owner, to calculate the next position shift
	 * and realizes it.
	 * */
	public void move(){
		calculateMove();
		makeMove();
	};
	
	/**
	 * calculates the eventual following position shift.
	 * */
	protected abstract void calculateMove();
	/**
	 * realizes one position shift.
	 * */
	protected abstract void makeMove();
}
