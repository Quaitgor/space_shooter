package test;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;

import java.lang.reflect.Field;
import java.util.Vector;

import observer.DeltaUpdater;
import observer.Observer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import entities.Entity;
import entities.Moveable;
import factory.EnemyFactory;
import factory.Spawn;
import graphics.GS;
import junit.framework.TestCase;

/**
 * Tests the EnemyFactory class.
 */
public class EnemyFactoryTest extends TestCase{
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
	
	/**
	 * commands the EnemyFactory to create many different Moveables and checks
	 * whether the specified Moveables have been created and are in the right
	 * position.
	 */
	public void testCreate(){
		GS.enemys = new Vector<Moveable>();
		GS.friendlys = new Vector<Moveable>();;
		initGL();
		GS.deltaUpdater = new DeltaUpdater();

		class TestSpawn extends Spawn{
			TestSpawn(String Moveable, double x, double y){
				this.Moveable = Moveable;
				this.x = x;
				this.y = y;
			}
		}
		TestSpawn[] TestSpawnArray = {
			new TestSpawn("ent_c.Enemy1", 0.0, 0.0),
			new TestSpawn("ent_c.Enemy2", 40.0, 40.0),
			new TestSpawn("ent_c.Enemy3", 80.0, 80.0),
			new TestSpawn("ent_c.Boss1", 120.0, 120.0),
			new TestSpawn("ent_c.Player", 160.0, 160.0)
		};
		for(TestSpawn ts : TestSpawnArray){
			EnemyFactory.create(ts.Moveable, ts.x, ts.y);
		}
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
		assertTrue(observers.size() == TestSpawnArray.length);
		for(int i=0;i<observers.size();++i){
			assertTrue(((Entity)observers.get(i)).getClass().getName()
					.equals(TestSpawnArray[i].Moveable));
			assertTrue(((Entity)observers.get(i)).posX == TestSpawnArray[i].x);
			assertTrue(((Entity)observers.get(i)).posY == TestSpawnArray[i].y);
			//assertTrue(((Entity)observers.get(i)).posX == al.get(i).x);
			
		}
	}
}
