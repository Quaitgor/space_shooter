package inputs;

import org.lwjgl.input.Keyboard;
import weapons.*;

import moveables.*;


public class PlayerControl extends Input{
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
	
	public PlayerControl(Movable owner, int player){
		this.owner = owner;
		this.player = player;
		
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
    	owner.posX += moveX;
    	owner.posY += moveY;
    	
    	
    	
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
        			((Player)owner).setInput(new PlayerControl(owner, 2));
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
}
