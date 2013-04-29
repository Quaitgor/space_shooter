package entities;

import entities_decor.Explode;
import entities_decor.ExplodeVar;
import graphics.GS;
import graphics.LayerData2;

import java.awt.Rectangle;
import java.util.Vector;

import movementV2.Move;

/**
 * Moveable extends Entity with the moevment Strategy Pattern
 * */
public abstract class Moveable extends Entity {
	public boolean dontPixelCheck = false;
	public Move movement;
	protected boolean isProjectile = false;
	protected boolean isPowerup = false;
	protected boolean friendly = false;
	public LayerData2 mainTexture = null;
	protected Rectangle hitbox;
	protected int[] hitboxOffset = new int[]{1,1,1,1};
	public int damage = 1;
	public int health = 1;
	protected String deathSprite = "explosion/explosion";
	protected Vector<Moveable> alreadyHit;
	
	public Moveable(double newPosX, double newPosY) {
		super(newPosX, newPosY);
		alreadyHit = new Vector<Moveable>();
	}
	
	/**
	 * with the movment Strategy Pattern the Entity can move itself on the screen, and exchange this behaviour with other Movement Patterns
	 * to sync the movement the update() method is upgraded to include movement
	 * */
	public void update(double delta) {
		super.update(delta);
		if(movement != null)movement.move();
		checkHP();
	}
	public void addToCollision(){
		Vector<Moveable> target = null;
		if(friendly){
			target = GS.friendlys;
		}else{
			target = GS.enemys;
		}
		target.add(this);
	}

	public void getDamage(int damage, Moveable target) {
		if(isProjectile){
			health -= 1;
		}else{
			health -= damage;		
		}
	}
	public void setDmg(int setdmg){
		 damage = setdmg;
	}
	protected void checkHP(){
		if(health <= 0){
			isAlive = false;
			this.death();
		}
	}
	protected void death(){
		unsubscribe();
		new ExplodeVar(posX, posY, deathSprite);
	}
	protected void unsubscribe(){
		super.unsubscribe();
		Vector<Moveable> target = null;
		if(friendly){
			target = GS.friendlys;
		}else{
			target = GS.enemys;
		}
		int index = target.indexOf(this);
		if(index != -1)target.remove(index);
		movement = null;
		mainTexture = null;
		hitbox = null;
	}
}
