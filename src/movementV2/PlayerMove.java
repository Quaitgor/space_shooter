package movementV2;


import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import weapons.*;
import ent_c.Player;
import entities.*;
import graphics.GS;
import graphics.LayerData2;

/**
 * PlayerMove is the movement Strategy Pattern the Player uses
 * this movement pattern does not only control movement of the player,
 * it is used to control the weapon and general input to the player object.
 * */
public class PlayerMove extends Move{
	public Entity owner;
	protected int player;
	protected boolean charging = false;
	public double chargedelta = 0;
	protected boolean fastshot = false;
	protected double maxSpeed = 7.5;
	protected double speedX = 0;
	protected double speedY = 0;
	protected int accel = 50;
	protected boolean movingLeft = false;
	protected boolean movingUp = false;
	protected boolean movingRight = false;
	protected boolean movingDown= false;
	public double chargeTime = 2000;
	
	//keybinding
	protected int firekey;
	protected int chargekey;
	protected int keepFiring;
	protected int moveUp;
	protected int moveDown;
	protected int moveLeft;
	protected int moveRight;
	public double hudX;
	public double hudY;
	public HUD hud = null;
	
	
	

	public PlayerMove(Entity owner, int player){
		super(owner);
		this.owner = owner;
		this.player = player;
		
		//  get HUDPositon & Control for Player from database
		hudX = 100;
		hudY = 50;
		
		HashMap<String, Integer> KeyMap = MenuControler.getKeyMap();
		firekey = KeyMap.get("Fire");
		chargekey = KeyMap.get("Charge");
		keepFiring = KeyMap.get("AutoFire");
		moveUp = KeyMap.get("Up");
		moveDown = KeyMap.get("Down");
		moveLeft = KeyMap.get("Left");
		moveRight = KeyMap.get("Right");
		/*
		firekey = Keyboard.KEY_A;
		chargekey = Keyboard.KEY_S;
		keepFiring = Keyboard.KEY_D;
		moveUp = Keyboard.KEY_UP;
		moveDown = Keyboard.KEY_DOWN;
		moveLeft = Keyboard.KEY_LEFT;
		moveRight = Keyboard.KEY_RIGHT;
		*/
		//setup the HUD
		hud = new HUD(hudX, hudY, this, player);
		
	}
	
	/**
	 * calulateMove() grabs all relevant Input of the user and calulates movement and weaponcontrol for the player Object
	 * */
	protected void calculateMove(){
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
    	
    	//Weapon Controls for Delay and Charge
    	if (fastshot){
    		((Player)owner).fire();
    	}
    	
    	if (charging){
    		if(chargedelta < chargeTime)chargedelta += owner.delta;
			if(chargedelta > chargeTime)chargedelta = chargeTime;
    	}
    	
    	/**
    	 * The Events inside while(Keyboard.next()) fire once when key pressed/released
    	 *  
    	 *  Keyboard.getEventKeyState() == true  => when Key Pressed
    	 *  Keyboard.getEventKeyState() == false => when Key Released
    	 * */
        while (Keyboard.next()) {

        	if (Keyboard.getEventKeyState()) {
        		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD1) {
        			((Player)owner).changeWeapon(new InfernoWeapon(owner, false));
        			((Player)owner).weapon .friendly = true;
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD2) {
        			((Player)owner).weapon = new PlasmaWeapon(owner, false);
        			((Player)owner).weapon .friendly = true;
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_NUMPAD3) {
        			((Player)owner).weapon = new IceWeapon(owner, false);
        			((Player)owner).weapon .friendly = true;
        		}
        		// switch booleans for movement
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
        		// Weapon Controls for charging, firing and Fastshot
        		if (Keyboard.getEventKey() == firekey) {
        			if (!fastshot && !charging) {
        				((Player)owner).fire();
        			}
        		}
        		if (Keyboard.getEventKey() == keepFiring) {
        			if(!charging){
            			this.fastshot = true;
        			}
        		}
        		if (Keyboard.getEventKey() == chargekey) {
        			if(!fastshot) {
        				charging = true;
        			}
        		}
        	}else{
        		// Movement Keys released booleans
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
        		// Weapon controls when Keys released for Charging and Fastshot
        		if (Keyboard.getEventKey() == chargekey) {
        			if (!fastshot) {
        				System.out.println("released Charge");
            			charging = false;
            			((Player)owner).chargeLevel = chargedelta;
            			((Player)owner).chargeFire();
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
	
	/**
	 * makeMove() executes the calulated movements & stops the player from leaving the window
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
