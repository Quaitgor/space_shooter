package movementV2;


import java.util.Random;

import org.lwjgl.input.Keyboard;

import ent_c.Player;
import entities.*;
import graphics.GS;
import graphics.LayerData2;

//cn
public class TestingControl extends PlayerMove{
	
	
	public TestingControl(Entity owner, int player){
		super(owner, player);
	}
	protected void calculateMove(){
		super.calculateMove();
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
    		//GS.fe.create("Asteroid",1400.0, randomValueY);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) {
    		//GS.fe.create("BarrierHit", owner.posX-100, owner.posY, 180.0);
        }

        while (Keyboard.next()) {
        }
	}
}
