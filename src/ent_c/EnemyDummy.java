package ent_c;

import entities.Entity;
import entities.Offensive;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

public class EnemyDummy extends Offensive {
	
	int defaultLayer = 40;
	
	public EnemyDummy(double posX, double posY) {
		super(posX, posY);
		weapon = new Weapon_Fire(this);
		movement = new TargetPosition(this, 4, 100, 384);
		//movement = new Nothing(this);
		mainTexture= new LayerData2(this, "enemy1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
	
	/*
	public void changeWeapon(String newWeapon ) {
		this.weapon.changeWeapon(newWeapon);
	}
	*/
}
