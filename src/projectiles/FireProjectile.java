package projectiles;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import movementV2.Move;
import movementV2.TargetPosition;
import entities.*;
import graphics.GS;
import graphics.LayerData2;

public class FireProjectile extends Offensive {
	
	public FireProjectile(Entity owner, double posX, double posY, double moveX, double moveY, Constructor<? extends Move> MovementConstructor, Object[] args, boolean friendOrFoe, String TextureName) {
		super(posX, posY);
		
		setDmg(160);
		// get Data from database?
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture = new LayerData2(this, TextureName, 1, 3);
		mainTexture.color = new float[]{1f, 1f, 1f, 1f};
		mainTexture.disableDepth = true;
		movement = new TargetPosition(this, 4, moveX, moveY, true);
		args[0] = this;
		try {
			movement = MovementConstructor.newInstance(args);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		friendly = friendOrFoe;
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
	public FireProjectile(Entity owner, double posX, double posY, double moveX, double moveY , boolean friendOrFoe) {
		this(owner,posX,posY,moveX,moveY, friendOrFoe, "projectile/fire");
	}
	public FireProjectile(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe, String TextureName) {
		this(owner,posX,posY,moveX,moveY, (Constructor<? extends Move>) TargetPosition.class.getConstructors()[0], new Object[]{null,8, GS.player1.posX, GS.player1.posY, true}, friendOrFoe, TextureName);
	}
	public FireProjectile(Entity owner, double posX, double posY, double moveX, double moveY, Constructor<? extends Move> MovementConstructor, Object[] args, boolean friendOrFoe) {
		this(owner,posX,posY,moveX,moveY, MovementConstructor, args, friendOrFoe, "projectile/fire");
	}
}