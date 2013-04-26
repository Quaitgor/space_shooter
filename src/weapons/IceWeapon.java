package weapons;

import entities.Entity;
import graphics.GS;

public class IceWeapon extends Weapon {
	
	public IceWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 300;
	}

	public void fire() {
		if(counter <= 0){
			super.fire();
			new projectiles.IceProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
		}
	}
}
