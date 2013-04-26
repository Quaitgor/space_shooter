package ent_c;

import behaviours.*;
import entities.Offensive;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

public class EnemyDummy extends Offensive {
	int defaultLayer = 40;
	
	public EnemyDummy(double posX, double posY) {
		super(posX, posY);
		setDmg(1);
		weapon = new FireWeapon(this, true);
		movement = new Nothing(this);
		mainTexture= new LayerData2(this, "enemy1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
