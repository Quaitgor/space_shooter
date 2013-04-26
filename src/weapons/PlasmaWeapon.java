package weapons;

import entities.Entity;
import graphics.GS;

public class PlasmaWeapon extends Weapon {
	
	public PlasmaWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	public void fire() {
		if(counter <= 0){
			super.fire();
			new projectiles.PlasmaProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
			new projectiles.PlasmaProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY+1, friendly);
			new projectiles.PlasmaProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY-1, friendly);
		}
	}
}
