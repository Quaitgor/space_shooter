package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import weapons.Weapon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import entities.Entity;
//public class EnemyFactory extends Factory{
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
	public static void create(String EnemyName, double posX, double posY){
			try {
				Class.forName("entities."+EnemyName).getConstructors()[0].newInstance(new double[]{posX,posY});
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
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				e.posX = posX;
//				e.posY = posY;
	}
}