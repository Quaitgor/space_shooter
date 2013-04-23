package entities;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import entites.decor.Hit;

import graphics.GS;
import graphics.LayerData2;
import weapons.Weapon;

/**
 * Offensive extends Moveable with the weapon Strategic Pattern
 * Objects made with this class can use weapons and have health
 * */
public abstract class Offensive extends Moveable{
	public Weapon weapon;
	protected int health = 1;
	protected int offsetX = 0;
	protected int offsetY = 0;
	protected LayerData2 mainTexture = null;
	protected boolean crashed = false;
	protected Rectangle hitbox;
	protected int[] hitboxOffset = new int[]{1,1,1,1};
	protected Collision colchecker = new Collision();

	public Offensive(double newPosX, double newPosY) {
		super(newPosX, newPosY);
		GS.offensive.add(this);
		hitbox = new Rectangle();
	}
	public void update(double delta){
		updateHitbox();
		super.update(delta);
		checkCollision();
		
		/*
		//temp texturebg for hitbox
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
	protected void unsubscribe(){
		super.unsubscribe();
		int offensiveIndex = GS.offensive.indexOf(this);
		GS.offensive.remove(offensiveIndex);
	}
	protected void updateHitbox(){
		hitbox.x = (int)(posX)+ hitboxOffset[0];
		hitbox.y = (int)(posY)+hitboxOffset[1];
		hitbox.width = (int)(posX)+hitboxOffset[2];
		hitbox.height= (int)(posY)+hitboxOffset[3];
	}
	protected void checkCollision(){
    		for (Offensive off: GS.offensive){
    			Offensive other = (Offensive) off;
    			if (!this.equals(other)) colchecker.intersects(this,other);
    		}
			/*
    		if(crashed){
				new Hit(posX, posY);
				crashed = false;
			}
			*/
			//if(health <= 0) this.unsubscribe();
	}
	
	/**
	 * fire() uses the weapons fire() method to fire the weapon
	 * with the weapon Strategy Pattern the weapon can be switched
	 * */
	public void fire(){
		this.weapon.fire();
	}

}
