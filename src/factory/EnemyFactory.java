package factory;

import java.lang.reflect.InvocationTargetException;
/**
 * Creates objects from strings.
 */
public class EnemyFactory{
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
			e.getCause().printStackTrace();
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