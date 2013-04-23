package ent_c;

import entities.Offensive;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

public class Enemy2 extends Offensive {
	
	int defaultLayer = 40;
	
	public Enemy2(double posX, double posY) {
		super(posX, posY);
		weapon = new Weapon_Fire(this);
		movement = new Sway(this, 100, 4, 5.0);
		mainTexture = new LayerData2(this, "enemy2", 1, 1);
		mainTexture.layer= defaultLayer;
		addNewLayer(mainTexture);
	}
	
	/*
	public void changeWeapon(String newWeapon ) {
		this.weapon.changeWeapon(newWeapon);
	}
	*/
}
