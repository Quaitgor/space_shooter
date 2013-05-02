package ent_c;

import movementV2.Sway;
import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import weapons.*;

public class Enemy2 extends HasMind {
	int defaultLayer = 40;
	
	public Enemy2(double posX, double posY) {
		super(posX, posY);
		setDmg(1);
		weapon = new IceWeapon(this, true);
		mind = new FireWhenRange(this, 400);
		movement = new Sway(this, 100, 4, 5.0);
		mainTexture= new LayerData2(this, "enemy2", 1, 1);
		mainTexture.layer = defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
