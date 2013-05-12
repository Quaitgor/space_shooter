package ent_c;

import movement.Sway;
import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import weapons.*;

/**
 * An Enemy that has a swaying movement and more health than the default enemy.
 * */
public class Enemy2 extends HasMind {
	int defaultLayer = 40;
	
	public Enemy2(double posX, double posY) {
		super(posX, posY);
		health = 20;
		setDmg(1);
		weapon = new EnemyDefaultWeapon(this, true, 1);
		mind = new FireWhenRange(this, 400);
		movement = new Sway(this, 100, 4, 5.0);
		mainTexture= new LayerData2(this, "enemy2", 1, 1);
		mainTexture.layer = defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
