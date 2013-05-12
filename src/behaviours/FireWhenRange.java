package behaviours;

import entities.*;
import graphics.GS;

/**
 * Fires when the owner has reached the specified distance to the player.
 */
public class FireWhenRange extends Behave {
	private boolean once = false;
	double range = 0;
	
	public FireWhenRange(Moveable getOwner, double range) {
		super(getOwner);
		this.range = range;
	}

	protected void checkMind() {
		if (GS.player1 != null){
			double x = GS.player1.posX - owner.posX;
			double y = GS.player1.posY - owner.posY;
			double distance = Math.sqrt(Math.pow(x,  2)+Math.pow(y, 2));
			if(distance < range){
				if (!once){
					((Offensive)owner).fire();
				}
			}
			
		}
		
	}

}
