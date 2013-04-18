package projectiles;

import entities.*;
import graphics.LayerData2;
import observer.Subject;
import movementV2.*;

public class Fire extends Moveable {
	
	//public LayerData2 lights = null;
	
	public Fire(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		// get Data from database?
		movement = new Straight(this, -12, 0);
		LayerData2 firetex = new LayerData2(this, "projectile/fire", 1, 3);
		firetex.color = new float[]{1f, 1f, 1f, 1f};
		addNewLayer(firetex);
		addNewLayer(new LayerData2(this, "projectile/glow", 1,1));

    	
    	double[][] animationTest = new double[3][3];
    	animationTest[0][0] = 200;
    	animationTest[0][1] = 400;
    	animationTest[0][2] = 600;
    	
    	animationTest[1][0] = 0;
    	animationTest[2][0] = 1;
    	
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 2;
    	
    	animationTest[1][2] = 0;
    	animationTest[2][2] = 3;


    	firetex.animationList.add(animationTest);
		
	}
}
