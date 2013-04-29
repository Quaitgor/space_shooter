package projectiles;

import movementV2.TargetPosition;
import entities.*;
import graphics.GS;
import graphics.LayerData2;

public class Chargelvl3Projectile extends Offensive {
	
	public Chargelvl3Projectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		isProjectile = true;
		health = 10;
		setDmg(200);
		// get Data from database?
		hitboxOffset = new int[]{-64, -64, 64, 64};
		mainTexture = new LayerData2(this, "projectile/chargelvl3", 1, 4);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		mainTexture.disableDepth = true;
		movement = new TargetPosition(this, 12, moveX, moveY, true);
		friendly = friendOrFoe;
		addToCollision();
		//addNewLayer(new LayerData2(this, "projectile/glow", 1,1));
    	
    	double[][] animationTest = new double[4][4];
    	animationTest[0][0] = 100;
    	animationTest[1][0] = 0;
    	animationTest[2][0] = 0;

    	animationTest[0][1] = 200;
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 1;

    	animationTest[0][2] = 300;
    	animationTest[1][2] = 0;
    	animationTest[2][2] = 2;
    	
    	animationTest[0][3] = 400;
    	animationTest[1][3] = 0;
    	animationTest[2][3] = 3;

    	mainTexture.animationList.add(animationTest);

		addNewLayer(mainTexture);
	}
}
