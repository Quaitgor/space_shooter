package observer;

/**
 * Implementing classes receive updates on the delta value.
 * 
 * Required to fulfill the Observer-pattern
 * */
public interface Observer {

	/**
	 * Used by DeltaUpdater to send each registered object the new delta-value.
	 * */
	public void update(double delta);
}