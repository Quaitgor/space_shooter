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
	
	public Player(double posX, double posY) {
		super(posX, posY);
		GS.player1 = this;
		//setDmg(10000);
		weapon = new IceWeapon(this, false);
		weapon.weaponOffset = new double[]{-100, -35};
		weapon.friendly = true;
		movement = new PlayerMove(this, 1);
		mainTexture = new LayerData2(this, "player", 1, 1);
		mainTexture.layer= defaultLayer;
		addNewLayer(mainTexture);
		friendly = true;
		addToCollision();
		hitboxOffset = new int[]{-68, -22, 68, 18};
	}
	protected void checkHP(){
		
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
