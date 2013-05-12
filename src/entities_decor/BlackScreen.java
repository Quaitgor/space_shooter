package entities_decor;

import entities.*;
import graphics.LayerData2;

/**
 * Special version of the starts that blocks every visiual. used as a fade-in effect for the game.
 */
public class BlackScreen extends StaticDecor {
	private double countdown = 40;
	private double timer = 0;
	LayerData2 tex;
	private boolean reverse = false;
	
	public BlackScreen(double posX, double posY, boolean reverse) {
		super(posX, posY);
		tex = new LayerData2(this, "stars", 1, 1);
		tex.disableAnimation = true;
		tex.layer = 1;
		tex.spriteDisplayX = 1280;
		tex.spriteDisplayY = 768;
		this.reverse = reverse;
		if(this.reverse)tex.color[3] = 0.0f;
		addNewLayer(tex);
	}
	public void update(double delta){
		super.update(delta);
		timer -= delta;
		System.out.println(timer);
		if(timer < 0){
			if(!reverse)this.tex.color[3] -= 0.02f;
			if(reverse)this.tex.color[3] += 0.02f;
			timer += countdown;
		}
		if(this.tex.color[3] <= 0.0f)unsubscribe();
	}
}
