package projectiles;

import movementV2.TargetPosition;
import entities.*;
import entities_decor.Explode;
import graphics.GS;
import graphics.LayerData2;

public class DefaultProjectile extends Offensive {
	
	public DefaultProjectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		
		setDmg(15);
		// get Data from database?
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture = new LayerData2(this, "projectile/chargelvl0", 1, 1);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		mainTexture.disableDepth = true;
		movement = new TargetPosition(this, 12, moveX, moveY, true);
		friendly = friendOrFoe;
		deathSprite = "explosion/iceexplosion";
		addToCollision();
		addNewLayer(mainTexture);
	}
}
