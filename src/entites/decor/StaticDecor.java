package entites.decor;

import observer.Subject;
import entities.Entity;

public class StaticDecor extends Entity {
	int defaultLayer = 62;
	public StaticDecor(double newPosX, double newPosY, Subject getdeltaUpdater) {
		super(newPosX, newPosY, getdeltaUpdater);
		// TODO Auto-generated constructor stub
	}

}
