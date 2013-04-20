package entities;

import weapons.Weapon;

/**
 * Offensive extends Moveable with the weapon Strategic Pattern
 * Objects made with this class can use weapons and have health
 * */
public abstract class Offensive extends Moveable{
	public Weapon weapon;
	protected int health;
	protected int offsetX = 0;
	protected int offsetY = 0;

	public Offensive(double newPosX, double newPosY) {
		super(newPosX, newPosY);
	}
	
	/**
	 * fire() uses the weapons fire() method to fire the weapon
	 * with the weapon Strategy Pattern the weapon can be switched
	 * */
	public void fire(){
		this.weapon.fire();
	}

}
