package entities_decor;

import entities.*;
import graphics.LayerData2;

/**
 * Black Screen appearing as a visual background that does not interact with
 * the rest of the game.
 */
public class Stars extends StaticDecor {
	
	public Stars(double posX, double posY) {
		super(posX, posY);
		LayerData2 tex = new LayerData2(this, "stars", 1, 1);
		tex.layer = defaultLayer;
		tex.spriteDisplayX = 1280;
		tex.spriteDisplayY = 768;
		addNewLayer(tex);
	}
}
