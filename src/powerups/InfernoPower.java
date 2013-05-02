package powerups;

import weapons.InfernoWeapon;
import entities.Moveable;
import entities.Offensive;
import entities.Powerup;

public class InfernoPower extends Powerup{
	
	public InfernoPower(double posX, double posY) {
		super(posX, posY);
	}

	public void pickedUp(Moveable target){
		super.pickedUp(target);
		((Offensive)target).changeWeapon(new InfernoWeapon(target, false));
	}
}
