package entities_decor;

import entities.*;
import graphics.LayerData2;
import movementV2.*;

/**
 * An asteroid belt appearing as a visual background which does not interact with
 * the rest of the game.
 */
public class AstroidBelt extends MoveableDecor {
	
	public AstroidBelt(double posX, double posY, int factor) {
		super(posX, posY);
		mainTexture = new LayerData2(this, "astroidbelt", 1, 1);
		mainTexture.spriteDisplayX *= factor;
		mainTexture.spriteDisplayY *= factor;
		mainTexture.layer = defaultLayer;
		addNewLayer(mainTexture);
		double speed = 0;
		if(factor == 2) speed = 0.1;
		if(factor == 3) speed = 0.4;
		if(factor == 4) speed = 0.8;
		this.posX = posX;
		this.posY = posY; 
		movement = new InfiniteScroll(this, speed, 0);
	}
}
