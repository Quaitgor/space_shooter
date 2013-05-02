package powerups;

import weapons.PlasmaWeapon;
import entities.Moveable;
import entities.Offensive;
import entities.Powerup;

public class PlasmaPower extends Powerup{
	
	public PlasmaPower(double posX, double posY) {
		super(posX, posY);
	}

	public void pickedUp(Moveable target){
		super.pickedUp(target);
		((Offensive)target).changeWeapon(new PlasmaWeapon(target, false));
	}
}
