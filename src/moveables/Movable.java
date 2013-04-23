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
import graphics.GS;
import graphics.LayerData;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

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
    protected int imageWidth = 128;
    protected int imageHeight = 128;
    protected float texX = 0.0f;
    protected float texY = 0.0f;
    protected float texXp = 1.0f;
    protected float texYp = 1.0f;
	protected int layer = 20;
	public double rotation = 0;
    protected float[] glC = new float[] { 0.8f, 0.8f, 0.8f, 1f};
	protected Vector<Texture> texturelayers;
	protected Vector<LayerData> texturelayersdata;
	protected int maintexture = 0;

	public Movable(double newPosX, double newPosY, Subject getdeltaUpdater){
		this.deltaUpdater = getdeltaUpdater;
		this.deltaUpdater.register(this);
		texturelayers = new Vector<Texture>();
		texturelayersdata = new Vector<LayerData>();
		this.posX = newPosX;
		this.posY = newPosY;

		// from json/database?
	}
	public Movable(double newPosX, double newPosY) {
		this.deltaUpdater = GS.deltaUpdater;
		this.posX = newPosX;
		this.posY = newPosY;
	}
	public void setupDelta(){
		this.deltaUpdater.register(this);
	}
	


	private void setupTexture(String texturepath, int texture) {
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File("res/sprites/"+texturepath+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	imageWidth = bimg.getWidth();
    	imageHeight = bimg.getHeight();
    	bimg.flush();
    	spriteWidth = imageWidth/spritesPerRow;
    	spriteHeight = imageHeight/spritesPerColumn;
    	boxWidth = spriteWidth;
    	boxHeight = spriteHeight;
    	maintexture = texture;
		calculateSprite(maintexture, 0, 0);
		
	}
	public void update(double delta) {
		this.delta = delta;
		this.draw();
		this.specialUpdate();
	}
	
	protected void specialUpdate(){
		//
	}
	protected void calculateSprite(int texture, int spriteX, int spriteY){
		texturelayersdata.get(texture).texCords[0] = (float)((double)(spriteX* imageWidth/spritesPerRow) /  texturelayers.get(texture).getTextureWidth());
		texturelayersdata.get(texture).texCords[1] = (float)((double)(spriteY* imageHeight/spritesPerColumn) /  texturelayers.get(texture).getTextureHeight());
		texturelayersdata.get(texture).texCords[2] = (float)((double)(spriteX* imageWidth/spritesPerRow+spriteWidth)) /  texturelayers.get(texture).getTextureWidth();
		texturelayersdata.get(texture).texCords[3] = (float)((double)(spriteY* imageHeight/spritesPerColumn+spriteHeight) /  texturelayers.get(texture).getTextureHeight());
	}
	
	
	protected void addLayer(String texture, Movable owner, double[] pos, int layer, double rotation, double[] size, float[] color, float[] texPos, boolean enableDepth,boolean mainTexture){
		// Create and save textures and its data
		Texture texturelayer = null;
        try {
            texturelayer = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/sprites/"+texture+".png")), GL_NEAREST);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		texturelayers.add(texturelayer);
        texturelayersdata.add(
        	new LayerData(
    			owner,
    			pos,
    			layer,
    			rotation,
    			size,
    			texturelayers.get(texturelayers.indexOf(texturelayer)),
    			color,
    			texPos,
    			enableDepth
    		)
        );
        if(mainTexture)setupTexture(texture, texturelayers.indexOf(texturelayer));
	}
	public void draw(){
		//draw all texturelayers
		if (texturelayers.size() > 0){
			for(int i=0;i<texturelayers.size();i++){
				LayerData o = texturelayersdata.get(i);
				drawLayer(o.getPosX(), o.getPosY(),
				o.layer, o.rotation,
				o.size[0], o.size[1],
				o.tex, o.color,
				o.texCords[0],o.texCords[1],o.texCords[2],o.texCords[3],
				o.enableDepth);
			}
		}
	}
	public void drawLayer(double LposX, double LposY,
			int Llayer, double Lrotation,
			double LboxWidth, double LboxHeight,
			Texture Ltex, float[] Lcolor,
			float LtexX, float LtexY, float LtexXp, float LtexYp,
			boolean disableDepth){
		
		// Draw the layer final step
		glPushMatrix();
        if (!disableDepth) GL11.glEnable(GL11.GL_DEPTH_TEST);
        glTranslatef((int)LposX, (int)LposY, -Llayer);
        GL11.glRotated(Lrotation, 0.0, 0.0, 1.0);
        int temp = (int)LboxWidth;
        int temp2 = (int)LboxHeight;
        glTranslatef(-temp/2, -temp2/2, -layer);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, Ltex.getTextureID());
        //GL11.glColor3f(glC[0], glC[1], glC[2]);
        GL11.glColor4f(Lcolor[0], Lcolor[1], Lcolor[2], Lcolor[3]);
        glBegin(GL_QUADS);
        {
        	glTexCoord2f(LtexX, LtexY);
        	glVertex2f(0, 0);
        	glTexCoord2f(LtexX, LtexYp);
        	glVertex2f(0, (int)LboxHeight);
        	glTexCoord2f(LtexXp, LtexYp);
        	glVertex2f( (int)LboxWidth, (int)LboxHeight);
        	glTexCoord2f(LtexXp, LtexY);
        	glVertex2f( (int)LboxWidth, 0);
        }
        glEnd();
        if (!disableDepth) GL11.glDisable(GL11.GL_DEPTH_TEST);
        glPopMatrix();
	}
}
