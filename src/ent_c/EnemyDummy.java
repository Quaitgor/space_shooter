package ent_c;

import behaviours.*;
import entities.Offensive;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

public class EnemyDummy extends Offensive {
	public Behave mind;
	int defaultLayer = 40;
	
	public EnemyDummy(double posX, double posY) {
		super(posX, posY);
		//mind = new FireWhenRange(this, 400);
		setDmg(1);
		weapon = new Enemy_Weapon1(this);
		//movement = new Nothing(this);
		//movement = new TargetPosition(this, 1, 500, 200);
		mind = new Suicidal(this, 400, 100);
		movement = new StraightTrigonometry(this, 5, 180);
		mainTexture= new LayerData2(this, "enemy1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
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
