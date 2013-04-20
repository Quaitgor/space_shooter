package entities;

/** Move is the superclass of the movement Strategy Pattern, objects extending this class will be able to be used as movement pattern for movement on (and off)screen
 * the movement Strategy Pattern has direct control over its owner, allowing it control more than only Position X/Y if needed, even allowing to replace itself with another movement pattern
 */
public abstract class Move {
	protected Entity owner;
	protected double nposX;
	protected double nposY;
	
	protected Move(Entity getOwner){
		this.owner = getOwner;
	}
	
	/**
	 * move() is called with every update on an Entity using the movement Strategy Pattern
	 * every Update Move calulates if any movement is nessesary and executes that movement
	 * */
	public void move(){
		calculateMove();
		makeMove();
	};
	
	protected abstract void calculateMove();
	protected abstract void makeMove();
}
