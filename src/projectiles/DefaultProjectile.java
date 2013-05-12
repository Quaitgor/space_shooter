package projectiles;

import movementV2.TargetPosition;
import entities.*;
import graphics.LayerData2;

/**
 * This is a Projectile with a set graphical design, movement and damage,
 * depending on the boolean value in the constructor its either friendly or 
 * enemy.
 * */
public class DefaultProjectile extends Offensive {
	
	public DefaultProjectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		isProjectile = true;
		setDmg(5);
		hitboxOffset = new int[]{-8, -8, 8, 8};
		mainTexture = new LayerData2(this, "projectile/chargelvl0", 1, 1);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		movement = new TargetPosition(this, 12, moveX, moveY, true);
		friendly = friendOrFoe;
		deathSprite = "explosion/defaultexplosion";
		addNewLayer(mainTexture);
		addToCollision();
	}
}
