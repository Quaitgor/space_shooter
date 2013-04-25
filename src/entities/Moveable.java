package entities;

import graphics.GS;
import graphics.LayerData2;

import java.awt.Rectangle;
import java.util.Vector;

import movementV2.Move;

/**
 * Moveable extends Entity with the moevment Strategy Pattern
 * */
public abstract class Moveable extends Entity {
	public Move movement;
	protected boolean friendly = false;
	public LayerData2 mainTexture = null;
	protected Rectangle hitbox;
	protected int[] hitboxOffset = new int[]{1,1,1,1};
	
	public Moveable(double newPosX, double newPosY) {
		super(newPosX, newPosY);
	}
	
	/**
	 * with the movment Strategy Pattern the Entity can move itself on the screen, and exchange this behaviour with other Movement Patterns
	 * to sync the movement the update() method is upgraded to include movement
	 * */
	public void update(double delta) {
		super.update(delta);
		movement.move();
	}
	public void addToCollision(){
		Vector target = null;
		if(friendly){
			target = GS.friendlys;
		}else{
			target = GS.enemys;
		}
		target.add(this);
	}
}
