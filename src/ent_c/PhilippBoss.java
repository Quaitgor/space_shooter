package ent_c;

import java.util.Random;

import behaviours.Behave;
import behaviours.FireWhenRange;
import behaviours.NoAction;
import entities.HasMind;
import entities.Offensive;
import entities_decor.ExplodeVar;
import graphics.GS;
import graphics.LayerData2;
import weapons.*;
import movementV2.*;

public class PhilippBoss extends HasMind {
	int defaultLayer = 40;
	protected double deathTimer;
	protected double deathExplosions = 80;
	protected Random r;
	
	public PhilippBoss(double posX, double posY) {
		super(posX, posY);
		r = new Random();
		setDmg(200);
		health = 4000;
		weapon = new BossWeapon(this, true);
		weapon.weaponOffset = new double[]{-100, -35};
		mind = new behaviours.Random6PositionBoss(this,true);
		mainTexture= new LayerData2(this, "boss1", 1, 1);
		mainTexture.layer= defaultLayer;
		hitboxOffset = new int[]{-172, -76, 172, 76};
		addNewLayer(mainTexture);
		movement = new TargetPosition(this, 5, 800, 400, false);
		addToCollision();
	}

	protected void checkHP(){
		if(health <= 0){
			death();
		}
	}
	
	protected void death(){
		deathTimer += delta;
		movement = new Nothing(this);
		if(deathExplosions > 0){
			if(deathTimer > 20){
				double rx = r.nextInt(128);
				double ry = r.nextInt(64);
				double rxp = -1 + 2*r.nextInt(2);
				double ryp = -1 + 2*r.nextInt(2);
				System.out.println(rx +" / "+ry +" / "+rxp +" / "+ryp);
				new ExplodeVar(posX+(rxp*rx), posY+(ryp*ry), deathSprite);
				deathTimer = 0;
				deathExplosions--;
				if(deathExplosions <= 20){
					mainTexture.color[3] -= 0.05f;
				}
			}
		}else{
			unsubscribe();
		}
	}
}
