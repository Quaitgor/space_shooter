package ent_c;

import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

public class EnemyDummyWithMind extends HasMind {
	int defaultLayer = 40;
	
	public EnemyDummyWithMind(double posX, double posY) {
		super(posX, posY);
		setDmg(1);
		weapon = new DefaultWeapon(this, true);
		mind = new Suicidal(this, 400, 200);
		movement = new StraightAhead(this, 5);
		mainTexture= new LayerData2(this, "enemy1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
