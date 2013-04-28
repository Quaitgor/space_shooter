package ent_c;

import entities.Entity;
import entities.Offensive;
import graphics.GS;
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
	
	//public LayerData2 lights = null;
	private int defaultLayer = 40;
	public int shieldCharges = 2;
	public Weapon secondWeapon;
	public double chargeLevel = 0;
	protected double projectileFireTime;
	public LayerData2 projectileFireCharge;
	public LayerData2 projectileFireDefault;
	public LayerData2 projectileFirePlasma;
	
	public Player(double posX, double posY) {
		super(posX, posY);
		GS.player1 = this;
		//setDmg(10000);
		weaponOffset = new double[]{80, 1};
		changeWeapon(1, new DefaultWeapon(this, false));
		changeWeapon(2, new ChargeWeapon(this, false));
		movement = new PlayerMove(this, 1);
		mainTexture = new LayerData2(this, "player", 1, 1);
		mainTexture.layer= defaultLayer;
		addNewLayer(mainTexture);
		friendly = true;
		addToCollision();
		hitboxOffset = new int[]{-68, -22, 68, 18};
		
		projectileFireCharge = new LayerData2(this, "projectile/projectilefireCharge", 4, 1);
    	double[][] animation = new double[4][4];
    	animation[0][0] = 100;
    	animation[1][0] = 0;
    	animation[2][0] = 0;
    	animation[0][1] = 200;
    	animation[1][1] = 1;
    	animation[2][1] = 0;
    	animation[0][2] = 300;
    	animation[1][2] = 2;
    	animation[2][2] = 0;
    	animation[0][3] = 350;
    	animation[1][3] = 3;
    	animation[2][3] = 0;
    	projectileFireCharge.animationList.add(animation);
    	projectileFireCharge.layer = defaultLayer-1;
    	projectileFireCharge.color[3]= 0.0f;
    	projectileFireCharge.disableAnimation = true;
		addNewLayer(projectileFireCharge);

		projectileFireDefault = new LayerData2(this, "projectile/projectilefireCharge", 4, 1);
    	double[][] animationD = new double[3][3];
    	animationD[0][0] = 33;
    	animationD[1][0] = 1;
    	animationD[2][0] = 0;
    	animationD[0][1] = 66;
    	animationD[1][1] = 2;
    	animationD[2][1] = 0;
    	animationD[0][2] = 99;
    	animationD[1][2] = 3;
    	animationD[2][2] = 0;
    	projectileFireDefault.animationList.add(animationD);
    	projectileFireDefault.layer = defaultLayer-1;
    	projectileFireDefault.color[3]= 0.0f;
    	projectileFireDefault.disableAnimation = true;
		addNewLayer(projectileFireDefault);
		
		projectileFirePlasma = new LayerData2(this, "projectile/projectilefirePlasma", 4, 1);
		projectileFirePlasma.animationList.add(animation);
		projectileFirePlasma.layer = defaultLayer-1;
		projectileFirePlasma.color[3]= 0.0f;
		projectileFirePlasma.disableAnimation = true;
		addNewLayer(projectileFirePlasma);
		
	}
	protected void checkHP(){
		
	}

	public void update(double delta){
		super.update(delta);
		secondWeapon.update(delta);
	}
	
	public void changeWeapon(int number, Weapon newWeapon){
		if(number == 1){
			this.weapon = newWeapon;
			weapon.friendly = true;
		}
		if(number == 2){
			this.secondWeapon = newWeapon;
			secondWeapon.friendly = true;
		}
	}
	public void chargeFire(){
		this.secondWeapon.fire();
	}
	public void playFireAnimation(LayerData2 fireAnimation){
		projectileFireTime += delta;
		fireAnimation.color[3]= 1.0f;
		fireAnimation.deactivateAfter = true;
		fireAnimation.disableAnimation = false;
	}
	public void playerHit(Entity other){
		if (shieldCharges > 0) {
			shieldCharges -= 1;
		}
		if(other != null){
			((Offensive)other).health -= damage;
		}
	}
}
