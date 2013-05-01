package projectiles;

import movementV2.TargetPosition;
import entities.*;
import graphics.GS;
import graphics.LayerData2;

public class BossProjectile extends Offensive {
	
	public BossProjectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		isProjectile = true;
		health = 1;
		setDmg(10);
		// get Data from database?
		hitboxOffset = new int[]{-26, -26, 26, 26};
		mainTexture = new LayerData2(this, "projectile/boss", 1, 4);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		mainTexture.disableDepth = true;
		movement = new TargetPosition(this, 9, moveX, moveY, true);
		friendly = friendOrFoe;
		addToCollision();
    	
    	double[][] animationTest = new double[3][3];
    	animationTest[0][0] = 100;
    	animationTest[1][0] = 0;
    	animationTest[2][0] = 0;

    	animationTest[0][1] = 200;
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 1;

    	animationTest[0][2] = 300;
    	animationTest[1][2] = 0;
    	animationTest[2][2] = 2;

    	mainTexture.animationList.add(animationTest);

		addNewLayer(mainTexture);
	}
}
