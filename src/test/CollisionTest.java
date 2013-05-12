package test;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.Vector;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import observer.DeltaUpdater;
import ent_c.Enemy1;
import ent_c.Player;
import entities.Collision;
import entities.Moveable;
import graphics.*;
import junit.framework.TestCase;

public class CollisionTest extends TestCase{
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
	public void testIntersects(){

		GS.enemys = new Vector<Moveable>();
		GS.friendlys = new Vector<Moveable>();;
		initGL();
		GS.deltaUpdater = new DeltaUpdater();
		Collision c = new Collision();
		Enemy1 e = new Enemy1(20, 20);
		//Enemy2 e2 = new Enemy2(20, 20);
		Player p = new Player(20,20);
		int Playerhealth = p.health;
		int Enemyhealth = e.health;
		e.update(1);
		p.update(1);
		c.intersects(e, p);
		assertTrue(e.health < Enemyhealth);
		assertTrue(p.health < Playerhealth);
	}
}