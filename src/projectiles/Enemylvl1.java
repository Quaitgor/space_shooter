package projectiles;

import movement.TargetPosition;
import entities.*;
import graphics.LayerData2;

/**
 * This is a Projectile with a set graphical design, movement and damage,
 * depending on the boolean value in the constructor its either friendly or 
 * enemy
 * */
public class Enemylvl1 extends Offensive {
	
	public Enemylvl1(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		isProjectile = true;
		health = 2;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		mainTexture = new LayerData2(this, "projectile/enemylvl1", 1, 4);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		movement = new TargetPosition(this, 12, moveX, moveY, true);
		friendly = friendOrFoe;
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
		addToCollision();
	}
}
