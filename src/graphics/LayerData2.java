package graphics;

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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import entities.Entity;


/**
 * LayerData (V2) is the Texture Handler, inside LayerData are the Data for Animation, texture, Size and Color for a layer of Graphic on an object.
 * Together with other LayerData an Object can be build with multiple layers of textures, each individual size color and position relative to the object
 */

public class LayerData2 {
	/**
	 * @uml.property  name="owner"
	 * @uml.associationEnd  
	 */
	public Entity owner;
	public double[] pos = new double[]{0,0};
	public double rotation = 0;
	public int layer = 40;
	public Texture tex;
	public float[] color =  new float[]{0.75f,0.75f,0.75f,1.0f};
	public float [] texCords;
	public boolean enableDepth = false;
	protected double anitimer = 0;
	
	public String texturepath = "";
    public double spritesV = 1;
    public double spritesH = 1;
    
    public double spriteWidth;
    public double spriteHeight;
    
    public double spriteDisplayX;
    public double spriteDisplayY;
    
    public int spriteV = 0;
    public int spriteH = 0;
    protected int anispeed = 100;
    protected int imageWidth = 128;
    protected int imageHeight = 128;
    
    public float texX = 0.0f;
    public float texY = 0.0f;
    public float texXp = 1.0f;
    public float texYp = 1.0f;
	public ArrayList<double[][]> animationList;
	protected int currentAnimation = 0;
    public boolean disableDepth = false;
    

	public LayerData2(Entity moveable, String texturepath, int h, int v) {
		this.owner = moveable;
    	this.spritesV = h;
    	this.spritesH = v;
    	this.texturepath = texturepath;
    	setupTexture();
    	calculateSprite();
    	animationList = new ArrayList<double[][]>();
		// Create and save textures and its data
	}
	/**
	 * setupTexture calulates important variables and saves them for other methods, normaly only executed once
	 * */
	private void setupTexture() {
		BufferedImage bimg = null;
		try {
			tex = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/sprites/"+texturepath+".png")), GL_NEAREST);
			bimg = ImageIO.read(new File("res/sprites/"+texturepath+".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	   	imageWidth = bimg.getWidth();
    	imageHeight = bimg.getHeight();
    	bimg.flush();    	
       	spriteWidth = imageWidth/spritesH;
    	spriteHeight = imageHeight/spritesV;
    	spriteDisplayX = spriteWidth;
    	spriteDisplayY = spriteHeight;
	}

	/**
	 * calulateSprite readjusts the texture on the sprite thats been selected with spriteH & spriteV
	 * */
	protected void calculateSprite(){
		texX =  (float)((double)(spriteV* imageWidth/spritesH) /  imageWidth);
		texY =  (float)((double)(spriteH* imageHeight/spritesV) /  imageHeight);
		texXp = (float)((double)(spriteV* imageWidth/spritesH+spriteWidth) /  imageWidth);
		texYp = (float)((double)(spriteH* imageHeight/spritesV+spriteHeight) /  imageHeight);
	}
	
	/**
	 * drawLayer draws checks the animation(if any) and draws the layer
	 * */
	public void drawLayer(){
		if (animationList.size() > 0){
			checkAnimation();
		}
		drawThisLayer();
		
	}
	
	/**
	 * checkAnimation checks if another Sprite needs to be set for the next draw, it uses the Arraylist and arrays inside it  and the delta of its owner
	 * to check for any change needed
	 * */
	protected void checkAnimation(){
		int tempSpriteX = spriteH;
		int tempSpriteY = spriteV;
		this.anitimer += owner.delta;
		double[][] list = animationList.get(currentAnimation);
    	for(int i=0;i<list[0].length;i++){

    		if (anitimer > list[0][i] && anitimer <= list[0][i+1]){
    			tempSpriteX = (int) list[1][i+1];
    			tempSpriteY = (int) list[2][i+1];
    		}
			if(anitimer >= list[0][list[0].length-1]) {
				anitimer = 0;
    			tempSpriteX = (int)list[1][0];
    			tempSpriteY = (int)list[2][0];
			}
    	}
    	if(spriteH != tempSpriteX || spriteV != tempSpriteY){
    		changeSprite(tempSpriteX, tempSpriteY);
    	}
	}
	
	/**
	 * drawThisLayer is the draw command for GL to draw a textured quad with the texture provided (and with the correct selected sprite)
	 * */
	protected void drawThisLayer(){
		// Draw the layer final step
        GL11.glEnable(GL11.GL_TEXTURE_2D);
		glPushMatrix();
        if (!disableDepth) GL11.glEnable(GL11.GL_DEPTH_TEST);
        glTranslatef((int)getPosX(), (int)getPosY(), -layer);
        GL11.glRotated(rotation, 0.0, 0.0, 1.0);
        int temp = (int)spriteDisplayX;
        int temp2 = (int)spriteDisplayY;
        glTranslatef(-temp/2, -temp2/2, -layer);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getTextureID());
        //GL11.glColor3f(glC[0], glC[1], glC[2]);
        GL11.glColor4f(color[0], color[1], color[2], color[3]);
        glBegin(GL_QUADS);
        {
        	glTexCoord2f(texX, texY);
        	glVertex2f(0, 0);
        	glTexCoord2f(texX, texYp);
        	glVertex2f(0, (int)spriteDisplayY);
        	glTexCoord2f(texXp, texYp);
        	glVertex2f( (int)spriteDisplayX, (int)spriteDisplayY);
        	glTexCoord2f(texXp, texY);
        	glVertex2f( (int)spriteDisplayX, 0);
        }
        glEnd();
        if (!disableDepth) GL11.glDisable(GL11.GL_DEPTH_TEST);
        glPopMatrix();
	}
	
	/**
	 * changeSprite is a shorthand method to change spriteH and spriteV and execute calculateSprite
	 * */
	public void changeSprite(int h, int v){
		this.spriteH = h;
		this.spriteV = v;
		calculateSprite();
	}
	/**
	 * setSpriteDisplaySize changes the displayed size of the object
	 * */
	public void setSpriteDisplaySize(double x, double y){
		spriteDisplayX = x;
		spriteDisplayY = y;
	}
	/**
	 * getPosX calulates its position relative to the position of its owner
	 * */
	public double getPosX(){
		return owner.posX + pos[0];
	}
	/**
	 * getPosX calulates its position relative to the position of its owner
	 * */
	public double getPosY(){
		return owner.posY + pos[1];
	}
}
