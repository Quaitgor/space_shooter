package projectiles;

import movementV2.TargetPosition;
import entities.*;
import entities_decor.ExplodeVar;
import graphics.LayerData2;

/**
 * This is a Projectile with a set graphical design, movement and damage,
 * depending on the boolean value in the constructor its either friendly or 
 * enemy.
 * */
public class EnemyDefaultProjectile extends Offensive {
	
	public EnemyDefaultProjectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		isProjectile = true;
		hitboxOffset = new int[]{-8, -8, 8, 8};
		mainTexture = new LayerData2(this, "projectile/enemylvl0", 1, 1);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		movement = new TargetPosition(this, 9, moveX, moveY, true);
		friendly = friendOrFoe;
		deathSprite = "explosion/defaultexplosion";
		addNewLayer(mainTexture);
		addToCollision();
	}
	protected void death(){
		new ExplodeVar(posX, posY, "explosion/enemysmall");
		unsubscribe();
	}
}
