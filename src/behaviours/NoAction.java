package behaviours;

import entities.Moveable;

/**
 * Deos nothing.
 * @author philipp
 *
 */
public class NoAction extends Behave{

	public NoAction(Moveable getOwner) {
		super(getOwner);
	}

	protected void checkMind() {
	}

}
