package weapons;

import graphics.GS;

public class Weapon {
	protected int damage;
	public int weapondelay = 200;
	public String weapon = "Default";
	
	public Weapon(String weapon){
		this.weapon = weapon;
	}
	public void fire(double posX,double posY){
		GS.profac.create(this.weapon,posX,posY);
	}
	public void changeWeapon(String newWeapon){
		this.weapon = newWeapon;
	}
	
}
