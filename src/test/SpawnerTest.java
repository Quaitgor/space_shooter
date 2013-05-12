package test;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import observer.DeltaUpdater;
import observer.Observer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import entities.Entity;
import entities.Moveable;
import factory.Spawn;
import factory.Spawner;
import graphics.GS;
import junit.framework.TestCase;

public class SpawnerTest extends TestCase{
	private void initGL(){
        try {
			Display.setDisplayMode(new DisplayMode(GS.FRAMEWIDTH,GS.FRAMEHEIGHT));
			Display.setFullscreen(true);
			Display.create();
			Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
		GL11.glEnable(GL11.GL_TEXTURE_2D);  
		//GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1.0f);
        //GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glDepthFunc(GL11.GL_ADD); //Wenn nicht auskommentiert führt es zu Exception
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glViewport(0,0,GS.FRAMEWIDTH,GS.FRAMEHEIGHT);
        GL11.glOrtho(0,GS.FRAMEWIDTH,GS.FRAMEHEIGHT,0,0,128);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glBlendEquation( BLENDING_EQUATIONS[blendingEquationIndex]);
		GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0);
        GL11.glCullFace(GL11.GL_BACK);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        GL11.glLoadIdentity();
	}
	public void testSpawning(){

		GS.enemys = new Vector<Moveable>();
		GS.friendlys = new Vector<Moveable>();;
		initGL();
		GS.deltaUpdater = new DeltaUpdater();
		Spawner s = new Spawner("testlevel", GS.deltaUpdater);
		s.update(400);
		Vector<Observer> observers = null;
		try {
			Field observerField = DeltaUpdater.class
					.getDeclaredField("observers");
			observerField.setAccessible(true);
			observers = (Vector<Observer>)observerField.get(GS.deltaUpdater);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson g = new Gson();
		HashMap<BigDecimal,Spawn[]> LevelMap = null;
		try {
			LevelMap = g.fromJson(new FileReader("json/level/testlevel.json")
				, (new TypeToken<HashMap<BigDecimal,Spawn[]>>(){}).getType());
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Spawn[] SpawnArray = LevelMap.get(LevelMap.keySet().toArray()[0]);
		assertTrue(observers.size() == SpawnArray.length+1);
				//+1 weil Spawner selbst registriert ist
		for(int i=0;i<observers.size()-1;++i){
			assertTrue(((Entity)observers.get(i+1)).getClass().getName()
					.equals(SpawnArray[i].Moveable));
			assertTrue(((Entity)observers.get(i+1)).posX == SpawnArray[i].x);
			assertTrue(((Entity)observers.get(i+1)).posY == SpawnArray[i].y);
			//assertTrue(((Entity)observers.get(i)).posX == al.get(i).x);
			
		}
	}
}
