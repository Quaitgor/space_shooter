package behaviours;

import entities.Moveable;

/**
 * Does nothing. Only to use for HasMind-Objects if the owner does not need a
 * mind, Mindless-Class should be initialized.
 */
public class NoAction extends Behave{

	public NoAction(Moveable getOwner) {
		super(getOwner);
	}

	protected void checkMind() {
	}

}
