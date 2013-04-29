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
	public LayerData2 projectileFire;
	
	public Player(double posX, double posY) {
		super(posX, posY);
		GS.player1 = this;
		//setDmg(10000);
		weapon = new InfernoWeapon(this, true);
		weapon.weaponOffset = new double[]{-100, -35};
		weapon.friendly = true;
		weaponOffset = new double[]{80, 1};
		changeWeapon(new DefaultWeapon(this, false));
		changeWeapon2(new ChargeWeapon(this, false));
		movement = new PlayerMove(this, 1);
		mainTexture = new LayerData2(this, "player", 1, 1);
		mainTexture.layer= defaultLayer;
		addNewLayer(mainTexture);
		friendly = true;
		addToCollision();
		hitboxOffset = new int[]{-68, -22, 68, 18};
		
		projectileFire = new LayerData2(this, "projectile/projectilefireCharge", 4, 1);
    	double[][] ani1 = new double[4][4];
    	ani1[0][0] = 100;
    	ani1[1][0] = 0;
    	ani1[2][0] = 0;
    	ani1[0][1] = 200;
    	ani1[1][1] = 1;
    	ani1[2][1] = 0;
    	ani1[0][2] = 300;
    	ani1[1][2] = 2;
    	ani1[2][2] = 0;
    	ani1[0][3] = 350;
    	ani1[1][3] = 3;
    	ani1[2][3] = 0;
    	double[][] ani2 = new double[3][3];
    	ani2[0][0] = 33;
    	ani2[1][0] = 1;
    	ani2[2][0] = 0;
    	ani2[0][1] = 66;
    	ani2[1][1] = 2;
    	ani2[2][1] = 0;
    	ani2[0][2] = 99;
    	ani2[1][2] = 3;
    	ani2[2][2] = 0;
    	projectileFire.animationList.add(ani1);
    	projectileFire.animationList.add(ani2);
    	projectileFire.layer = defaultLayer-1;
    	projectileFire.color[3]= 0.0f;
    	projectileFire.disableAnimation = true;
		addNewLayer(projectileFire);
	}
	protected void checkHP(){
		
	}

	public void update(double delta){
		super.update(delta);
		secondWeapon.update(delta);
	}

	public void changeWeapon(Weapon newWeapon){
		this.weapon = newWeapon;
		weapon.friendly = true;
	}
	public void changeWeapon2(Weapon newWeapon){
		this.secondWeapon = newWeapon;
		secondWeapon.friendly = true;
	}
	public void chargeFire(){
		this.secondWeapon.fire();
	}
	public void playFireAnimation(String texturepath, int aniNumber){
		projectileFire.color[3]= 1.0f;
		if(projectileFire.texturepath != texturepath){
			projectileFire.changeTexture(texturepath);
			System.out.println("texchange");
		}
		projectileFire.currentAnimation = aniNumber;
		projectileFire.deactivateAfter = true;
		projectileFire.disableAnimation = false;
		/*
		projectileFireTime += delta;
		fireAnimation.color[3]= 1.0f;
		fireAnimation.deactivateAfter = true;
		fireAnimation.disableAnimation = false;
		*/
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
