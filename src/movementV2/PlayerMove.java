package movementV2;


import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import entities.*;
import graphics.GS;
import graphics.HUD;
import graphics.LayerData2;
import weapons.*;

/**
 * @author  vmadmin
 */
public class PlayerMove extends Move{
	/**
	 * @uml.property  name="owner"
	 * @uml.associationEnd  
	 */
	protected Entity owner;
	protected int player;
	protected boolean charging = false;
	protected double chargedelta = 0;
	protected boolean fastshot = false;
	protected double fastfiredelay = 0;
	protected double weapondelay = 0;
	protected double maxSpeed = 5.0;
	protected double speedX = 0;
	protected double speedY = 0;
	protected int accel = 15;
	protected boolean movingLeft = false;
	protected boolean movingUp = false;
	protected boolean movingRight = false;
	protected boolean movingDown= false;
	
	//keybinding

	protected int firekey;
	protected int chargekey;
	protected int keepFiring;
	protected int moveUp;
	protected int moveDown;
	protected int moveLeft;
	protected int moveRight;
	public HUD hud = null;
	
	
	//testing
	private float reverse = 0.1f;
	boolean mousefree = true;
	

	public PlayerMove(Entity owner, int player){
		super(owner);
		this.owner = owner;
		this.player = player;
		//HUD init
		hud = new HUD(100, 50, GS.deltaUpdater, this);

		//  get Control for Player from database
		firekey = Keyboard.KEY_A;
		chargekey = Keyboard.KEY_S;
		keepFiring = Keyboard.KEY_D;
		moveUp = Keyboard.KEY_UP;
		moveDown = Keyboard.KEY_DOWN;
		moveLeft = Keyboard.KEY_LEFT;
		moveRight = Keyboard.KEY_RIGHT;
		
	}
	protected void calculateMove(){
		speedX = 0;
		speedY = 0;
    	int tempAccel = 1;
		if(movingRight){
			if (nposX < 0) tempAccel = 2;
			speedX += owner.delta/1000*tempAccel*accel;
		}
		if(movingLeft){
			if (nposX > 0) tempAccel = 2;
			speedX -= owner.delta/1000*tempAccel*accel;
		}
		if(movingDown){
			if (nposY < 0) tempAccel = 2;
			speedY += owner.delta/1000*tempAccel*accel;
		}
		if(movingUp){
			if (nposY > 0) tempAccel = 2;
			speedY -= owner.delta/1000*tempAccel*accel;
		}

		if (!movingRight && ! movingLeft){
			if(Math.abs(nposX) > 0 && Math.abs(nposX) > owner.delta/1000*accel) {
				if (nposX > 0){
					speedX -= owner.delta/1000*accel;
				}else{
					speedX += owner.delta/1000*accel;
				}
			}else{
				nposX = 0;
			}
		}
		if (!movingDown && !movingUp){
			if(Math.abs(nposY) > 0 && Math.abs(nposY) > owner.delta/1000*accel) {
				if (nposY > 0){
					speedY -= owner.delta/1000*accel;
				}else{
					speedY += owner.delta/1000*accel;
				}
			}else{
				nposY = 0;
			}
		}
    	nposX += speedX;
    	nposY += speedY;


        //rotation test
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) {
        	owner.LayerDatas.get(0).rotation += 2.0;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) {
        	owner.LayerDatas.get(0).rotation -= 2.0;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) {
        	owner.LayerDatas.get(0).rotation = 0.0;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) {
    		Random r = new Random();
    		double randomValueY = 0 + 768 * r.nextDouble();
    		GS.fe.create("Asteroid",1400.0, randomValueY);
        }

