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
import weapons.Weapon;

public class Movable implements Observer {
	protected int health;
	public double delta;
	protected Subject deltaUpdater;
	//position
    public double posX, posY;
	// Graphics Variables
    protected double spritesPerRow = 1;
    protected double spritesPerColumn = 1;
    public double spriteWidth;
    public double spriteHeight;
    public double boxWidth;
    public double boxHeight;
    protected int spriteX = 0;
    protected int spriteY = 0;
    protected int anitimer = 0;
    protected int anispeed = 100;
    protected Texture texture;
    protected String filename = "spriteship";
    protected int imageWidth = 128;
    protected int imageHeight = 128;
    protected float texX = 0.0f;
    protected float texY = 0.0f;
    protected float texXp = 1.0f;
    protected float texYp = 1.0f;
	protected int layer = 20;
	public double rotation = 0;
    float[] glC = new float[] {1f,1f,1f,1f};
	

	public Movable(double newPosX, double newPosY, Subject getdeltaUpdater){
		this.deltaUpdater = getdeltaUpdater;
		this.deltaUpdater.register(this);
		this.posX = newPosX;
		this.posY = newPosY;

		// from json/database?
	}
	protected void setUpGraphics(String filename){
		this.filename = filename;
		
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
    	boxWidth = spriteWidth;
    	boxHeight = spriteHeight;
		calculateSprite();
	}
	
	public void update(double delta) {
		this.delta = delta;
		this.draw();
		this.specialUpdate();
	}
	
	protected void specialUpdate(){
		//
	}
	protected void calculateSprite(){
		texX = (float)((double)(spriteX* imageWidth/spritesPerRow) /  texture.getTextureWidth());
		texY = (float)((double)(spriteY* imageHeight/spritesPerColumn) /  texture.getTextureHeight());
		texXp = (float)((double)(spriteX* imageWidth/spritesPerRow+spriteWidth)) /  texture.getTextureWidth();
		texYp = (float)((double)(spriteY* imageHeight/spritesPerColumn+spriteHeight) /  texture.getTextureHeight());
	}
	public void draw(){
        glPushMatrix();
        glTranslatef((int)posX, (int)posY, -layer);
        GL11.glRotated(rotation, 0.0, 0.0, 1.0);
        int temp = (int)boxWidth;
        int temp2 = (int)boxHeight;
        glTranslatef(-temp/2, -temp2/2, -layer);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        //GL11.glColor3f(glC[0], glC[1], glC[2]);
        GL11.glColor4f(glC[0], glC[1], glC[2], glC[3]);
        glBegin(GL_QUADS);
        {
        	glTexCoord2f(texX, texY);
        	glVertex2f(0, 0);
        	
        	glTexCoord2f(texX, texYp);
        	glVertex2f(0, (int)boxHeight);

        	glTexCoord2f(texXp, texYp);
        	glVertex2f( (int)boxWidth, (int)boxHeight);
        	
        	glTexCoord2f(texXp, texY);
        	glVertex2f( (int)boxWidth, 0);
        }
        glEnd();
        /*
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        GL11.glColor3f(1f, 1f, 1f);

        glBegin(GL_QUADS);
        {
        	glTexCoord2f(texX, texY);
        	glVertex2f(0, 0);
        	
        	glTexCoord2f(texX, texYp);
        	glVertex2f(0, (int)boxHeight);

        	glTexCoord2f(texXp, texYp);
        	glVertex2f( (int)boxWidth, (int)boxHeight);
        	
        	glTexCoord2f(texXp, texY);
        	glVertex2f( (int)boxWidth, 0);
        }
        glEnd();
        */
        glPopMatrix();
	}
}
