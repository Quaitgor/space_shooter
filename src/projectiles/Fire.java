package projectiles;

import entities.*;
import graphics.LayerData2;

public class Fire extends Projectiles {
	
	public Fire(double posX, double posY, double moveX, double moveY) {
		super(posX, posY, moveX, moveY);
		setDmg(50);
		friendly = true;
		addToCollision();
		// get Data from database?
		System.out.println("Fire");
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture = new LayerData2(this, "projectile/fire", 1, 3);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		mainTexture.disableDepth = true;
		//addNewLayer(new LayerData2(this, "projectile/glow", 1,1));
    	
    	double[][] animationTest = new double[3][3];
    	animationTest[0][0] = 100;
    	animationTest[1][0] = 0;
    	animationTest[2][0] = 1;

    	animationTest[0][1] = 200;
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 2;

    	animationTest[0][2] = 300;
    	animationTest[1][2] = 0;
    	animationTest[2][2] = 3;

    	mainTexture.animationList.add(animationTest);

		addNewLayer(mainTexture);
	}
}
