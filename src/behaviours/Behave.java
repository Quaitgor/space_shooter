package behaviours;

import moveables.Movable;

public abstract class Behave {
	protected Movable owner;
	
	Behave(Movable getOwner){
		this.owner = getOwner;
	}
	
	protected void changeMove(){};
}
