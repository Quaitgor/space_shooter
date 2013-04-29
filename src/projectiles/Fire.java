package projectiles;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import movementV2.Move;
import movementV2.RandomStraightTrig;
import movementV2.TargetPosition;
import entities.*;
import graphics.GS;
import graphics.LayerData2;

public class Fire extends Offensive {
	
	public Fire(Entity owner, double posX, double posY, double moveX, double moveY, Constructor<? extends Move> MovementConstructor, Object[] args) {
		super(posX, posY);
		isProjectile = true;

		// get Data from database?
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture = new LayerData2(this, "projectile/fire", 1, 3);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		mainTexture.disableDepth = true;
		
		args[0] = this;
		try {
			movement = MovementConstructor.newInstance(args);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addToCollision();
		//addNewLayer(new LayerData2(this, "projectile/glow", 1,1));
    	
    	double[][] animationTest = new double[3][3];
    	animationTest[0][0] = 100;
    	animationTest[1][0] = 0;
    	animationTest[2][0] = 1;

    	animationTest[0][1] = 200;
    	animationTest[1][1] = 0;
    	animationTest[2][1] = 2;

    	animationTest[0][2] = 300;
    	animationTest[1][2] = 0;
    	animationTest[2][2] = 3;

    	mainTexture.animationList.add(animationTest);

		addNewLayer(mainTexture);
	}

	
	public Fire(Entity owner, double posX, double posY, double moveX, double moveY) {
		this(owner,posX,posY,moveX,moveY, (Constructor<? extends Move>) TargetPosition.class.getConstructors()[0], new Object[]{null,8, GS.player1.posX, GS.player1.posY, true});
	}
}
