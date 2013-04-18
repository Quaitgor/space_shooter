package entites.decor;

import java.util.Random;

import entities.*;
import graphics.LayerData2;
import observer.Subject;
import movementV2.*;

public class Asteroid extends MoveableDecor {
	double rangeMin = 5;
	double rangeMax = 0.2;
	
	public Asteroid(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		Random r = new Random();
		String tex = "";
		int X = 1;
		int Y = 1;
		int randomTex = r.nextInt(7)+2;
		System.out.println(randomTex);
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

		double randomValue = 2 + ( 6 - 2) * r.nextDouble();
		double randomValue2 = -2.0 + ( 2.0 - -2.0) * r.nextDouble();
		movement = new Straight(this, randomValue, randomValue2);
		((Straight) movement).randomRotate(true);
		
	}
}
