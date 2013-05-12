package movementV2;

import entities.Entity;

/**
 * imitates a sinus-wave stretched with the specified multiplicator.
 */
public class SinusWave extends Move {
	private double distanceToOrigin;
	private double sinusMultiplicator;
	private double xAxis;
	public SinusWave(Entity getOwner, double speed, double sinusMultiplicator) {
		super(getOwner);
		xAxis = owner.posY;
		nposX = speed;
		this.sinusMultiplicator = sinusMultiplicator;
	}
	
	/**
	 * Not needed in this movement.
	 * */
	public void calculateMove() {

	}
	
	/**
	 * @see Move.makeMove().
	 * */
	public void makeMove() {
		distanceToOrigin += nposX;
		owner.posX += nposX;
		owner.posY =  xAxis+sinusMultiplicator*Math.sin(distanceToOrigin/50);
	}

}
