package entities;

import observer.Subject;

/**
 * MoveableDecor extends Moveable for use as Decorations (Backgrounds, graphic effects, HUD, random flying objects)
 * the Decor extending MoveableDecor can move around with the movement Strategy Pattern
 * */
public class MoveableDecor extends Moveable {
	protected int defaultLayer = 60;
	public MoveableDecor(double newPosX, double newPosY, Subject getdeltaUpdater) {
		super(newPosX, newPosY, getdeltaUpdater);
	}
}
