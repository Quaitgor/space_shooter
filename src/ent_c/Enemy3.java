package ent_c;

import movementV2.Sway;
import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import weapons.*;

public class Enemy3 extends HasMind {
	int defaultLayer = 40;
	
	public Enemy3(double posX, double posY) {
		super(posX, posY);
		setDmg(1);
		weapon = new InfernoWeapon(this, true);
		//mind = new FireWhenRange(this, 400);
		//movement = new Sway(this, 100, 4, 5.0);
		mind = new Random6PositionBoss(this, false);
		mainTexture= new LayerData2(this, "enemy3", 1, 1);
		mainTexture.layer = defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
