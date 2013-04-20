package entities.combat;

import entities.Offensive;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

/**
 * Player extens Offensive, together with the movement Strategy Pattern the User can control this Object.
 * the PlayerMove movement has special control over this object and its weapon and grapics
 * PlayerControl is the PlayerMove class
 * */

public class Player extends Offensive {
	
	public LayerData2 lights = null;
	int defaultLayer = 40;
	
	public Player(double posX, double posY) {
		super(posX, posY);
		weapon = new Weapon_Fire(this);
		movement = new TestingControl(this, 1);
		LayerData2 tex = new LayerData2(this, "player", 1, 1);
		tex.layer= defaultLayer;
		addNewLayer(tex);
		lights = new LayerData2(this, "playerlight", 1, 1);
		lights.layer = defaultLayer -1;
		lights.color = new float[]{0.2f, 0.2f, 0,2f, 1.0f};
		addNewLayer(lights);
	}
	
	/*
	public void changeWeapon(String newWeapon ) {
		this.weapon.changeWeapon(newWeapon);
	}
	*/
}
