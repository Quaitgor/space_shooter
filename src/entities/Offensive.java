package entities;

import java.awt.Rectangle;
import weapons.Weapon;

/**
 * Offensive can have weapons, has health and can therefore die.
 * */
public abstract class Offensive extends Moveable{
	public Weapon weapon = null;
	public double[] weaponOffset = new double[]{0,0};
	protected double destroyTimer = 0;

	public Offensive(double newPosX, double newPosY) {
		super(newPosX, newPosY);
		hitbox = new Rectangle();
	}
	/**
	 * Receives new delta-value and sends it to the weapon
	 * and updates the hitbox of the Offensive.
	 * @see Moveable.update(double);
	 */
	public void update(double delta){
		updateHitbox();
		super.update(delta);
		if(weapon != null)weapon.update(delta);
        
	}
	/**
	 * Updates the Hitboxes, to move them with the Offensive.
	 * */
	protected void updateHitbox(){
		hitbox.x = (int)(posX)+ hitboxOffset[0];
		hitbox.y = (int)(posY)+hitboxOffset[1];
		hitbox.width = (int)(posX)+hitboxOffset[2];
		hitbox.height= (int)(posY)+hitboxOffset[3];
	}

	/**
	 * Executes the fire()-method of the assigned weapon.
	 * @see Entity.update(double);
	 * */
	public void fire(){
		this.weapon.fire();
	}
	
	/**
	 * Removes this object from the Observer and the game.
	 * */
	public void unsubscribe(){
		super.unsubscribe();
		weapon = null;
	}
	
	/**
	 * Replaces the current weapon with the new one.
	 * */
	public void changeWeapon(Weapon newWeapon, boolean friendlyToPlayer){
		this.weapon = newWeapon;
		weapon.friendly = friendlyToPlayer;
	}

}
