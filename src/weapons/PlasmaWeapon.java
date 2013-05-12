package weapons;

import ent_c.Player;
import entities.Entity;
import graphics.GS;

/**
 * fires three PlasmaProjectiles.
 * */
public class PlasmaWeapon extends Weapon {
	
	public PlasmaWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	/**
	 * Fires 3 projectiles at once, if the weapon is ready (delay).
	 * With an added graphical firing-effect if the owner is the player.
	 * */
	public void fire() {
		if(counter <= 0){
			super.fire();
			if(owner.equals(GS.player1)){
				((Player)owner).playFireAnimation("projectile/projectilefirePlasma", 0);
			}
			new projectiles.PlasmaProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
			new projectiles.PlasmaProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY+1, friendly);
			new projectiles.PlasmaProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY-1, friendly);
		}
	}
}
