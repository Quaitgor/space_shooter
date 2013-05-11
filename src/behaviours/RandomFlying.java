package behaviours;

import java.util.Random;

import movementV2.*;
import entities.*;
import graphics.GS;

/**
 * Regularely changes the owners movement-pattern towards a randomly determined
 * direction and fires towards the player after reaching the specified distance.
 */
public class RandomFlying extends Behave {
	private double rx, ry, oldrx, oldry;
	private Random rnd;
	double spriteX, spriteY;
	
	public RandomFlying(Moveable getOwner) {
		super(getOwner);
		rnd = new Random();
		spriteX = owner.mainTexture.spriteDisplayX/2;
		spriteY = owner.mainTexture.spriteDisplayY/2;

		rx = 480 + rnd.nextDouble()*800;
		ry = spriteY+ rnd.nextDouble()*(768- spriteY);
	}

	protected void checkMind() {
		
		System.out.println(owner.posX - rx);
		
		if(Math.abs(owner.posX - rx) <= 50 || Math.abs(owner.posY - ry) <= 50){
			oldrx = rx;
			oldry = ry;
			while(Math.abs(rx - oldrx) <= 400){
				rx = 640 + rnd.nextDouble()*640;
			}

			while(Math.abs(ry - oldry) <= 100){
				ry = spriteY+ rnd.nextDouble()*(768- spriteY);
			}
			
		}
		System.out.println(rx);
		((TargetPosition)owner.movement).changeTarget(rx,  ry);
	}
}

