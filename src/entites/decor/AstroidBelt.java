package entites.decor;

import entities.*;
import graphics.LayerData2;
import observer.Subject;
import movementV2.*;

public class AstroidBelt extends MoveableDecor {
	
	public AstroidBelt(double posX, double posY, Subject deltaUpdater, int factor) {
		super(posX, posY, deltaUpdater);

		LayerData2 texture1 = new LayerData2(this, "astroidbelt", 1, 1);
		texture1.spriteDisplayX *= factor;
		texture1.spriteDisplayY *= factor;
		texture1.layer = defaultLayer;
		addNewLayer(texture1);
		double speed = 0;
		if(factor == 2) speed = 0.1;
		if(factor == 3) speed = 0.4;
		if(factor == 4) speed = 0.8;
		movement = new InfiniteScroll(this, speed, 0);
	}
}
