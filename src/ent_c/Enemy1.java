package ent_c;

import behaviours.Behave;
import behaviours.FireWhenRange;
import entities.Offensive;
import graphics.LayerData2;
import weapons.*;
import movementV2.*;

public class Enemy1 extends Offensive {
	public Behave mind;
	int defaultLayer = 40;
	
	public Enemy1(double posX, double posY) {
		super(posX, posY);
		weapon = new Enemy_Weapon1(this);
		mind = new FireWhenRange(this, 800);
		movement = new Sway(this, 100, 4, 5.0);
		//movement = new Nothing(this);
		mainTexture= new LayerData2(this, "enemy1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
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
