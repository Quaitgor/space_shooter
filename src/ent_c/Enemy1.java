package ent_c;

import movement.Sway;
import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import weapons.*;

/**
 * An Enemy that flys a straight line and attemps to suicide-crash into the player when near enough.
 * */
public class Enemy1 extends HasMind {
	int defaultLayer = 40;
	
	public Enemy1(double posX, double posY) {
		super(posX, posY);
		health = 10;
		setDmg(1);
		weapon = new EnemyDefaultWeapon(this, true, 0);
		mind = new Suicidal(this, 400, 200, false);
		movement = new Sway(this, 100, 4, 5.0);
		mainTexture= new LayerData2(this, "enemy1", 1, 1);
		mainTexture.layer = defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
