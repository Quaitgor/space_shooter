package player;


import static org.lwjgl.opengl.GL11.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import observer.Observer;
import observer.Subject;
import weapons.Laser;
import weapons.Weapon;
import weapons.Canon;

// class for testing graphics, Input etc, not final player
public class Player implements Observer{
	protected int health;
	protected Weapon weapon;
	double delta;
	private Subject deltaUpdater;
    protected double dx, dy;
    public double posX, posY;
	Vector<Rectangle> Hitboxes = new Vector<Rectangle>();
	Rectangle mainHitbox;

	// Graphics Variables
	private int spritesPerRow;
	private int spritesPerColumn;
	public int spriteWidth;
	public int spriteHeight;
	private int spriteX = 0;
	private int spriteY = 0;
	private int anitimer = 0;
	int anispeed = 100;
	private Texture texture;
	private String filename = "1spriteship";
	int imageWidth = 400;
	int imageHeight = 128;
	float texX = 0.0f;
	float texY = 0.0f;
	float texXp = 1.0f;
	float texYp = 1.0f;
	
	
	//Constructor, with default weapon, register with DeltaObserver
	public Player(Weapon Weapon, Subject getdeltaUpdater){
		if (Weapon != null) {
			this.weapon = Weapon;
		}else{
			this.weapon = new Canon();
		}

		this.deltaUpdater = getdeltaUpdater;
		this.deltaUpdater.register(this);
		
		spritesPerRow = 2;
		spritesPerColumn = 2;
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
	
	// Update is called every delta Update
	public void update(double delta) {
		this.delta = delta;
		this.checkInput();
		this.checkSprite();
		this.draw();
	}
	
	public void checkInput(){
    	double moveX = 0;
    	double moveY = 0;
    	int step = 2;
    	//these Keys fire EVERY UPDATE (movement for example
    	{
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            	moveX = -1 * delta * step *0.1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            	moveX = delta * step *0.1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            	moveY = -1 * delta * step *0.1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            	moveY = delta * step *0.1;
            }
    	}
        posX += moveX;
        posY += moveY;
        while (Keyboard.next()) {
        	if (Keyboard.getEventKeyState()) {
            	//these Keys in this loop fire ONCE when PRESSED
        		if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                	this.weapon.fire();
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_S) {
        			System.out.println("Charging!!");
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_L) {
                	this.weapon = new Laser();
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_C) {
                	this.weapon = new Canon();
        		}
        	}else{
            	//these Keys in this loop fire ONCE when RELEASED
        		if (Keyboard.getEventKey() == Keyboard.KEY_S) {
        			System.out.println("Charging released");
        			//example
        		}
        	}
        }
	}
	private void checkSprite(){
		//make the animation, improve
		int tempSpriteX = spriteX;
		int tempSpriteY = spriteY;
		anispeed = 500;
		if (anitimer <= anispeed-1) {
			spriteX = 0;
			spriteY = 0;
		}
		else if (anitimer >= 1*anispeed-1 && anitimer <= 2*anispeed) {
			spriteX = 1;
			spriteY = 0;
		}
		else if (anitimer >= 2*anispeed-1 && anitimer <= 3*anispeed) {
			spriteX = 0;
			spriteY = 1;
		}
		else if (anitimer >= 3*anispeed-1 && anitimer <= 4*anispeed) {
			spriteX = 1;
			spriteY = 1;
		}
		anitimer += this.delta;
		if (anitimer >= 4*anispeed) anitimer = 0;
		if (tempSpriteX != spriteX || tempSpriteY != spriteY) calculateSprite();
	}
	
	private void calculateSprite(){
		texX = (float)((double)(spriteX* imageWidth/spritesPerRow) /  texture.getTextureWidth());
		texY = (float)((double)(spriteY* imageHeight/spritesPerColumn) /  texture.getTextureHeight());
		texXp = (float)((double)(spriteX* imageWidth/spritesPerRow+spriteWidth)) /  texture.getTextureWidth();
		texYp = (float)((double)(spriteY* imageHeight/spritesPerColumn+spriteHeight) /  texture.getTextureHeight());
	}
	public void draw(){
        glPushMatrix();
        glTranslatef((int)posX, (int)posY, -20);
        //glRotatef(50,0,0,1);
        glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
        glColor3f(1f, 1f, 1f);
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