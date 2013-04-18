package movementV2;


import org.lwjgl.input.Keyboard;
import entities.*;
import graphics.LayerData2;
import weapons.*;

public class PlayerMove extends Move{
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
	
	
	//testing
	private float reverse = 0.1f;
	

	public PlayerMove(Entity owner, int player){
		super(owner);
		this.owner = owner;
		this.player = player;
		

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
    	
    	int tempAccel = 1;
		if(movingLeft && !movingRight){
			if (speedX > 0) tempAccel = 2;
			if (speedX > -1*maxSpeed)speedX -= owner.delta/1000*tempAccel*accel;
		}
		else if (movingRight && !movingLeft){
			if (speedX < 0)  tempAccel = 2;
			if (speedX < maxSpeed)speedX += owner.delta/1000*tempAccel*accel;
		}
		if(!movingLeft && !movingRight){
			if(speedX > 0) speedX -= owner.delta/1000*accel/2;
			if(speedX < 0) speedX += owner.delta/1000*accel/2;
			if(Math.abs(speedX) > 0 && (Math.abs(speedX) < owner.delta/1000*accel)) speedX = 0;
		}

		if(movingUp && !movingDown){
			if (speedY < 0)  tempAccel = 2;
			if (speedY > -1*maxSpeed) speedY -= owner.delta/1000*tempAccel*accel;
		}
		else if (movingDown && !movingUp){
			if (speedY > 0)  tempAccel = 2;
			if (speedY < maxSpeed)speedY += owner.delta/1000*tempAccel*accel;
			
		}
		if(!movingUp && !movingDown){
			if(speedY > 0) speedY -= owner.delta/1000*accel/2;
			if(speedY < 0) speedY += owner.delta/1000*accel/2;
			if(Math.abs(speedY) > 0 && (Math.abs(speedY) < owner.delta/1000*accel)) speedY = 0;
		}
    	nposX = speedX;
    	nposY = speedY;


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
        
    	// weapon controls & variables
    	if (weapondelay > 0) weapondelay -= owner.delta;
    	if (fastshot){
    		if (weapondelay <= 0){
    			((Player)owner).fire();
    			weapondelay = ((Player)owner).weapon.weapondelay;
    		}
    	}
    	if (charging){
    		chargedelta += owner.delta;
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
	}
}
