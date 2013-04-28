package weapons;

import java.lang.reflect.Constructor;

import projectiles.Fire;
import projectiles.FireProjectile;
import movementV2.Move;
import movementV2.RandomStraightTrig;
import movementV2.SinusWave;
import movementV2.Straight;
import entities.Entity;
import graphics.GS;

public class Inferno extends Weapon {
	
	public Inferno(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
	}

	public void fire() {
		if(counter <= 0){
			super.fire();

			Constructor<? extends Move> f0Constructor = (Constructor<? extends Move>) SinusWave.class.getConstructors()[0];
			Constructor<? extends Move> f1Constructor = (Constructor<? extends Move>) Straight.class.getConstructors()[0];
			Object[] f0args = {null, 5,75};
			Object[] f1args = {null, 5, 50};
			Object[] f2args = {null, -5};
			Object[] f3args = {null, 5,-50};
			Object[] f4args = {null, 5,-75};
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f0Constructor, f0args, targetPlayer);
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f0Constructor, f1args, targetPlayer, "projectile/plasma");
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f1Constructor, f2args, targetPlayer);
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f0Constructor, f3args, targetPlayer, "projectile/plasma");
			new FireProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, f0Constructor, f4args, targetPlayer);
		}
	}
}
