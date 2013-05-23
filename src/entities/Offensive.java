package entities;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import graphics.GS;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import weapons.Weapon;

/**
 * Offensive can have weapons.
 * */
public abstract class Offensive extends Moveable{
	public Weapon weapon = null;
	public double[] weaponOffset = new double[]{0,0};
	protected double destroyTimer = 0;

	public boolean showBoundary = true;
	
	
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
		if(GS.enableBoundarys){
			mainTexture.drawBoundary = true;
		}else{
			mainTexture.drawBoundary = false;
		}
		if(GS.enableHitbox){
			mainTexture.updateHitbox(hitboxOffset);
			mainTexture.showHitbox = true;
		}else{
			mainTexture.showHitbox = false;
		}
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
	}

	/**
	 * Executes the fire()-method of the assigned weapon.
	 * @see Entity.update(double);
	 * */
	public void fire(){
		this.weapon.fire();
	}
	
	/**
	 * Removes this object from the Observer and the game.
	 * */
	public void unsubscribe(){
		super.unsubscribe();
		weapon = null;
	}
	
	/**
	 * With this a new weapon can be switched
	 * */
	public void changeWeapon(Weapon newWeapon, boolean friendlyToPlayer){
		this.weapon = newWeapon;
		weapon.friendly = friendlyToPlayer;
	}

}
