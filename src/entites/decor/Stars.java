package entites.decor;

import entities.*;
import graphics.LayerData2;
import observer.Subject;
import movementV2.*;

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
