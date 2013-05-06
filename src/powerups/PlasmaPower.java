package powerups;

/**
 * This is a WeaponUpgrade that changes the Weapon of the object picking it up
 * to a set weapon. It extends the Powerup class.
 * */
import weapons.PlasmaWeapon;
import entities.Moveable;
import entities.Offensive;
import entities.Powerup;

public class PlasmaPower extends Powerup{
	
	public PlasmaPower(double posX, double posY) {
		super(posX, posY);
	}
	
	/**
	 * This method calls the changeWeapon method of the object touching this and
	 * gives it a new weapon
	 * */
	public void pickedUp(Moveable target){
		super.pickedUp(target);
		((Offensive)target).changeWeapon(new PlasmaWeapon(target, false), true);
	}
}
