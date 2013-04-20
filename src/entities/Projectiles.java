package entities;

import movementV2.Straight;
import observer.Subject;
import weapons.Weapon;

/**
 * Offensive extends Moveable with the weapon Strategic Pattern
 * Objects made with this class can use weapons and have health
 * */
public abstract class Projectiles extends Moveable{
	protected int damage;

	public Projectiles(double newPosX, double newPosY, double moveX, double moveY) {
		super(newPosX, newPosY);
		movement = new Straight(this, moveX, moveY);
	}
	

}
