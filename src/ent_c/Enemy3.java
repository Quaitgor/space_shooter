package ent_c;

import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import weapons.*;

/**
 * An Enemy that uses Random Positioning to annoy the player.
 * */
public class Enemy3 extends HasMind {
	int defaultLayer = 40;
	
	public Enemy3(double posX, double posY) {
		super(posX, posY);
		setDmg(1);
		health = 150;
		weapon = new EnemyDefaultWeapon(this, true, 2);
		double[][] pos =  new double[][]{
				{1200.0, 162.0},
				{1200.0, 384.0},
				{1200.0, 604.0},
				{960.0, 162.0},
				{960.0, 384.0},
				{960.0, 604.0}
		};
		mind = new Random6PositionBoss(this, false, pos);
		mainTexture= new LayerData2(this, "enemy3", 1, 1);
		mainTexture.layer = defaultLayer;
		hitboxOffset = new int[]{-32, -16, 32, 16};
		addNewLayer(mainTexture);
		addToCollision();
	}
}
