package entities;

/**
 * MoveableDecor is used for ornamental entities like backgrounds, 
 * graphical effects and randomly flying objects.
 * */
public class MoveableDecor extends Moveable {
	protected int defaultLayer = 60;
	public MoveableDecor(double newPosX, double newPosY) {
		super(newPosX, newPosY);
	}
}
