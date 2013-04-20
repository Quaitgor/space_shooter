package entities;

import movementV2.Move;
import graphics.LayerData2;

/**
 * HUD is a Head-up-Display, a Display for showing information to the User
 * HUD sets up the graphical elements of the HUD, the HUD itself is controled by the Player Object (and its PlayerMove)
 * */
public class HUD extends Entity{
	private LayerData2 int_BG;
	public LayerData2 int_SBar;
	public LayerData2 int_BBar;
	public LayerData2 int_BBarGlow;
	public LayerData2 int_Dot1;
	public LayerData2 int_Dot2;
	private Move owner;
	public float[] defaultColor;
	public double hideBBarValue = -190;
	private int player = 1;
	
	public HUD(double newPosX, double newPosY, Move owner, int player) {
		super(newPosX, newPosY);
		this.player = player;
		defaultColor = new float[]{0.5f, 1.0f, 0.5f, 1.0f};
		int scale = 2;
		this.owner = owner;
		int_BG = new LayerData2(this, "interface/int_BG", 1, 1);
		int_BG.spriteDisplayX *= scale;
		int_BG.spriteDisplayY *= scale;
		int_BG.layer = 9;

		int_BBarGlow = new LayerData2(this, "interface/int_BBarGlow", 1, 1);
		int_BBarGlow.spriteDisplayX *= scale;
		int_BBarGlow.spriteDisplayY *= scale;
		int_BBarGlow.layer = 8;
		int_BBarGlow.color[3] = 0.0f;
		int_BBarGlow.disableDepth = true;
		
		int_SBar = new LayerData2(this, "interface/int_SBar", 1, 1);
		int_SBar.spriteDisplayX *= scale;
		int_SBar.spriteDisplayY *= scale;
		int_SBar.layer = 10;
		 
		int_BBar = new LayerData2(this, "interface/int_BBar", 1, 1);
		int_BBar.spriteDisplayX *= scale;
		int_BBar.spriteDisplayY *= scale;
		int_BBar.layer = 10;
		int_BBar.color = defaultColor;
		int_BBar.pos[0] = hideBBarValue;
		
		int_BBar.color = new float[]{0.5f, 0.5f, 0.5f, 1.0f};
		int_Dot1 = new LayerData2(this, "interface/int_Dot", 1, 1);
		int_Dot1.spriteDisplayX *= scale;
		int_Dot1.spriteDisplayY *= scale;
		int_Dot1.layer = 8;
		int_Dot1.pos = new double[]{ 38, 10};
		int_Dot2 = new LayerData2(this, "interface/int_Dot", 1, 1);
		int_Dot2.spriteDisplayX *= scale;
		int_Dot2.spriteDisplayY *= scale;
		int_Dot2.layer = 8;
		int_Dot2.pos = new double[]{ 54, 10};

		addNewLayer(int_BG);
		addNewLayer(int_BBar);
		addNewLayer(int_BBarGlow);
		addNewLayer(int_SBar);
		addNewLayer(int_Dot1);
		addNewLayer(int_Dot2);
	}

	

}
