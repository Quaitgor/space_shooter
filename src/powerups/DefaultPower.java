package powerups;

import weapons.DefaultWeapon;
import entities.Moveable;
import entities.Offensive;
import entities.Powerup;

public class DefaultPower extends Powerup{
	
	public DefaultPower(double posX, double posY) {
		super(posX, posY);
	}

	public void pickedUp(Moveable target){
		super.pickedUp(target);
		((Offensive)target).changeWeapon(new DefaultWeapon(target, false));
	}
}
