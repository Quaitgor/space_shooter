package entities;

import entities_decor.Explode;
import entities_decor.ExplodeVar;
import graphics.GS;
import graphics.LayerData2;

import java.awt.Rectangle;
import java.util.Vector;

import movementV2.Move;

/**
 * Moveable is a non-static Entity which through the Strategy Pattern is able
 * to be moved across the screen in different and changeable patterns. 
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
	 * Moves the entity across the screen when the deltaUpdater calls it.
	 * */
	public void update(double delta) {
		super.update(delta);
		if(movement != null)movement.move();
		checkHP();
	}
	/**
	 * Adds the Object to the Vectors for friendlys or enemys
	 * depending on the friendly-boolean
	 * */
	public void addToCollision(){
		Vector<Moveable> target = null;
		if(friendly){
			target = GS.friendlys;
		}else{
			target = GS.enemys;
		}
		target.add(this);
	}

	/**
	 * Damages the Object, subclasses can use the target to identify the object
	 * making the damage and can use it to act
	 * */
	public void getDamage(int damage, Moveable target) {
		if(isProjectile){
			health -= 1;
		}else{
			health -= damage;		
		}
	}
	
	/**
	 * setdmg setter method
	 * */
	public void setDmg(int setdmg){
		 damage = setdmg;
	}
	
	/**
	 * Checks whether the Moveable is still alive.
	 */
	protected void checkHP(){
		if(health <= 0){
			isAlive = false;
			this.death();
		}
	}
	/**
	 * Unsubscribes the Moveable and detonates it visually.
	 */
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
