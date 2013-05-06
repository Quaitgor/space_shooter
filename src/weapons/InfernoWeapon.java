package weapons;

import java.lang.reflect.Constructor;

import projectiles.FireProjectile;
import movementV2.Move;
import movementV2.SinusWave;
import movementV2.StraightAhead;
import ent_c.Player;
import entities.Entity;
import graphics.GS;

/**
 * InfernoWeapon is an extended Weapon built for firing a fireshot, built out of 5 projectiles, moving in a pattern
 * */
public class InfernoWeapon extends Weapon {
	
	public InfernoWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	/**
	 * Firing 5 Fire Projectiles at once if the weapon is ready (delay),
	 * each modified to a certain movement pattern. they fly as a bundle of projectiles.
	 * With an added graphical firing-effect if the owner is the player.
	 * */
	public void fire() {
		if(counter <= 0){
			super.fire();
			if(owner.equals(GS.player1)){
				((Player)owner).playFireAnimation("projectile/projectileireInferno", 0);
			}

			Constructor<? extends Move> f0Constructor = (Constructor<? extends Move>) SinusWave.class.getConstructors()[0];
			Constructor<? extends Move> f1Constructor = (Constructor<? extends Move>) StraightAhead.class.getConstructors()[0];
			Object[] f0args = {null, 5, 65};
			Object[] f1args = {null, 5, 40};
			Object[] f2args = {null, -5};
			Object[] f3args = {null, 5,-40};
			Object[] f4args = {null, 5,-65};
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f0Constructor, f0args, friendly);
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f0Constructor, f1args, friendly);
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f1Constructor, f2args, friendly);
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f0Constructor, f3args, friendly);
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f0Constructor, f4args, friendly);
		}
	}
}
