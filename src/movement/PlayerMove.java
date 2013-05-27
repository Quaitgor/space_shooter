package movement;


import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import powerups.IcePower;
import weapons.ChargeWeapon;

import ent_c.Player;
import entities.*;
import factory.Spawner;
import graphics.GS;

/**
 * PlayerMove is the movement Strategy Pattern the Player uses.
 * This movement does not only control movement of the player,
 * it is used to control the weapon and general input to the player object.
 * */
public class PlayerMove extends Move{
	public Entity owner;
	protected int player;
	protected boolean charging1 = false;
	protected boolean charging2 = false;
	protected double releaseChargeKey = 0;
	protected boolean fastshot = false;
	protected double maxSpeed = 7.5;
	protected double speedX = 0;
	protected double speedY = 0;
	protected int accel = 50;
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
	
	protected boolean strategyPres = false;
	protected boolean layerDataPres = true;

	public PlayerMove(Entity owner, int player){
		super(owner);
		this.owner = owner;
		this.player = player;
		
		firekey = Keyboard.KEY_A;
		chargekey = Keyboard.KEY_S;
		keepFiring = Keyboard.KEY_D;
		moveUp = Keyboard.KEY_UP;
		moveDown = Keyboard.KEY_DOWN;
		moveLeft = Keyboard.KEY_LEFT;
		moveRight =  Keyboard.KEY_RIGHT;
	}
	
	/**
	 * This method grabs all relevant Input of the user and calulates movement
	 * and weaponcontrol for the Player.
	 * */
	protected void switchControl(int number){
		layerDataPres = false;
		strategyPres = false;
		switch(number){
		case Keyboard.KEY_F1:
			strategyPres = true;
			System.out.println("Strategy Presentation");
			GS.levelgen = new Spawner("strategydemo", GS.deltaUpdater);
			break;
		case Keyboard.KEY_F2:
			layerDataPres = true;
			System.out.println("Layer Presentation");
			break;
		}
	}
	
