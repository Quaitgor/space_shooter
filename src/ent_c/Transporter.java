package ent_c;

import movementV2.Sway;
import behaviours.*;
import entities.HasMind;
import graphics.LayerData2;
import weapons.*;

/**
 * An flying object that transports the powerups. the subclasses of this class spawn the upgrades on death.
 * */
public class Transporter extends HasMind {
	int defaultLayer = 40;
	
	public Transporter(double posX, double posY) {
		super(posX, posY);
		setDmg(1);
		health = 40;
		weapon = new EnemyDefaultWeapon(this, true, 0);
		mind = new Suicidal(this, 400, 200, false);
		movement = new Sway(this, 100, 4, 5.0);
		mainTexture= new LayerData2(this, "transport", 1, 1);
		mainTexture.layer = defaultLayer;
		hitboxOffset = new int[]{-32, -32, 32, 32};
		addNewLayer(mainTexture);
		addToCollision();
	}
	
}
