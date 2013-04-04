package moveables;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import observer.*;
import weapons.Canon;
import weapons.Weapon;

public class Testenemy extends Enemy implements Observer {
	protected int health;
	protected Weapon weapon;
	double delta;
	private Subject deltaUpdater;
    protected double posX, posY;
	// Graphics Variables
	private int spritesPerRow;
	private int spritesPerColumn;
	private int spriteWidth;
	private int spriteHeight;
	private int spriteX = 0;
	private int spriteY = 0;
	private int anitimer = 0;
	private int anispeed = 100;
	private Texture texture;
	private String filename = "boss1";
	private int imageWidth = 400;
	private int imageHeight = 128;
	private float texX = 0.0f;
	private float texY = 0.0f;
	private float texXp = 1.0f;
	private float texYp = 1.0f;
	private int layer = 20;

	public Testenemy(Weapon Weapon, Subject getdeltaUpdater){
		super(Weapon, getdeltaUpdater);
		spritesPerRow = 1;
		spritesPerColumn = 1;
        BufferedImage bimg = null;
        try {
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/sprites/"+filename+".png")), GL_NEAREST);
			bimg = ImageIO.read(new File("res/sprites/"+filename+".png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	imageWidth = bimg.getWidth();
    	imageHeight = bimg.getHeight();
    	bimg.flush();
    	spriteWidth = imageWidth/spritesPerRow;
    	spriteHeight = imageHeight/spritesPerColumn;
		calculateSprite();
	}
	public void fire(){
		weapon.fire();
	}
	public void update(double delta) {
		this.delta = delta;
		this.checkSprite();
		this.draw();
	}

	private void checkSprite(){
	}
	
	private void calculateSprite(){
		texX = (float)((double)(spriteX* imageWidth/spritesPerRow) /  texture.getTextureWidth());
		texY = (float)((double)(spriteY* imageHeight/spritesPerColumn) /  texture.getTextureHeight());
		texXp = (float)((double)(spriteX* imageWidth/spritesPerRow+spriteWidth)) /  texture.getTextureWidth();
		texYp = (float)((double)(spriteY* imageHeight/spritesPerColumn+spriteHeight) /  texture.getTextureHeight());
	}
	public void draw(){
        glPushMatrix();
        glTranslatef((int)posX, (int)posY, -layer);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glColor3f(1f, 1f, 1f);
        glBegin(GL_QUADS);
        {
        	glTexCoord2f(texX, texY);
        	glVertex2f(0, 0);
        	
        	glTexCoord2f(texX, texYp);
        	glVertex2f(0, spriteHeight);

        	glTexCoord2f(texXp, texYp);
        	glVertex2f( spriteWidth, spriteHeight);
        	
        	glTexCoord2f(texXp, texY);
        	glVertex2f( spriteWidth, 0);
        }
        glEnd();
        glPopMatrix();
	}
	
}
