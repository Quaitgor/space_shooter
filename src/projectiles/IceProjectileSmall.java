package projectiles;

import movementV2.TargetPosition;
import entites_decor.SmallIceExplode;
import entities.*;
import entities_decor.Explode;
import graphics.GS;
import graphics.LayerData2;

public class IceProjectileSmall extends Offensive {
	private double livetime = 500;
	
	public IceProjectileSmall(Entity owner, double posX, double posY, double moveX, double moveY, boolean friendOrFoe) {
		super(posX, posY);
		setDmg(10);
		dontPixelCheck = true;
		// get Data from database?
		hitboxOffset = new int[]{-16, -16, 20, 16};
		mainTexture = new LayerData2(this, "projectile/smallice", 1, 1);
		mainTexture.color = new float[]{1f, 1f, 1f, 1.0f};
		movement = new TargetPosition(this, 4, moveX, moveY, true);
		friendly = friendOrFoe;
		addToCollision();
		addNewLayer(mainTexture);
	}
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
