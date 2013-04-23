package observer;

/**
 * Observer is an interface that allows the objects implementing this class to get updates on the delta value via the observer pattern
 * */
public interface Observer {

	/**
	 * update() is the method that every registered Object needs to recieve the updated delta
	 * with this method the DeltaUpdater can send to each object the new value of delta
	 * */
	public void update(double delta);
}