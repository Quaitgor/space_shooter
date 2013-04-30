package ent_c;

import behaviours.FireWhenRange;
import behaviours.NoAction;
import entities.HasMind;
import entities.Offensive;
import graphics.LayerData2;
import weapons.*;
import movementV2.*;

public class Enemy1 extends HasMind {
	
	public Enemy1(double posX, double posY) {
		super(posX, posY);
		health = 30;
		weapon = new DefaultWeapon(this, true);
		//mind = new FireWhenRange(this, 800);
		//movement = new Sway(this, 100, 4, 5.0);
		mind = new NoAction(this);
		movement = new Nothing(this);
		mainTexture= new LayerData2(this, "enemy1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
