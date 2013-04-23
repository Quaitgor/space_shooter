package entities_decor;

import entities.*;
import graphics.GS;
import graphics.LayerData2;
import observer.Subject;

public class ForceField extends Entity {
	
	public ForceField(double posX, double posY) {
		super(posX, posY);
		
		for(int i=0;i<9;i++){
			for(int j=0; j<3;j++){
				new ForceFieldPart(posX+(i*14), posY+(j*14));
				new ForceFieldPart(posX+(i*14), posY-(j*14));
				new ForceFieldPart(posX-(i*14), posY+(j*14));
				new ForceFieldPart(posX-(i*14), posY-(j*14));
				
			}
		}
		/*
		int yg = 17;
		int xg = 14;
		
		new ForceFieldPart(posX, posY, deltaUpdater);
		new ForceFieldPart(posX, posY+yg, deltaUpdater);
		new ForceFieldPart(posX+xg, posY, deltaUpdater);
		new ForceFieldPart(posX+xg, posY+yg, deltaUpdater);
		*/
	}
}
