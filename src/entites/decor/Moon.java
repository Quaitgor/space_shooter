package entites.decor;

import entities.Entity;
import graphics.LayerData2;
import observer.Subject;
import movementV2.*;

public class Moon extends StaticDecor {
	
	public Moon(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		LayerData2 tex = new LayerData2(this, "moon", 1, 1);
		tex.layer = defaultLayer;
		tex.spriteDisplayX *= 2;
		tex.spriteDisplayY *= 2;
		addNewLayer(tex);
	}
}
