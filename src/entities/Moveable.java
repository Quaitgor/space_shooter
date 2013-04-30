package entities;

import entites_decor.ExplodeVar;
import entities_decor.Explode;
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
	protected boolean friendly = false;
	public LayerData2 mainTexture = null;
	protected Rectangle hitbox;
	protected int[] hitboxOffset = new int[]{1,1,1,1};
	public int damage = 1;
	public int health = 1;
	protected String deathSprite = "explosion/explosion";
	
	public Moveable(double newPosX, double newPosY) {
		super(newPosX, newPosY);
	}
	
	/**
	 * Moves the entity across the screen when the deltaUpdater calls it.
	 * */
	public void update(double delta) {
		super.update(delta);
		if(movement != null)movement.move();
		checkHP();
	}
	//Diese Methode muss Christian erklären.
	public void addToCollision(){
		Vector target = null;
		if(friendly){
			target = GS.friendlys;
		}else{
			target = GS.enemys;
		}
		target.add(this);
	}

	public void getDamage(int damage) {
		health -= damage;		
	}
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
			/*
			setDmg(0);
			//destroy animation here
			if(destroyTimer == 0)new Explode(posX, posY);
			destroyTimer += delta;
			mainTexture.color[3] -= 0.2f;
			if(destroyTimer >= 500){
				
			}*/
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
