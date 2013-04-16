package entities;

import observer.Subject;

public abstract class Offensive extends Moveable{

	public Offensive(double newPosX, double newPosY, Subject getdeltaUpdater) {
		super(newPosX, newPosY, getdeltaUpdater);
	}

}
