package weapons;

import entities.Entity;
import entities.Offensive;
import graphics.GS;

/**
 * The Weapon is a Strategic Pattern that allows switching weapons for each offensive Object
 * in this game. With this other Objects can change their weapon ingame (ex. Player).
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
	 * With this Method the weapon recieves Updates of the delta from its owner.
	 * Used to build delays in firespeed for example.
	 * */
	public void update(double delta){
		if(counter > 0)counter -= delta;
	}
	
	/**
	 * Fire is called whenever a object trys to fire its weapon.
	 * depending on the set target the weapon spawns the projectiles
	 * which target either the player or a direction.
	 * fire is extended in the weapon objects.
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
