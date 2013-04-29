package projectiles;

import movementV2.TargetPosition;
import entities.*;
import entities_decor.ExplodeVar;
import graphics.LayerData2;

public class PlasmaProjectile extends Offensive {
	
	public PlasmaProjectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		isProjectile = true;
		
		setDmg(80);
		// get Data from database?
		mainTexture = new LayerData2(this, "projectile/plasma", 4, 1);
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		movement = new TargetPosition(this, 8, moveX, moveY, true);
		friendly = friendOrFoe;
		deathSprite = "explosion/plasmaexplosion";
		addToCollision();
		//addNewLayer(new LayerData2(this, "projectile/glow", 1,1));

    	double[][] animationTest = new double[3][3];
		
    	animationTest[0][0] = 100;
    	animationTest[1][0] = 1;
    	animationTest[2][0] = 0;

    	animationTest[0][1] = 200;
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 0;

    	animationTest[0][2] = 300;
    	animationTest[1][2] = 1;
    	animationTest[2][2] = 0;

    	mainTexture.animationList.add(animationTest);

		addNewLayer(mainTexture);
		
	}
}
