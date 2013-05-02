package weapons;

import entities.Entity;
import graphics.GS;

/**
 * BossWeapon is an extended Weapon built for firing shots designed for the boss-enemy
 * */
public class BossWeapon extends Weapon {
	
	public BossWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	/**
	 * Fires the projectile
	 * */
	public void fire() {
		if(counter <= 0){
			super.fire();
			new projectiles.BossProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
		}
	}
}
