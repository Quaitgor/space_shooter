package entities;

import java.awt.Rectangle;
import java.util.Vector;

import org.lwjgl.opengl.GL11;

import entities_decor.Explode;

import graphics.GS;
import weapons.Weapon;

/**
 * Offensive extends Moveable with the weapon Strategic Pattern
 * Objects made with this class can use weapons and have health
 * */
public abstract class Offensive extends Moveable{
	public Weapon weapon;
	public double damage = 0;
	public int health = 1;
	protected int offsetX = 0;
	protected int offsetY = 0;
	protected boolean crashed = false;
	protected double destroyTimer = 0;

	public Offensive(double newPosX, double newPosY) {
		super(newPosX, newPosY);
		hitbox = new Rectangle();
	}
	
	public void update(double delta){
		updateHitbox();
		super.update(delta);
		checkHP();
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
	protected void unsubscribe(){
		super.unsubscribe();
		Vector target = null;
		if(friendly){
			target = GS.friendlys;
		}else{
			target = GS.enemys;
		}
		int index = target.indexOf(this);
		if(index != -1)target.remove(index);
	}
	public void setDmg(double setdmg){
		 damage = setdmg;
	}
	protected void updateHitbox(){
		hitbox.x = (int)(posX)+ hitboxOffset[0];
		hitbox.y = (int)(posY)+hitboxOffset[1];
		hitbox.width = (int)(posX)+hitboxOffset[2];
		hitbox.height= (int)(posY)+hitboxOffset[3];
	}
	
	public void exchangeCollision(Entity other){
		this.health -= ((Offensive)other).damage;
		((Offensive)other).health -= damage;
	}
	protected void checkHP(){
		if(health <= 0){
			setDmg(0);
			//destroy animation here
			if(destroyTimer == 0)new Explode(posX, posY);
			destroyTimer += delta;
			mainTexture.color[3] -= 0.2f;
			if(destroyTimer >= 500){
				this.unsubscribe();
				
			}
		}
	}
	/**
	 * fire() uses the weapons fire() method to fire the weapon
	 * with the weapon Strategy Pattern the weapon can be switched
	 * */
	public void fire(){
		this.weapon.fire();
	}

}
