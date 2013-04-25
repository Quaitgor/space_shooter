package entities_decor;

import entities.*;
import graphics.LayerData2;
import observer.Subject;

public class Explode extends Entity {
	private double livetime;
	private LayerData2 tex;
	public Explode(double posX, double posY) {
		super(posX, posY);
		// get Data from database?
		tex = new LayerData2(this, "explosion", 1, 8);
		tex.layer = 35;
    	double[][] hit = new double[8][8];
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
    	
    	hit[0][6] = 700;
    	hit[1][6] = 0;
    	hit[2][6] = 6;
    	
    	hit[0][7] = 3200;
    	hit[1][7] = 0;
    	hit[2][7] = 7;
    	

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
			tex.color[3] -= 0.2f;
		}
		if(livetime >= 1400){
			this.deltaUpdater.unregister(this);
		}
	}
}
