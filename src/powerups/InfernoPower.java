package powerups;

import weapons.InfernoWeapon;
import entities.Moveable;
import entities.Offensive;
import entities.Powerup;

/**
 * This is a WeaponUpgrade that changes the Weapon of the object picking it up
 * to a set weapon. It extends the Powerup class.
 * */
public class InfernoPower extends Powerup{
	
	public InfernoPower(double posX, double posY) {
		super(posX, posY);
		System.out.println("created inf "+posX+" / "+posY);
	}


	/**
	 * This method calls the changeWeapon method of the object touching this and
	 * gives it a new weapon
	 * */
	public void pickedUp(Moveable target){
		super.pickedUp(target);
		((Offensive)target).changeWeapon(new InfernoWeapon(target, false), true);
	}
}
