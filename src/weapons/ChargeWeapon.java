package weapons;

import ent_c.Player;
import entities.Entity;

/**
 * shoots variable Projectiles, depending on its owners chargelevel.
 * It is designed for the player and not for enemys.
 * */
public class ChargeWeapon extends Weapon {
	
	public ChargeWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	/**
	 * Fires one Projectile, depending on the chargelevel of the player.
	 * */
	public void fire() {
		if(counter <= 0){
			double chargeLevel = ((Player)owner).chargeLevel;
			super.fire();
			if(chargeLevel < 500){
				((Player)owner).fire();
			}
			if(chargeLevel <= 1000 && chargeLevel > 500){
				((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
				new projectiles.Chargelvl1Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+40, targetY, friendly);
			}
			if(chargeLevel >= 1001 && chargeLevel < 2000){
				((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
				new projectiles.Chargelvl2Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+40, targetY, friendly);
			}
			if(chargeLevel >= 2000){
				((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
				new projectiles.Chargelvl3Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+70, targetY, friendly);
			}
		}
	}
}
