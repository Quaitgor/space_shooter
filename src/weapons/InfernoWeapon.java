package weapons;

import java.lang.reflect.Constructor;

import projectiles.FireProjectile;
import movementV2.Move;
import movementV2.RandomStraightTrig;
import movementV2.SinusWave;
import movementV2.Straight;
import entities.Entity;
import graphics.GS;

public class InfernoWeapon extends Weapon {
	
	public InfernoWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	public void fire() {
		if(counter <= 0){
			super.fire();

			Constructor<? extends Move> f0Constructor = (Constructor<? extends Move>) SinusWave.class.getConstructors()[0];
			Constructor<? extends Move> f1Constructor = (Constructor<? extends Move>) Straight.class.getConstructors()[0];
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