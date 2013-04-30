package factory;

import java.lang.reflect.InvocationTargetException;
/**
 * Creates objects from strings.
 * @author philipp
 *
 */
public class EnemyFactory{
	/*
	public static Gson GsonParser = new Gson();
//	public static HashMap<String, Enemy> GeneratedEnemies = new HashMap<String, Enemy>();
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Weapon.class, new WeaponAdapter());
		GsonParser = builder.create();
	}
	*/
	/**
	 * Creates an object from a given string and places it in the right position.
	 * @param EnemyName
	 * @param posX
	 * @param posY
	 */
	public static void create(String EnemyName, double posX, double posY){
		try {
			Class.forName(EnemyName).getConstructor(
					new Class[]{Double.TYPE, Double.TYPE}).newInstance(
					new Object[]{posX,posY});
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}