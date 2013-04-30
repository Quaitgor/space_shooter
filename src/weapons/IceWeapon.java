package weapons;

import entities.Entity;
import graphics.GS;

/**
 * IceWeapon is an extended Weapon built for firing an Ice Projectile.
 * */
public class IceWeapon extends Weapon {
	
	public IceWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 300;
	}

	/**
	 * Fires one Projectile if weapon is ready (delay).
	 * With an added graphical firing-effect if the owner is the player.
	 * */
	public void fire() {
		if(counter <= 0){
			super.fire();
			new projectiles.IceProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
		}
	}
}
