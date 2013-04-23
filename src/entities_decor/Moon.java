package entities_decor;

import entities.StaticDecor;
import graphics.LayerData2;
import observer.Subject;

public class Moon extends StaticDecor {
	
	public Moon(double posX, double posY) {
		super(posX, posY);
		LayerData2 tex = new LayerData2(this, "moon", 1, 1);
		tex.layer = defaultLayer;
		tex.spriteDisplayX *= 2;
		tex.spriteDisplayY *= 2;
		addNewLayer(tex);
	}
}
