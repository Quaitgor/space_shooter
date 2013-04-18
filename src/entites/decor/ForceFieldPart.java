package entites.decor;

import entities.*;
import graphics.LayerData2;
import observer.Subject;

public class ForceFieldPart extends Entity {
	private double livetime;
	private LayerData2 tex;
	public ForceFieldPart(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		// get Data from database?
		tex = new LayerData2(this, "hit", 8, 1);
		tex.disableAnimation = true;
		tex.layer = 35;
    	double[][] shatter = new double[7][7];
    	shatter[0][0] = 100;
    	shatter[1][0] = 0;
    	shatter[2][0] = 0;

    	shatter[0][1] = 200;
    	shatter[1][1] = 1;
    	shatter[2][1] = 0;

    	shatter[0][2] = 300;
    	shatter[1][2] = 2;
    	shatter[2][2] = 0;

    	shatter[0][3] = 400;
    	shatter[1][3] = 3;
    	shatter[2][3] = 0;

    	shatter[0][4] = 500;
    	shatter[1][4] = 4;
    	shatter[2][4] = 0;

    	shatter[0][5] = 600;
    	shatter[1][5] = 5;
    	shatter[2][5] = 0;
    	shatter[0][6] = 1200;
    	shatter[1][6] = 5;
    	shatter[2][6] = 0;

    	tex.color = new float[]{1.0f,1.0f,1.0f,0.5f};
    	tex.animationList.add(shatter);
		addNewLayer(tex);

	}
	public void update(double delta) {
		this.delta = delta;
		this.draw();
		this.changeAni();
	}
	private void changeAni(){
		livetime += delta;
		if(livetime >= 300){
			tex.disableAnimation = false;
		}
		if(livetime >= 900){
			tex.changeSprite(5, 0);
			tex.disableAnimation = true;
		}
	}
}
