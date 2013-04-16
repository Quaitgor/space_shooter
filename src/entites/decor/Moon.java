package entites.decor;

import entities.Entity;
import graphics.LayerData2;
import observer.Subject;
import movementV2.*;

public class Moon extends Entity {
	
	public Moon(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		
		addNewLayer(new LayerData2(this, "moon", 1, 1));
	}
}
