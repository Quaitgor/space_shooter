package factory;

/**
 * Type into which the Json-Array-Elements are stored.
 * Only the Spawner class uses it so therefore it should be an class-internal class.
 * But because the Gson-Parser cannot handle these types it needs to be a single
 * public class.
 */
public class Spawn{
	public String Moveable;
	public double x;
	public double y;
}
