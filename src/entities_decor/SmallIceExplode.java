package entities_decor;

import entities.*;
import graphics.LayerData2;

/**
 * A small explosion, used for the small ice projectile, it removes 
 * itself after playing the animation once
 * */
public class SmallIceExplode extends Entity {
	private double livetime;
	private LayerData2 tex;
	public SmallIceExplode(double posX, double posY) {
		super(posX, posY);
		// get Data from database?
		tex = new LayerData2(this, "explosion/smalliceexplosion", 1, 8);
		tex.layer = 35;
    	double[][] hit = new double[5][5];

    	hit[0][0] = 100;
    	hit[1][0] = 0;
    	hit[2][0] = 2;

    	hit[0][1] = 200;
    	hit[1][1] = 0;
    	hit[2][1] = 3;

    	hit[0][2] = 300;
    	hit[1][2] = 0;
    	hit[2][2] = 4;

    	hit[0][3] = 400;
    	hit[1][3] = 0;
    	hit[2][3] = 5;
    	
    	hit[0][4] = 2200;
    	hit[1][4] = 0;
    	hit[2][4] = 6;
    	

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
		if(livetime >= 500){
			tex.color[3] -= 0.2f;
		}
		if(livetime >= 1400){
			this.deltaUpdater.unregister(this);
		}
	}
}
