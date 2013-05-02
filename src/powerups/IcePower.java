package powerups;

import weapons.IceWeapon;
import entities.Moveable;
import entities.Offensive;
import entities.Powerup;

public class IcePower extends Powerup{
	
	public IcePower(double posX, double posY) {
		super(posX, posY);
	}

	public void pickedUp(Moveable target){
		super.pickedUp(target);
		((Offensive)target).changeWeapon(new IceWeapon(target, false));
	}
}
