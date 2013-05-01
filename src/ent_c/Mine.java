package ent_c;

import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

public class Mine extends HasMind {
	int defaultLayer = 40;
	
	public Mine(double posX, double posY) {
		super(posX, posY);
		setDmg(1);
		weapon = new DefaultWeapon(this,false);
		mind = new Suicidal(this, 400, 200, false);
		movement = new Straight(this, 5);
		mainTexture= new LayerData2(this, "mines", 1, 4);

    	double[][] animationTest = new double[6][6];
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
    	animationTest[0][4] = 500;
    	animationTest[1][4] = 0;
    	animationTest[2][4] = 2;
    	animationTest[0][5] = 600;
    	animationTest[1][5] = 0;
    	animationTest[2][5] = 1;

    	mainTexture.animationList.add(animationTest);
		mainTexture.layer = defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
