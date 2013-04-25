package entites_decor;

import entities.*;
import graphics.LayerData2;
import observer.Subject;

public class Electro extends Entity {
	private LayerData2 tex;
	public Electro(double posX, double posY) {
		super(posX, posY);
		// get Data from database?
		tex = new LayerData2(this, "electro", 2, 2);
		tex.layer = 35;
    	double[][] hit = new double[4][4];
    	hit[0][0] = 100;
    	hit[1][0] = 0;
    	hit[2][0] = 0;

    	hit[0][1] = 200;
    	hit[1][1] = 0;
    	hit[2][1] = 1;
    	
    	hit[0][2] = 300;
    	hit[1][2] = 1;
    	hit[2][2] = 0;

    	hit[0][3] = 400;
    	hit[1][3] = 1;
    	hit[2][3] = 1;
    	tex.animationList.add(hit);
		addNewLayer(tex);

	}
}
