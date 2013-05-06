package powerups;

import weapons.IceWeapon;
import entities.Moveable;
import entities.Offensive;
import entities.Powerup;

/**
 * This is a WeaponUpgrade that changes the Weapon of the object picking it up
 * to a set weapon. It extends the Powerup class.
 * */
public class IcePower extends Powerup{
	
	public IcePower(double posX, double posY) {
		super(posX, posY);
	}


	/**
	 * This method calls the changeWeapon method of the object touching this and
	 * gives it a new weapon
	 * */
	public void pickedUp(Moveable target){
		super.pickedUp(target);
		((Offensive)target).changeWeapon(new IceWeapon(target, false), true);
	}
}
