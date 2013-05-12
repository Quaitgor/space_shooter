package behaviours;

import movementV2.Move;
import entities.Moveable;

/**
 * Defines the Behaviour of its owner.
 * Usually used to regularly change the owners movement-pattern and delaying the 
 * frequency with which he fires his weapon.
 *
 */
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
