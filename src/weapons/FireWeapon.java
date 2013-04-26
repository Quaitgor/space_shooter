package weapons;

import entities.Entity;
import graphics.GS;

public class FireWeapon extends Weapon {
	
	public FireWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	public void fire() {
		if(counter <= 0){
			super.fire();
			new projectiles.FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
		}
	}
}
