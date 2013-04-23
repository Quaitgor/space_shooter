package behaviours;

import movementV2.Move;
import entities.Moveable;

public abstract class Behave {
	protected Moveable owner;
	double delta;
	
	Behave(Moveable getOwner){
		this.owner = getOwner;
	}
	
	public void update(){
		delta = owner.delta;
		checkMind();
	}
	protected abstract void checkMind();
	protected void changeMovement(Move newMove){
		owner.movement = newMove;
	}
}
