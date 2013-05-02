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
		weapon = new DefaultWeapon(this, true);
		weapon.weaponOffset = new double[]{-100, -35};
		mind =null;
		movement = new TargetPosition(this, 5, posX, posY, false);
		//movement = new Nothing(this);
		mainTexture= new LayerData2(this, "boss1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-172, -76, 172, 76};
		addNewLayer(mainTexture);
		addToCollision();
		

	}
}
