package entites.decor;

import entities.*;
import graphics.LayerData2;
import observer.Subject;

public class BarrierHit extends Entity {
	private double livetime;
	private LayerData2 tex;
	int speed = 100;
	
	public BarrierHit(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		System.out.println("Hit Animation");
		// get Data from database?
		tex = new LayerData2(this, "barrierHit", 1, 8);
		tex.layer = 35;
    	double[][] hit = new double[8][8];
    	hit[0][0] = 0.5*speed;
    	hit[1][0] = 0;
    	hit[2][0] = 0;

    	hit[0][1] = 1*speed;
    	hit[1][1] = 0;
    	hit[2][1] = 1;

    	hit[0][2] = 1.5*speed;
    	hit[1][2] = 0;
    	hit[2][2] = 2;

    	hit[0][3] = 2*speed;
    	hit[1][3] = 0;
    	hit[2][3] = 3;

    	hit[0][4] = 3*speed;
    	hit[1][4] = 0;
    	hit[2][4] = 4;

    	hit[0][5] = 4*speed;
    	hit[1][5] = 0;
    	hit[2][5] = 5;

    	hit[0][6] = 5*speed;
    	hit[1][6] = 0;
    	hit[2][6] = 6;

    	hit[0][7] = 6*speed;
    	hit[1][7] = 0;
    	hit[2][7] = 7;
    	
    	tex.animationList.add(hit);
		addNewLayer(tex);

	}
	public void update(double delta) {
		this.delta = delta;
		this.draw();
		this.changeAni();
	}
	private void changeAni(){
		livetime += delta;
		if(livetime >= 6*speed){
			tex.changeSprite(-1, -1);
			tex.disableAnimation = true;
			this.deltaUpdater.unregister(this);
		}
	}
}
