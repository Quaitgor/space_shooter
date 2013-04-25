package graphics;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.Vector;

import observer.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import entites.decor.Hit;
import entities.Collision;
import entities.Entity;
import entities.Moveable;
import entities.Offensive;
import factory.*;

/**
 * GS is the Graphics-Synchronizer, GS controls the speed of the game, and keeps every element in sync
 * GS creates the Display and sets basic options for the Graphics and its resolution and speed
 * */
public class GS {	
    static boolean isRunning = true;
    static long lastFrame;
	public static DeltaUpdater deltaUpdater;
	public static double delta = 0;
	public static Vector<Moveable> enemys;
	public static Vector<Moveable> friendlys;
	public static Entity player1;
	public static Entity player2;
	protected Collision colchecker = new Collision();
	int fps;
	long lastFPS;
	
	public GS(DeltaUpdater getdeltaUpdater) {
		deltaUpdater = getdeltaUpdater;
		enemys = new Vector<Moveable>();
		friendlys = new Vector<Moveable>();
		getDelta();
	}
    
	/**
	 * start creates the display with the set options and keeps it open until a close is requested,
	 * every Frame the Loop inside the method updates the deltaUpdater Observer, which in turn updates every graphical element with the Observer Pattern
	 * */
	public void start() {
		// Run Graphics window until close request (X or Alt-F4), while running send out Delta to DeltaObserver
		initGL(1280,768);
		
        while (isRunning) {
            if (Display.isCloseRequested()) {
           		isRunning = false;
            }
            render();
            checkCollision();
            updateInfo();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
    }
	
	

	protected void checkCollision(){
    		for (Moveable friendlys: GS.friendlys){
    			Moveable friend = (Moveable) friendlys;

        		for (Moveable enemys: GS.enemys){
        			Moveable enemy= (Moveable) enemys;
        			colchecker.intersects(friend,enemy);
        		}
    		}
			/*
    		if(crashed){
				new Hit(posX, posY);
				crashed = false;
			}
			*/
			//if(health <= 0) this.unsubscribe();
	}
	
	
	
	/**
	 * updateInfo() displays information in the Titlebar of the Window
	 * */
	public void updateInfo() {
		/* not working ??
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
		*/
		Display.setTitle("ObsCount: " + deltaUpdater.getObserverNumber());
	}
	
	/**
	 * render() calulates readys the screen for the next frame and calulates the timediffrence since the last calulation (delta) and updates the deltaUpdater with this delta
	 * */
	private void render(){
    	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        getDelta();
        GS.deltaUpdater.setDelta(delta);
	}
	
	/**
	 * initGL() sets GL options and prepares the screen for starting the game 
	 * */
	private void initGL(int width, int height){
        try {
			Display.setDisplayMode(new DisplayMode(width,height));
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
        GL11.glDepthFunc(GL11.GL_ADD); ////// 
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glViewport(0,0,width,height);
        GL11.glOrtho(0,width,height,0,0,128);
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
        //GL11.glLoadIdentity();
        
        // Create some Objects for testing
		/*
		fe.create("Moon", 300.0, 400.0);
		fe.create("AstroidbeltL1", 512.0, 400.0);
		fe.create("AstroidbeltL2", 512.0, 460.0);
		fe.create("AstroidbeltL3", 512.0, 620.0);
		fe.create("Stars", 640.0, 384.0);
		fe.create("Electro", 300.0, 200.0);
		*/
	}
	
	/**
	 * getDelta() calulates the time since last calulation (delta) and returns it
	 * */
    private static double getDelta() {
        long currentTime = getTime();
        double delta1 = (double) (currentTime - lastFrame);
        lastFrame = getTime();
        delta = delta1;
        return delta;
    }
    
    /**
     * getTime() grabs the system Time and returns it
     * */
    private static long getTime() {
    	// get Systemtime
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
