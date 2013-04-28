package projectiles;

import movementV2.TargetPosition;
import entities.*;
import graphics.GS;
import graphics.LayerData2;

public class Chargelvl2Projectile extends Offensive {
	
	public Chargelvl2Projectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		
		setDmg(80);
		// get Data from database?
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture = new LayerData2(this, "projectile/chargelvl2", 1, 4);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		mainTexture.disableDepth = true;
		movement = new TargetPosition(this, 12, moveX, moveY, true);
		friendly = friendOrFoe;
		addToCollision();
		//addNewLayer(new LayerData2(this, "projectile/glow", 1,1));
    	
    	double[][] animationTest = new double[6][6];
    	animationTest[0][0] = 100;
    	animationTest[1][0] = 0;
    	animationTest[2][0] = 2;

    	animationTest[0][1] = 200;
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 3;

    	animationTest[0][2] = 300;
    	animationTest[1][2] = 0;
    	animationTest[2][2] = 0;

    	animationTest[0][3] = 400;
    	animationTest[1][3] = 0;
    	animationTest[2][3] = 1;
    	animationTest[0][4] = 500;
    	animationTest[1][4] = 0;
    	animationTest[2][4] = 0;
    	animationTest[0][5] = 600;
    	animationTest[1][5] = 0;
    	animationTest[2][5] = 3;

    	mainTexture.animationList.add(animationTest);

		addNewLayer(mainTexture);
	}
}