	protected void calculateMove(){
		
		double timer = 2;
		// Presentation Code

		if (Keyboard.isKeyDown(Keyboard.KEY_F11)){
			GS.customReset();
		}
		if(layerDataPres){
			if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)){
    			((Player)owner).mainTexture3.spriteDisplayY -= timer;
    		} 
			if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)){
    			((Player)owner).mainTexture3.spriteDisplayY += timer;
    		}
			if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)){
    			((Player)owner).mainTexture3.spriteDisplayX -= timer;
    		}
			if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)){
    			((Player)owner).mainTexture3.spriteDisplayX += timer;
    		}
			

			if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)){
    			((Player)owner).mainTexture2.rotation += timer;
    		}
			if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)){
    			((Player)owner).mainTexture2.rotation -= timer;
    		}
			
			
			if (Keyboard.isKeyDown(Keyboard.KEY_ADD)){
    			((Player)owner).mainTexture2.pos[1] += timer;
    			((Player)owner).mainTexture3.pos[1] -= timer;
    		}
			if (Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)){
    			((Player)owner).mainTexture2.pos[1] -= timer;
    			((Player)owner).mainTexture3.pos[1] += timer;
    		}
		}
		// Presentation Code End
		
		
		
		
		
		// Movement Controls
		speedX = 0;
		speedY = 0;
    	int tempAccel = 1;
		if(movingRight){
			if (nposX < 0) tempAccel = 4;
			speedX += owner.delta/1000*tempAccel*accel;
		}
		if(movingLeft){
			if (nposX > 0) tempAccel = 4;
			speedX -= owner.delta/1000*tempAccel*accel;
		}
		if(movingDown){
			if (nposY < 0) tempAccel = 4;
			speedY += owner.delta/1000*tempAccel*accel;
		}
		if(movingUp){
			if (nposY > 0) tempAccel = 4;
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
    	if(Math.abs(nposX) > Math.abs(maxSpeed)) {
    		double reverse = 1;
    		if(nposX < 1) reverse = -1;
    		nposX = reverse*maxSpeed;
    	}
    	if(Math.abs(nposY) > Math.abs(maxSpeed)){
    		double reverse = 1;
    		if(nposY < 1) reverse = -1;
    		nposY = reverse*maxSpeed;
    	}
    	//Weapon Controls
    	if (fastshot){
    		((Player)owner).fire();
    	}
    	if (charging1 || charging2){
    		 ((ChargeWeapon)((Player)owner).secondWeapon).chargingWeapon();
    	}
    	
    	/**
    	 * The Events inside while(Keyboard.next()) fire once when key pressed/released.
    	 *  - Keyboard.getEventKeyState() == true  => when Key Pressed.
    	 *  - Keyboard.getEventKeyState() == false => when Key Released.
    	 * */
        while (Keyboard.next()) {
        	if (Keyboard.getEventKeyState()) {

        		// Presentation Code
        		if (Keyboard.getEventKey() == Keyboard.KEY_F1 ||Keyboard.getEventKey() == Keyboard.KEY_F2 || Keyboard.getEventKey() == Keyboard.KEY_F3) {
        			switchControl(Keyboard.getEventKey());
        		}
        		if(layerDataPres){
            		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD1) {
            			GS.enableBoundarys();
            		}
            		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD3) {
            			GS.enableHitboxes();
            		}
        		}
        		
        		// Presentation Code End

        		if (Keyboard.getEventKey() == Keyboard.KEY_F12) {
        			GS.resetGame(0);
        		}
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
        			if (!fastshot && !charging1 && !charging2) {
        				((Player)owner).fire();
        			}
        		}
        		if (Keyboard.getEventKey() == keepFiring) {
        			if(!charging1 && !charging2){
            			this.fastshot = true;
        			}
        		}
        		if (Keyboard.getEventKey() == chargekey || Keyboard.getEventKey() == Keyboard.KEY_F) {
        			if(!fastshot) {
        				if(Keyboard.getEventKey() == chargekey){
            				charging1 = true;
        				}
        				if(Keyboard.getEventKey() == Keyboard.KEY_F){
            				charging2 = true;
        				}
        			}
        		}
        	}else{
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
        		if (Keyboard.getEventKey() == chargekey || Keyboard.getEventKey() == Keyboard.KEY_F) {
        			if (!fastshot) {
        				if(Keyboard.getEventKey() == chargekey){
                			charging1 = false;
                			releaseChargeKey = chargekey;
        				}
        				if(Keyboard.getEventKey() == Keyboard.KEY_F){
                			charging2 = false;
                			releaseChargeKey = Keyboard.KEY_F;
        				}
        				if(!charging1 && !charging2){
        					if(releaseChargeKey == chargekey){
        						((ChargeWeapon)((Player)owner).secondWeapon).burstSwitch = false;
        					}
        					if(releaseChargeKey == Keyboard.KEY_F){
        						((ChargeWeapon)((Player)owner).secondWeapon).burstSwitch = true;
        					}
    						((Player)owner).chargeFire();
        				}
        			}
        		}
        		if (Keyboard.getEventKey() == keepFiring) {
        			if(!charging1 && !charging2){
            			this.fastshot = false;
            			System.out.println("keeFiring released");
        			}
        		}
        	}
        }
	}
	
	/**
	 * this method executes the calulated movements & prevents the player from leaving the window
	 * */
	protected void makeMove(){

		if (owner.posX > 1280 || owner.posX < 0){
			nposX = 0;
			speedX = 0;
			if(owner.posX > 1280){
				owner.posX = 1280;
			}
			if(owner.posX < 0){
				owner.posX = 0;
			}
		}
		if (owner.posY > 768 || owner.posY < 0){
			nposY = 0;
			speedY = 0;
			if(owner.posY > 768){
				owner.posY = 768;
			}
			if(owner.posY < 0){
				owner.posY = 0;
			}
		}	
		owner.posX += nposX;
		owner.posY += nposY;		
	}
}
