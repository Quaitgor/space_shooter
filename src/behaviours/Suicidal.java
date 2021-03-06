package behaviours;

import movement.TargetPosition;
import entities.*;
import graphics.GS;

/**
 * Attempts to fly the owner into the player upon reaching the specified range
 * and thereby killing him.
 */
public class Suicidal extends Behave {
	double range;
	double maxRange;
	boolean keepDirection = false;
	boolean following = false;
	boolean rotation;
	double weaponrange = 800;
	public Suicidal(Moveable getOwner, double range, double maxRange, boolean ratation) {
		super(getOwner);
		this.rotation = ratation;
		if(maxRange < 100){
			maxRange = 100;
		}
		this.range = range;
		this.maxRange = maxRange;
	}
	public Suicidal(Moveable getOwner, double range, double maxRange) {
		this(getOwner,range,maxRange,false);
	}

	protected void checkMind() {
		if(!keepDirection){
			if (GS.player1 != null){
				double x = GS.player1.posX - owner.posX;
				double y = GS.player1.posY - owner.posY;
				double distance = Math.sqrt(Math.pow(x,  2)+Math.pow(y, 2));
				if(((Offensive)owner).weapon != null && distance < weaponrange && owner.posX > GS.player1.posX){
					((Offensive)owner).fire();
				}
				if(following){
					((TargetPosition)owner.movement).changeTarget(GS.player1.posX, GS.player1.posY);
				}else{
					if(distance < range){
						owner.movement = new TargetPosition(owner, 5, GS.player1.posX, GS.player1.posY, rotation);
						following = true;
					}
				}
				if(distance < maxRange){
					((TargetPosition)owner.movement).changeTarget(owner.posX,owner.posY);
					keepDirection = true;
				}
			}
		}
	}
}
