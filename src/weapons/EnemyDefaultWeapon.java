package weapons;

import entities.Entity;

/**
 * fires a small Projectile, its the default weapon of the enemy.
 * With the level in the constructor the type of Projectile is defined.
 * */
public class EnemyDefaultWeapon extends Weapon {
	private int weaponlevel;
	
	/**
	 * @param level defines the type of the Projectile.
	 */
	public EnemyDefaultWeapon(Entity owner, boolean targetPlayer, int level) {
		super(owner, targetPlayer);
		weaponDelay = 800;
		weaponlevel = level;
	}
	
	/**
	 * Fires one Projectile.
	 * */
	public void fire() {
		if(counter <= 0){
			super.fire();
			if(weaponlevel == 0) new projectiles.EnemyDefaultProjectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
			if(weaponlevel == 1) new projectiles.Enemylvl1(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
			if(weaponlevel == 2) new projectiles.Enemylvl2(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY, friendly);
		}
	}
}
