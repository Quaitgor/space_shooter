package movement;

import moveables.Movable;
import moveables.Player;

import org.lwjgl.input.Keyboard;
import weapons.Canon;
import weapons.Laser;

public class PlayerMove extends Move{
	protected Movable owner;
	protected int firekey;
	protected int chargekey;
	protected int changeLaser;
	protected int changeCanon;
	protected int keepFiring;
	protected int player;
	protected boolean charging = false;
	protected double chargedelta = 0;
	protected boolean fastshot = false;
	protected double fastfiredelay = 0;
	protected double weapondelay = 0;
	
	public PlayerMove(Movable owner, int player){
		super(owner);
		this.owner = owner;
		this.player = player;
		

		//  get Control for Player from database
		firekey = Keyboard.KEY_A;
		chargekey = Keyboard.KEY_S;
		changeLaser = Keyboard.KEY_L;
		changeCanon = Keyboard.KEY_C;
		keepFiring = Keyboard.KEY_D;
		// test for player 2
		if (player == 2) firekey = Keyboard.KEY_Z;
		fastfiredelay = 600;
		
		
	}
	public void move(){
		checkInput();
		makeMove();
	}
	protected void checkInput(){
    	double moveX = 0;
    	double moveY = 0;
    	int step = 2;
    	
    	// weapon controls & variables
    	if (weapondelay > 0) weapondelay -= owner.delta;
    	if (fastshot){
    		if (weapondelay <= 0){
    			((Player)owner).weapon.fire();
    			weapondelay = fastfiredelay;
    		}
    	}
    	if (charging){
    		chargedelta += owner.delta;
    	}
    	
    	//these Keys fire EVERY UPDATE (movement for example)
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
        	moveX = -1 * owner.delta * step *0.1;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
        	moveX = owner.delta * step *0.1;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
        	moveY = -1 * owner.delta * step *0.1;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
        	moveY = owner.delta * step *0.1;
        }
        //rotation test
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) {
        	owner.rotation += 2.0;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) {
        	owner.rotation -= 2.0;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) {
        	owner.rotation = 0.0;
        }
    	nposX = moveX;
    	nposY = moveY;
    	
    	
        while (Keyboard.next()) {
        	if (Keyboard.getEventKeyState()) {
            	//these Keys in this loop fire ONCE when PRESSED
        		if (Keyboard.getEventKey() == firekey) {
        			if (!fastshot && !charging && weapondelay <= 0) {
        				((Player)owner).weapon.fire();
            			weapondelay = fastfiredelay;
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
        			}
        		}
        		if (Keyboard.getEventKey() == changeLaser) {
        			((Player)owner).weapon = new Laser();
        		}
        		if (Keyboard.getEventKey() == changeCanon) {
        			((Player)owner).weapon = new Canon();
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_2) {
        			((Player)owner).setMove(new PlayerMove(owner, 2));
        		}
        	}else{
            	//these Keys in this loop fire ONCE when RELEASED
        		if (Keyboard.getEventKey() == chargekey) {
        			if (!fastshot) {
            			charging = false;
            			System.out.println("Charging released: "+chargedelta);
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
