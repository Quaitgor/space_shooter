package entities;

import observer.Subject;

/**
 * Used for Visual effects like backgrounds which don't move.
 * */
public class StaticDecor extends Entity {
	protected int defaultLayer = 62;
	public StaticDecor(double newPosX, double newPosY) {
		super(newPosX, newPosY);
		// TODO Auto-generated constructor stub
	}

}
