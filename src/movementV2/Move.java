package movementV2;

import entities.*;

public abstract class Move {
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
