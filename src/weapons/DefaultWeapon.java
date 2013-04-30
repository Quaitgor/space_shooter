package weapons;

import ent_c.Player;
import entities.Entity;
import graphics.GS;

/**
 * DefaultWeapon is an extended Weapon built for firing an small Projectile,
 * its the default weapon of the player
 * */
public class DefaultWeapon extends Weapon {
	
	public DefaultWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 100;
	}
	
	/**
	 * Fires one small, fast projectile.
	 * With an added graphical firing-effect if the owner is the player.
	 * */
	public void fire() {
		if(counter <= 0){
			super.fire();
			if(owner.equals(GS.player1)){
				((Player)owner).playFireAnimation("projectile/projectilefireCharge", 1);
			}
			new projectiles.DefaultProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
		}
	}
}
