package projectiles;

import movement.TargetPosition;
import entities.*;
import graphics.LayerData2;

public class Chargelvl3Projectile extends Offensive {
	private double maxsize = 256;

/**
 * This is a Projectile with a set graphical design, movement and damage,
 * depending on the boolean value in the constructor its either friendly or 
 * enemy
 * */
	public Chargelvl3Projectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		isProjectile = true;
		health = 20;
		setDmg(40);
		hitboxOffset = new int[]{-0, -114, 128, 114};
		mainTexture = new LayerData2(this, "projectile/chargelvl3", 1, 4);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		movement = new TargetPosition(this, 12, moveX, moveY, true);
		friendly = friendOrFoe;
		mainTexture.spriteDisplayX = 64;
		mainTexture.spriteDisplayY = 64;
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
		addToCollision();
	}
	/**
	 * This projectile has a modified update to include changeSize().
	 * */
	public void update(double delta){
		changeSize();	
		super.update(delta);	
	}
	/**
	 * This projectile grows in size until it hits its maximum size in both dimensions.
	 * */
	protected void changeSize(){
		if(mainTexture.spriteDisplayX < maxsize)mainTexture.spriteDisplayX += 4;
		if(mainTexture.spriteDisplayY < maxsize)mainTexture.spriteDisplayY += 4;
		
	}
}
