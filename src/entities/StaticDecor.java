package entities;

import observer.Subject;

/**
 * StaticDecor extends Entity for the use with static Decorations (not moving elements on the screen like a background)
 * */
public class StaticDecor extends Entity {
	protected int defaultLayer = 62;
	public StaticDecor(double newPosX, double newPosY) {
		super(newPosX, newPosY);
		// TODO Auto-generated constructor stub
	}

}
