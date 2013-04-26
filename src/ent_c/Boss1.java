package ent_c;

import behaviours.Behave;
import behaviours.FireWhenRange;
import behaviours.NoAction;
import behaviours.RandomFlying;
import entities.HasMind;
import entities.Offensive;
import graphics.LayerData2;
import weapons.*;
import movementV2.*;

public class Boss1 extends HasMind {
	int defaultLayer = 40;
	
	public Boss1(double posX, double posY) {
		super(posX, posY);
		health = 10000;
		weapon = new FireWeapon(this, true);
		weapon.weaponOffset = new double[]{-100, -35};
		mind = new RandomFlying(this, 2000, 1200, 740, 740, 0, 0, 8);
		movement = new TargetPosition(this, 5, posX, posY, false);
		//movement = new Nothing(this);
		mainTexture= new LayerData2(this, "boss1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-172, -76, 172, 76};
		addNewLayer(mainTexture);
		addToCollision();
		

	}
}
