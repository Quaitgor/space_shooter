package weapons;

import entities.Entity;
import entities.Offensive;
import graphics.GS;

/**
 * The Weapon is a strategy which can be exchanged in an Offensive. It can be
 * fired, which produces a projectile.
 * */
public abstract class Weapon {
	protected Entity owner;
	public int weaponDelay = 200;
	double counter = 0;
	protected boolean targetPlayer = false;
	public double[] weaponOffset = new double[]{0,0};
	protected double targetX = 0, targetY = 0;
	public boolean friendly = false;
	
	public Weapon(Entity owner, boolean targetPlayer){
		this.owner= owner;
		this.targetPlayer = targetPlayer;
		this.weaponOffset= ((Offensive)owner).weaponOffset;
	}
	
	/**
	 * Used receive updates and to allow delays in the fire frequency for example.
	 * */
	public void update(double delta){
		if(counter > 0)counter -= delta;
		if(counter < delta && counter > 0) counter = 0;
	}
	
	/**
	 * is called whenever an Offensive tries to fire its weapon.
	 * */
	public void fire(){
		if(targetPlayer == true){
			targetX = GS.player1.posX;
			targetY = GS.player1.posY;
		}else{
			targetX = owner.posX+weaponOffset[0]+5;
			targetY = owner.posY+weaponOffset[1];
		}
		counter = weaponDelay;
	};
}
