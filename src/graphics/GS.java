package graphics;
import observer.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import player.Player;

import factory.EnemyFactory;

public class GS {
	
    static boolean isRunning = true;
    static long lastFrame;
	private static DeltaUpdater deltaUpdater;
	public static moveables.Player player;
	
	public GS(DeltaUpdater getdeltaUpdater) {
		GS.deltaUpdater = getdeltaUpdater;
		getDelta();
	}
    
	public void start() {
		// Run Graphics window until close request (X or Alt-F4), while running send out Delta to DeltaObserver
		initGL(1280,768);
		
        while (isRunning) {
            if (Display.isCloseRequested()) {
           		isRunning = false;
            }
            render();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
    }
	private void render(){
    	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GS.deltaUpdater.setDelta(getDelta());
	}
	private void initGL(int width, int height){
    	//Settings for Graphics, Ortho, Alpha, Color, Depth etc
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
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glViewport(0,0,width,height); 
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glOrtho(0,width,height,0,0,128);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0);
        GL11.glDepthFunc(GL11.GL_ADD);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glLoadIdentity();
        
        
        // Create some Objects for testing
		EnemyFactory ef = new EnemyFactory(deltaUpdater);
		ef.create("Standard-Gegner");
		ef.create("Schwacher-Gegner");
		//player = new Player(null, deltaUpdater);
		ef.create("Testenemy");
		ef.create("Testenemy2");
		ef.create("Player");
		ef.create("Moon");
		ef.create("Background1");
		ef.create("Background2");
	}
    private static double getDelta() {
    	// Calculate Delta time (time since last calculation)
        long currentTime = getTime();
        double delta = (double) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
    
    private static long getTime() {
    	// get Systemtime
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
