package weapons;

import entities.Entity;
import graphics.GS;

public abstract class Weapon {
	protected Entity owner;
	public int weaponDelay = 200;
	
	public Weapon(Entity owner){
		this.owner= owner;
	}
	public abstract void fire();
}
