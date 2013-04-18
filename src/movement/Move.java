package movement;

import moveables.Movable;

public abstract class Move {
	protected Movable owner;
	protected double nposX;
	protected double nposY;
	Move(Movable getOwner){
		this.owner = getOwner;
	}
	public void move(){
		calculateMove();
		makeMove();
	};
	protected void calculateMove(){};
	protected void makeMove(){};
}
