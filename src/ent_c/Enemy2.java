package ent_c;

import behaviours.FireWhenRange;
import entities.HasMind;
import graphics.LayerData2;
import weapons.*;
import movementV2.*;

public class Enemy2 extends HasMind {
	
	public Enemy2(double posX, double posY) {
		super(posX, posY);
		health = 100;
		weapon = new DefaultWeapon(this, true);
		mind = new FireWhenRange(this, 400);
		movement = new Sway(this, 100, 4, 5.0);
		mainTexture = new LayerData2(this, "enemy2", 1, 1);
		mainTexture.layer= defaultLayer;
		addNewLayer(mainTexture);
		addToCollision();
	}
}
