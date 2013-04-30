package ent_c;

import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

public class Rocket extends HasMind {
	int defaultLayer = 40;
	
	public Rocket(double posX, double posY) {
		super(posX, posY);
		setDmg(1);
		weapon = new DefaultWeapon(this,false);
		mind = new Suicidal(this, 400, 200, true);
		movement = new Straight(this, 5);
		mainTexture= new LayerData2(this, "enemy1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
