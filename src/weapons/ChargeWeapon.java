package weapons;

import ent_c.Player;
import entities.Entity;
import graphics.GS;

/**
 * ChargeWeapon is an extended Weapon built for shooting variable Shots, depending on its owners chargelevel.
 * this weapon fires diffrent types of shots when the firecommand is fired.
 * */
public class ChargeWeapon extends Weapon {
	
	public ChargeWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	/**
	 * Fires one Projectile, depending on the chargelevel of its owner.
	 * With an added graphical firing-effect if the owner is the player.
	 * 
	 * */
	public void fire() {
		double chargeLevel = ((Player)owner).chargeLevel;
		if(counter <= 0){
			super.fire();
			if(chargeLevel < 500){
				((Player)owner).fire();
			}
			if(chargeLevel <= 1000 && chargeLevel > 500){
				if(owner.equals(GS.player1)){
					((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
				}
				new projectiles.Chargelvl1Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+40, targetY, friendly);
			}
			if(chargeLevel >= 1001 && chargeLevel < 2000){
				if(owner.equals(GS.player1)){
					((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
				}
				new projectiles.Chargelvl2Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+40, targetY, friendly);
			}
			if(chargeLevel >= 2000){
				if(owner.equals(GS.player1)){
					((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
				}
				new projectiles.Chargelvl3Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+70, targetY, friendly);
			}
		}
	}
}
