package weapons;

import ent_c.Player;
import entities.Entity;
import graphics.GS;

public class ChargeWeapon extends Weapon {
	
	public ChargeWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	public void fire() {
		double chargeLevel = ((Player)owner).chargeLevel;
		if(counter <= 0){
			super.fire();
			if(chargeLevel < 500){
				((Player)owner).fire();
			}
			if(chargeLevel <= 1000 && chargeLevel > 500){
				((Player)owner).playFireAnimation(((Player)owner).projectileFireCharge);
				new projectiles.Chargelvl1Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+40, targetY, friendly);
			}
			if(chargeLevel >= 1001 && chargeLevel < 2000){
				((Player)owner).playFireAnimation(((Player)owner).projectileFireCharge);
				new projectiles.Chargelvl2Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+40, targetY, friendly);
			}
			if(chargeLevel >= 2000){
				((Player)owner).playFireAnimation(((Player)owner).projectileFireCharge);
				new projectiles.Chargelvl3Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+70, targetY, friendly);
			}
		}
	}
}
