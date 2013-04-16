package projectiles;

import entities.Entity;
import graphics.LayerData2;
import observer.Subject;
import movementV2.*;

public class Plasma extends Entity {
	protected Move movement;
	//public LayerData2 lights = null;
	
	public Plasma(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		// get Data from database?
		movement = new Straight(this, -12, 0);
		LayerData2 tex = new LayerData2(this, "projectile/plasma", 4, 1);
		tex.color = new float[]{1f, 1f, 1f, 1f};
		
    	
    	double[][] animationTest = new double[3][3];
    	animationTest[0][0] = 200;
    	animationTest[1][0] = 2;
    	animationTest[2][0] = 0;

    	animationTest[0][1] = 400;
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 0;

    	animationTest[0][2] = 600;
    	animationTest[1][2] = 1;
    	animationTest[2][2] = 0;
    	


    	tex.animationList.add(animationTest);
		addNewLayer(tex);
	}
}
