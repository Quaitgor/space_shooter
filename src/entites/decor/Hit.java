package entites.decor;

import entities.*;
import graphics.LayerData2;
import observer.Subject;

public class Hit extends Entity {
	private double livetime;
	private LayerData2 tex;
	public Hit(double posX, double posY) {
		super(posX, posY);
		System.out.println("Hit Animation");
		// get Data from database?
		tex = new LayerData2(this, "hit", 1, 8);
		tex.layer = 35;
    	double[][] hit = new double[7][7];
    	hit[0][0] = 100;
    	hit[1][0] = 0;
    	hit[2][0] = 0;

    	hit[0][1] = 200;
    	hit[1][1] = 0;
    	hit[2][1] = 1;

    	hit[0][2] = 300;
    	hit[1][2] = 0;
    	hit[2][2] = 2;

    	hit[0][3] = 400;
    	hit[1][3] = 0;
    	hit[2][3] = 3;

    	hit[0][4] = 500;
    	hit[1][4] = 0;
    	hit[2][4] = 4;

    	hit[0][5] = 600;
    	hit[1][5] = 0;
    	hit[2][5] = 5;
    	hit[0][6] = 1200;
    	hit[1][6] = 0;
    	hit[2][6] = 5;

    	//tex.color = new float[]{1.0f,1.0f,1.0f,0.5f};
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
		if(livetime >= 600){
			tex.changeSprite(0, 5);
			tex.disableAnimation = true;
			this.deltaUpdater.unregister(this);
		}
	}
}
