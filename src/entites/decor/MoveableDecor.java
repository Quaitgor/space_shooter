package entites.decor;

import observer.Subject;
import entities.Moveable;

public class MoveableDecor extends Moveable {

	public MoveableDecor(double newPosX, double newPosY, Subject getdeltaUpdater) {
		super(newPosX, newPosY, getdeltaUpdater);
	}

}
