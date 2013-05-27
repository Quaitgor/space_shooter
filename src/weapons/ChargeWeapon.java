package weapons;

import java.util.Random;

import ent_c.Player;
import entities.Entity;

/**
 * shoots variable Projectiles, depending on its owners chargelevel.
 * It is designed for the player and not for enemys.
 * */
public class ChargeWeapon extends Weapon {
	Random r;
	public double interfaceBarPercent;
	public double maxCharge = 2000;
	public double chargeLevel;
	public boolean weaponDisabled = false;
	private double burstPower = 0;
	private double burstDelay = 25;
	private double burstCost = 150;
	public boolean burstSwitch = false;
	private double weaponDelayBurst = 5000;
	private boolean burstMode = false;
	
	private double secondCount = 0;
	private double percentOfCharge = 0;
	private double cooldownTick = 0;
	private double percentOfChargeOnFire = 0;
	
	
	
	
	public ChargeWeapon(Entity owner, boolean targetPlayer) {
		super(owner, targetPlayer);
		weaponDelay = 500;
		r = new Random();

	}
	
	public void update(double delta){
		super.update(delta);
		secondCount -= delta;
		percentOfCharge = 100/maxCharge;
		percentOfCharge = percentOfCharge*chargeLevel;
		interfaceBarPercent = percentOfCharge;
		if(secondCount > 0){
			chargeLevel -= 2*(percentOfChargeOnFire/cooldownTick)*delta;
			secondCount -= delta;
			if(secondCount <delta && secondCount > 0) secondCount = 0;
		}
		
		if(burstMode){
			System.out.println("burst");
			if(burstPower <= 0) {
				burstMode = false;
			}else{
				makeBurst();
			}
		}

	}
	public void chargingWeapon(){
		if(secondCount <= 0){
			if(chargeLevel < maxCharge) chargeLevel += owner.delta;
			if(chargeLevel > maxCharge) chargeLevel = maxCharge;
		}
	}
	
	private void fireCharge(){
		super.fire();
		secondCount = weaponDelay;
		if(chargeLevel < 500){
			((Player)owner).fire();
		}
		if(chargeLevel <= 1000 && chargeLevel > 500){
			((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
			new projectiles.Chargelvl1Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+40, targetY, friendly);
		}
		if(chargeLevel >= 1001 && chargeLevel < 2000){
			((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
			new projectiles.Chargelvl2Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+40, targetY, friendly);
		}
		if(chargeLevel >= 2000){
			((Player)owner).playFireAnimation("projectile/projectilefireCharge", 0);
			new projectiles.Chargelvl3Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX+70, targetY, friendly);
		}
	}
	
	private void fireBurst(){
		burstMode = true;
		counter = burstDelay;
		secondCount = weaponDelayBurst;
	}
	private void makeBurst(){
		counter = burstDelay;
		burstPower -= burstCost;
		super.fire();
		double rdir = r.nextDouble()*2 -1;
		double rdir2 = r.nextDouble()*2 -1;
		new projectiles.Chargelvl1Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY+rdir, friendly);
		new projectiles.Chargelvl1Projectile(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], targetX, targetY+rdir2, friendly);
	}
	
	/**
	 * Fires one Projectile, depending on the chargelevel of the player.
	 * */
	public void fire() {
		if(secondCount <= 0 && !burstMode){
			if (!burstSwitch){
				cooldownTick = weaponDelay;
				percentOfChargeOnFire = chargeLevel;
				fireCharge();
			}
			if (burstSwitch){
				cooldownTick = weaponDelayBurst;
				burstPower = chargeLevel;
				percentOfChargeOnFire = chargeLevel;
				fireBurst();
			}
			//temp
		}
	}
}
