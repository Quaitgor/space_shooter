package behaviours;

import entities.Moveable;

/**
 * Deos nothing.
 * Exists for testing purposes.
 */
public class NoAction extends Behave{

	public NoAction(Moveable getOwner) {
		super(getOwner);
	}

	protected void checkMind() {
	}

}
