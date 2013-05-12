package projectiles;

import movementV2.TargetPosition;
import entities.*;
import entities_decor.SmallIceExplode;
import graphics.LayerData2;

/**
 * This is a Projectile with a set graphical design, movement and damage,
 * depending on the boolean value in the constructor its either friendly or 
 * enemy
 * */
public class IceProjectileSmall extends Offensive {
	private double livetime = 500;
	
	/**
	 * the small iceProjectile has a special lifetime, it destroys itself
	 * after the set time.
	 * */
	public IceProjectileSmall(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		isProjectile = true;
		isProjectile = true;
		setDmg(5);
		dontPixelCheck = true;
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture = new LayerData2(this, "projectile/smallice", 1, 1);
		mainTexture.color = new float[]{1f, 1f, 1f, 1.0f};
		movement = new TargetPosition(this, 4, moveX, moveY, true);
		friendly = friendOrFoe;
		addNewLayer(mainTexture);
		addToCollision();
	}
	
	/**
	 * Modified update to include the lifetime
	 * */
	public void update(double delta){
		super.update(delta);
		livetime -= delta;
		if(livetime <= 0) death();
	}
	protected void death(){
		new SmallIceExplode(posX, posY);
		unsubscribe();
	}
	
}
