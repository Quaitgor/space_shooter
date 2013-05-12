package entities_decor;

import java.util.Random;

import entities.*;
import graphics.LayerData2;
import movementV2.*;

/**
 * An asteroid flying randomly in random speed and rotation.
 */
public class Asteroid extends MoveableDecor {
	double rangeMin = 5;
	double rangeMax = 0.2;
	
	public Asteroid(double posX, double posY) {
		super(posX, posY);
		Random r = new Random();
		String tex = "";
		int X = 1;
		int Y = 1;
		int randomTex = r.nextInt(7)+2;
		tex = "deco/astro"+randomTex;
		if(randomTex >= 4 && randomTex <= 9){
			X *= 2;
			Y *= 2;
		}
		
		LayerData2 texture1 = new LayerData2(this, tex, 1, 1);
		texture1.spriteDisplayX *= X;
		texture1.spriteDisplayY *= Y;
		texture1.layer = defaultLayer-1;
		addNewLayer(texture1);

		movement = new RandomStraightTrig(this, 5);
	}
}
