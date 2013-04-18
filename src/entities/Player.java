package entities;

import behaviours.*;
import graphics.HUD;
import graphics.LayerData2;
import observer.Subject;
import weapons.*;
import movementV2.*;

public class Player extends Offensive {
	

	public Weapon weapon;
	protected Behave behaviour;
	public LayerData2 lights = null;
	
	public Player(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		// get Data from database?
		weapon = new Weapon("Fire");
		movement = new PlayerMove(this, 1);
		addNewLayer(new LayerData2(this, "player", 1, 1));
		lights = new LayerData2(this, "playerlight", 1, 1);
		lights.layer = 39;
		lights.color = new float[]{0.2f, 0.2f, 0,2f, 1.0f};
		addNewLayer(lights);
	}
	
	
	public void fire(){
		weapon.fire(posX, posY);
	}
	public void changeWeapon(String newWeapon ) {
		this.weapon.changeWeapon(newWeapon);
	}
}
