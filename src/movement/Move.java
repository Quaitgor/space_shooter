package movement;

import entities.Entity;

/** Move is the superclass of the movement Strategy Pattern, objects extending this class will be able
 * to be used as movement pattern for movement on (and off)screen. The movement Strategy Pattern has direct
 * control over its owner, allowing it control more than only Position X/Y if needed,
 * even allowing to replace itself with another movement pattern.
 */
public abstract class Move {
	public Entity owner;
	protected double nposX;
	protected double nposY;
	
	protected Move(Entity getOwner){
		this.owner = getOwner;
	}
	
	/**
	 * move() is called with every update on an Entity using the movement Strategy Pattern.
	 * Every Update Move calulates if any movement is necessary and executes that movement.
	 * */
	public void move(){
		calculateMove();
		makeMove();
	};
	
	/**
	 * this method is called before making a visible movement,
	 * in this method objects extending this class will calulate before they
	 * make any change visible to the player with makeMove()
	 * */
	protected abstract void calculateMove();
	/**
	 * this method is called after calulateMove and is the final step
	 * where to the player visible actions happen.
	 * */
	protected abstract void makeMove();
}
