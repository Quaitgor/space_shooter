package entities;

public abstract class Mindless extends Offensive{
	protected int defaultLayer = 40;
	
	public Mindless(double newPosX, double newPosY) {
		super(newPosX, newPosY);
	}
}
