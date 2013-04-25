package ent_c;

import behaviours.Behave;
import behaviours.FireWhenRange;
import behaviours.RandomFlying;
import entities.Offensive;
import graphics.LayerData2;
import weapons.*;
import movementV2.*;

public class Boss1 extends Offensive {
	public Behave mind;
	int defaultLayer = 40;
	
	public Boss1(double posX, double posY) {
		super(posX, posY);
		health = 200;
		weapon = new Enemy_Weapon1(this);
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

	public void update(double delta){
		super.update(delta);
		mind.update();
	}
	/*
	public void changeWeapon(String newWeapon ) {
		this.weapon.changeWeapon(newWeapon);
	}
	*/
}
