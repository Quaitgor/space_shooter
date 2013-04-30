package ent_c;

import weapons.DefaultWeapon;
import weapons.IceWeapon;
import entities.Mindless;
import entities.Moveable;
import entities.Offensive;
import graphics.GS;
import graphics.LayerData2;
import movementV2.*;

public class Powerup extends Mindless {
	
	public Powerup(double posX, double posY) {
		super(posX, posY);
		health = 30;
		//mind = new FireWhenRange(this, 800);
		//movement = new Sway(this, 100, 4, 5.0);
		movement = new Nothing(this);
		mainTexture= new LayerData2(this, "powerup", 1, 8);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-16, -16, 16, 16};
		isPowerup = true;
    	double[][] animationTest = new double[8][8];
    	animationTest[0][0] = 200;
    	animationTest[1][0] = 0;
    	animationTest[2][0] = 0;

    	animationTest[0][1] = 400;
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 1;

    	animationTest[0][2] = 600;
    	animationTest[1][2] = 0;
    	animationTest[2][2] = 2;

    	animationTest[0][3] = 800;
    	animationTest[1][3] = 0;
    	animationTest[2][3] = 3;

    	animationTest[0][4] = 1000;
    	animationTest[1][4] = 0;
    	animationTest[2][4] = 4;

    	animationTest[0][5] = 1200;
    	animationTest[1][5] = 0;
    	animationTest[2][5]= 5;

    	animationTest[0][6] = 1400;
    	animationTest[1][6] = 0;
    	animationTest[2][6] = 6;

    	animationTest[0][7] = 1600;
    	animationTest[1][7] = 0;
    	animationTest[2][7] = 7;
    	mainTexture.animationList.add(animationTest);
		addNewLayer(mainTexture);
		
		addToCollision();
	}
	public void pickedUp(Moveable target){
		boolean weaponTarget = true;
		if(target.equals(GS.player1)){
			weaponTarget = false;
		}
		((Offensive)target).changeWeapon(new IceWeapon(target, weaponTarget));
		posX = -1000;
		posY = -1000;
		//buggy?
		//((Offensive)this).unsubscribe();
	}
}
