package weapons;

import entities.Entity;
import entities.Offensive;
import graphics.GS;

public abstract class Weapon {
	protected Entity owner;
	public int weaponDelay = 200;
	double counter = 0;
	private boolean targetPlayer = false;
	public double[] weaponOffset = new double[]{0,0};
	protected double targetX = 0, targetY = 0;
	public boolean friendly = false;
	
	public Weapon(Entity owner, boolean targetPlayer){
		this.owner= owner;
		this.targetPlayer = targetPlayer;
		this.weaponOffset= ((Offensive)owner).weaponOffset;
	}
	public void update(double delta){
		if(counter > 0)counter -= delta;
	}
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