    	// weapon controls & variables
    	if (weapondelay > 0) weapondelay -= owner.delta;
    	if (fastshot){
    		if (weapondelay <= 0){
    			((Player)owner).fire();
    			weapondelay = ((Player)owner).weapon.weapondelay;
    		}
    	}
    	if (charging){
    		if(chargedelta < 2000){
    			chargedelta += owner.delta;
    			double percent = 0.05 * chargedelta/100;
    			double bigBarPos = hud.hideBBarValue -(hud.hideBBarValue * percent);
    			if (percent*100 >= 60) {
        			float[] color = hud.int_BBar.color;
        			if (color[0] < 1.0f) color[0] += 0.01f;
        			if (color[1] > 0.5f) color[1] -= 0.01f;
    			}
    			if(percent*100 >= 95){
    				if(hud.int_BBarGlow.color[3] < 1.0f) hud.int_BBarGlow.color[3] += 0.05f;
    			}
    			hud.int_BBar.pos = new double[]{bigBarPos, 0.0};
    		}else{
    			chargedelta = 2000;
				if(hud.int_BBarGlow.color[3] > 0.0f) hud.int_BBarGlow.color[3] -= 0.01f;
    		}
			
    		
    		float[] x = ((Player)owner).lights.color;
    		if (x[0] <= 1.0f) x[0] += 0.01f;
    		if (x[1] >= 0.1f) x[1] -= 0.01f;
    		if (x[2] >= 0.1f) x[2] -= 0.01f;
    		if (x[3] <= 1.0f) x[3] += 0.01f;
			
    	}
        while (Keyboard.next()) {
        	if (Keyboard.getEventKeyState()) {
            	//these Keys in this loop fire ONCE when PRESSED
        		if (Keyboard.getEventKey() == moveLeft) {
                	movingLeft = true;
                }
        		if (Keyboard.getEventKey() == moveUp) {
                	movingUp= true;
                }
        		if (Keyboard.getEventKey() == moveRight) {
                	movingRight = true;
                }
        		if (Keyboard.getEventKey() == moveDown) {
                	movingDown = true;
                }
        		if (Keyboard.getEventKey() == firekey) {
        			if (!fastshot && !charging && weapondelay <= 0) {
        				((Player)owner).fire();
            			weapondelay = ((Player)owner).weapon.weapondelay;
        			}
        		}
        		if (Keyboard.getEventKey() == keepFiring) {
        			if(!charging){
            			this.fastshot = true;
            			System.out.println("keepFiring");
        			}
        		}
        		if (Keyboard.getEventKey() == chargekey) {
        			if(!fastshot) {
        				charging = true;
        				System.out.println("Charging!!");
        				System.out.println(hud.defaultColor[0]);

        				System.arraycopy( hud.defaultColor, 0, hud.int_BBar.color, 0, hud.defaultColor.length );
        				
        				
        	    		float[] x = ((Player)owner).lights.color;
        	    		x[0] = 0.2f;
        	    		x[1] = 0.4f;
        	    		x[2] = 0.4f;
        			}
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD1) {
        			System.out.println("Plasma");
        			((Player)owner).changeWeapon("Plasma");
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD2) {
        			System.out.println("Ice");
        			((Player)owner).changeWeapon("Ice");
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD3) {
        			System.out.println("Fire");
        			((Player)owner).changeWeapon("Fire");
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD4) {
        			System.out.println("Default");
        			((Player)owner).changeWeapon("Default");
        		}

        		// Color Test??
        		LayerData2 x = owner.LayerDatas.get(owner.LayerDatas.indexOf(((Player)owner).lights));
    			float r = x.color[0];
    			float g = x.color[1];
    			float b = x.color[2];
    			float a = x.color[3];
    			
        		if (Keyboard.getEventKey() == Keyboard.KEY_F1) {
        			r = x.color[0] +reverse;
        			x.color = new float[]{r, g, b, a};
        			System.out.println(r +"/"+g+"/"+b+"/"+a);
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_F2) {
        			g = x.color[1] +reverse;
        			x.color = new float[]{r, g, b, a};
        			System.out.println(r +"/"+g+"/"+b+"/"+a);
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_F3) {
        			b = x.color[2] + reverse;
        			x.color = new float[]{r, g, b, a};
        			System.out.println(r +"/"+g+"/"+b+"/"+a);
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_F4) {
        			a = ((Player)owner).LayerDatas.get(1).color[3] + reverse;
        			((Player)owner).LayerDatas.get(1).color = new float[]{r, g, b, a};
        			System.out.println(r +"/"+g+"/"+b+"/"+a);
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_F5) {
        			System.out.println("reverse");
        			if(reverse == 0.2f) {
        				reverse = -0.2f; 
        			}else{
        				reverse = 0.2f;
        			}
        		}
        		
        	}else{
        		// Release Key
        		if (Keyboard.getEventKey() == moveLeft) {
                	movingLeft = false;
                }
        		if (Keyboard.getEventKey() == moveUp) {
                	movingUp= false;
                }
        		if (Keyboard.getEventKey() == moveRight) {
                	movingRight = false;
                }
        		if (Keyboard.getEventKey() == moveDown) {
                	movingDown = false;
                }
            	//these Keys in this loop fire ONCE when RELEASED
        		if (Keyboard.getEventKey() == chargekey) {
        			if (!fastshot) {
            			charging = false;
            			System.out.println("Charging released: "+chargedelta);
            			hud.int_BBar.pos = new double[]{hud.hideBBarValue, 0.0};
        				hud.int_BBarGlow.color[3] = 0.0f;

                		float[] x = ((Player)owner).lights.color;
                		x[0] = 0.2f;
                		x[1] = 0.2f;
                		x[2] = 0.2f;
                		x[3] = 0.2f;
            			chargedelta = 0;
        			}
        		}
        		if (Keyboard.getEventKey() == keepFiring) {
        			if(!charging){
            			this.fastshot = false;
            			System.out.println("keeFiring released");
        			}
        		}
        	}
        }
	}
	protected void makeMove(){
		owner.posX += nposX;
		owner.posY += nposY;
		
		if (owner.posX < 0 || owner.posX > 1280){
			if(owner.posX < 0) owner.posX = 0;
			if(owner.posX > 1280) owner.posX = 1280;
			nposX = 0;
			speedX = 0;
		}
		if (owner.posY < 0 || owner.posY > 768){
			if(owner.posY < 0) owner.posY = 0;
			if (owner.posY > 768) owner.posY = 768;
			nposY = 0;
			speedY = 0;
		}
	}
}
