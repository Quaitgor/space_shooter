package entities;

import java.awt.Rectangle;
import java.util.Vector;

import org.lwjgl.opengl.GL11;

import entities_decor.Explode;

import graphics.GS;
import weapons.Weapon;

/**
 * Offensive can have weapons, has health and can therefore die.
 * */
public abstract class Offensive extends Moveable{
	public Weapon weapon = null;
	public double[] weaponOffset = new double[]{0,0};
	protected double destroyTimer = 0;

	public Offensive(double newPosX, double newPosY) {
		super(newPosX, newPosY);
		hitbox = new Rectangle();
	}
	/**
	 * Update is extended to include sending the Delta to the weapon
	 * and update the hitbox of the object.
	 * @see Moveable.update(double);
	 */
	public void update(double delta){
		updateHitbox();
		super.update(delta);
		if(weapon != null)weapon.update(delta);
        
	}
	/**
	 * Updates the Hitboxes, to move them wit the object itself
	 * */
	protected void updateHitbox(){
		hitbox.x = (int)(posX)+ hitboxOffset[0];
		hitbox.y = (int)(posY)+hitboxOffset[1];
		hitbox.width = (int)(posX)+hitboxOffset[2];
		hitbox.height= (int)(posY)+hitboxOffset[3];
		//temp texturebg for hitbox
		/*
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1f,1f,1f,0.5f);
        GL11.glBegin(GL11.GL_QUADS);
        {
        	GL11.glTexCoord2f(0, 0);
        	GL11.glVertex2f(hitbox.x, hitbox.y);
        	GL11.glTexCoord2f(0, 1);
        	GL11.glVertex2f(hitbox.x, hitbox.height);
        	GL11.glTexCoord2f(1, 1);
        	GL11.glVertex2f(hitbox.width, hitbox.height);
        	GL11.glTexCoord2f(1, 0);
        	GL11.glVertex2f(hitbox.width, hitbox.y);
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
		*/
	}

	/**
	 * Executes the fire()-method of the assigned weapon.
	 * @see Entity.update(double);
	 * */
	public void fire(){
		this.weapon.fire();
	}
	public void unsubscribe(){
		super.unsubscribe();
		weapon = null;
	}
	public void changeWeapon(Weapon newWeapon){
		this.weapon = newWeapon;
		weapon.friendly = true;
	}

}
