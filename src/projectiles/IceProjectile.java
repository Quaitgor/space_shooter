package projectiles;

import movementV2.TargetPosition;
import entities.*;
import entities_decor.Explode;
import graphics.GS;
import graphics.LayerData2;

public class IceProjectile extends Offensive {
	
	public IceProjectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		
		setDmg(50);
		// get Data from database?
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture = new LayerData2(this, "projectile/ice", 8, 2);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		mainTexture.disableDepth = true;
		movement = new TargetPosition(this, 8, moveX, moveY, true);
		friendly = friendOrFoe;
		deathSprite = "explosion/iceexplosion";
		addToCollision();
		//addNewLayer(new LayerData2(this, "projectile/glow", 1,1));

    	double[][] animationTest = new double[3][11];
    	animationTest[0][0] = 80;
    	animationTest[1][0] = 0;
    	animationTest[2][0] = 0;

    	animationTest[0][1] = 160;
    	animationTest[1][1] = 1;
    	animationTest[2][1] = 0;

    	animationTest[0][2] = 240;
    	animationTest[1][2] = 2;
    	animationTest[2][2] = 0;

    	animationTest[0][3] = 320;
    	animationTest[1][3] = 3;
    	animationTest[2][3] = 0;

    	animationTest[0][4] = 400;
    	animationTest[1][4] = 4;
    	animationTest[2][4] = 0;

    	animationTest[0][5] = 480;
    	animationTest[1][5] = 5;
    	animationTest[2][5] = 0;

    	animationTest[0][6] = 560;
    	animationTest[1][6] = 0;
    	animationTest[2][6] = 1;

    	animationTest[0][7] = 640;
    	animationTest[1][7] = 1;
    	animationTest[2][7] = 1;

    	animationTest[0][8] = 720;
    	animationTest[1][8] = 2;
    	animationTest[2][8] = 1;

    	animationTest[0][9] = 800;
    	animationTest[1][9] = 3;
    	animationTest[2][9] = 1;

    	animationTest[0][10] = 880;
    	animationTest[1][10] = 4;
    	animationTest[2][10] = 1;
    	mainTexture.animationList.add(animationTest);
		addNewLayer(mainTexture);
	}
	protected void death(){
		super.death();
		new IceProjectileSmall(this, this.posX, this.posY, this.posX+5, this.posY-5, friendly);
		new IceProjectileSmall(this, this.posX, this.posY, this.posX+5, this.posY, friendly);
		new IceProjectileSmall(this, this.posX, this.posY, this.posX+5, this.posY+5, friendly);
		new IceProjectileSmall(this, this.posX, this.posY, this.posX-5, this.posY+5, friendly);
		new IceProjectileSmall(this, this.posX, this.posY, this.posX-5, this.posY, friendly);
		new IceProjectileSmall(this, this.posX, this.posY, this.posX-5, this.posY-5, friendly);
		new IceProjectileSmall(this, this.posX, this.posY, this.posX, this.posY+5, friendly);
		new IceProjectileSmall(this, this.posX, this.posY, this.posX, this.posY-5, friendly);
	}
}
