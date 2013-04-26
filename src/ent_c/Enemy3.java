package ent_c;

import behaviours.FireWhenRange;
import entities.HasMind;
import graphics.LayerData2;
import weapons.*;
import movementV2.*;

public class Enemy3 extends HasMind {
	
	int defaultLayer = 40;
	
	public Enemy3(double posX, double posY) {
		super(posX, posY);
		weapon = new FireWeapon(this, true);
		mind = new FireWhenRange(this, 400);
		movement = new Sway(this, 100, 4, 5.0);
		mainTexture = new LayerData2(this, "enemy3", 1, 1);
		mainTexture.layer= defaultLayer;
		addNewLayer(mainTexture);
		addToCollision();
	}
}
