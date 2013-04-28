package weapons;

import ent_c.Player;
import entities.Entity;
import graphics.GS;

public class DefaultWeapon extends Weapon {
	
	public DefaultWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 100;
	}

	public void fire() {
		if(counter <= 0){
			super.fire();
			if(owner.equals(GS.player1)){
				((Player)owner).playFireAnimation(((Player)owner).projectileFireDefault);
			}
			new projectiles.DefaultProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
		}
	}
}
