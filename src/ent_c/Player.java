package ent_c;

import java.util.Random;

import entities.HUD;
import entities.Moveable;
import entities.Offensive;
import entities_decor.ExplodeVar;
import graphics.GS;
import graphics.LayerData2;
import weapons.*;
import movementV2.*;

/**
 * Player extens Offensive, together with the movement Strategy Pattern the User can control this Object.
 * the PlayerMove movement has special control over this object and its weapon and grapics
 * PlayerControl is the PlayerMove class
 * */

public class Player extends Offensive {
	
	private int defaultLayer = 40;
	public int shieldCharges = 2;
	public Weapon secondWeapon;
	public double chargeLevel = 0;
	public LayerData2 projectileFire;
	protected Random r;
	protected double deathTimer;
	protected double deathExplosions = 10;
	protected boolean isDying = false;
	private boolean flyIn = true;
	public HUD hud = null;
	
	public Player(double posX, double posY) {
		super(posX, posY);
		r = new Random();
		setDmg(500);
		GS.player1 = this;
		weapon = new InfernoWeapon(this, true);
		weapon.weaponOffset = new double[]{-100, -35};
		weapon.friendly = true;
		weaponOffset = new double[]{80, 1};
		changeWeapon(new DefaultWeapon(this, false));
		changeWeapon2(new ChargeWeapon(this, false));
		movement = new TargetPosition(this, 2, 120, 384, false);
		mainTexture = new LayerData2(this, "player", 1, 1);
		mainTexture.layer= defaultLayer;
		addNewLayer(mainTexture);
		friendly = true;
		addToCollision();
		hitboxOffset = new int[]{-68, -22, 68, 18};
		
		projectileFire = new LayerData2(this, "projectile/projectilefireCharge", 4, 1);
    	double[][] ani1 = new double[4][4];
    	ani1[0][0] = 140;
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
	
	/**
	 * Custom getDamage to allow for shield damage and custom death when player is hit.
	 * */
	public void getDamage(int damage, Moveable enemy) {
		if(shieldCharges > 0){
			playerHit();
			new ExplodeVar(enemy.posX, enemy.posY, "explosion/shieldexplosion");
		}else{
			isDying = true;
		}
		
	}
	/**
	 * Custom checkHP for the player
	 * */
	protected void checkHP(){
		if(isDying)death();
	}

	/**
	 * Custom update to allow change of movement to PlayerMove after the initial flight inside the screen.
	 * */
	public void update(double delta){
		super.update(delta);
		secondWeapon.update(delta);
		if(flyIn){
			if(posX >200){
				flyIn = false;
				movement = new PlayerMove(this, 1);
				hud = new HUD(100, 50, movement, 1);
			}
		}
	}

	/**
	 * Custom death animation and reset of the game on player death.
	 * */
	protected void death(){
		deathTimer += delta;
		movement = new Nothing(this);
		if(deathExplosions > 0){
			if(deathTimer > 100){
				double rx = r.nextInt(64);
				double ry = r.nextInt(32);
				double rxp = -1 + 2*r.nextInt(2);
				double ryp = -1 + 2*r.nextInt(2);
				new ExplodeVar(posX+(rxp*rx), posY+(ryp*ry), deathSprite);
				deathTimer = 0;
				deathExplosions--;
				if(deathExplosions <= 4){
					mainTexture.color[3] -= 0.2f;
				}
			}
		}else{
			unsubscribe();
			GS.resetGame(4000);
		}
	}
	
	/**
	 * Method that changes the weapon and sets it to friendly at the same time.
	 * */
	public void changeWeapon(Weapon newWeapon){
		this.weapon = newWeapon;
		weapon.friendly = true;
	}

	/**
	 * Method that changes the second weapon and sets it to friendly at the same time.
	 * */
	public void changeWeapon2(Weapon newWeapon){
		this.secondWeapon = newWeapon;
		secondWeapon.friendly = true;
	}

	/**
	 * Same as fire(), but for the second Weapon.
	 * */
	public void chargeFire(){
		this.secondWeapon.fire();
	}
	
	/**
	 * special method for the player to play firing animations on the player. Only graphical.
	 * */
	public void playFireAnimation(String texturepath, int aniNumber){
		projectileFire.color[3]= 1.0f;
		if(projectileFire.texturepath != texturepath){
			projectileFire.changeTexture(texturepath);
		}
		projectileFire.currentAnimation = aniNumber;
		projectileFire.deactivateAfter = true;
		projectileFire.disableAnimation = false;
	}
	
	/**
	 * method to make a hit on the player, seperate from getDamage() for testing purposes.
	 * */
	public void playerHit(){
		if (shieldCharges > 0) {
			shieldCharges -= 1;
		}
	}
}
