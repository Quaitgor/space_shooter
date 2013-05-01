package movementV2;

import entities.Entity;

/**
 * Imitates a sin-wave stretched with the specified multiplicator.
 * @author philipp
 *
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
	public void calculateMove() {

	}
	public void makeMove() {
		distanceToOrigin += nposX;
		owner.posX += nposX;
		owner.posY =  xAxis+sinusMultiplicator*Math.sin(distanceToOrigin/50);
	}

}
