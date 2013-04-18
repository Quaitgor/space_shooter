package projectiles;

import entities.Entity;
import graphics.LayerData2;
import observer.Subject;
import movementV2.*;

public class Ice extends Entity {
	protected Move movement;
	//public LayerData2 lights = null;
	
	public Ice(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		// get Data from database?
		movement = new Straight(this, -12, 0);
		LayerData2 tex = new LayerData2(this, "projectile/ice", 8, 2);
		tex.color = new float[]{1f, 1f, 1f, 1f};
		addNewLayer(tex);

		//build generator for this
    	double[][] animationTest = new double[3][11];
    	animationTest[0][0] = 80;
    	animationTest[0][1] = 160;
    	animationTest[0][2] = 240;
    	animationTest[0][3] = 320;
    	animationTest[0][4] = 400;
    	animationTest[0][5] = 480;
    	animationTest[0][6] = 560;
    	animationTest[0][7] = 640;
    	animationTest[0][8] = 720;
    	animationTest[0][9] = 800;
    	animationTest[0][10] = 880;

    	animationTest[1][0] = 0;
    	animationTest[2][0] = 0;
    	
    	animationTest[1][1] = 1;
    	animationTest[2][1] = 0;

    	animationTest[1][2] = 2;
    	animationTest[2][2] = 0;

    	animationTest[1][3] = 3;
    	animationTest[2][3] = 0;

    	animationTest[1][4] = 4;
    	animationTest[2][4] = 0;

    	animationTest[1][5] = 5;
    	animationTest[2][5] = 0;

    	animationTest[1][6] = 0;
    	animationTest[2][6] = 1;

    	animationTest[1][7] = 1;
    	animationTest[2][7] = 1;
    	
    	animationTest[1][8] = 2;
    	animationTest[2][8] = 1;
    	
    	animationTest[1][9] = 3;
    	animationTest[2][9] = 1;
    	
    	animationTest[1][10] = 4;
    	animationTest[2][10] = 1;
    	
    	tex.animationList.add(animationTest);
	}
}
