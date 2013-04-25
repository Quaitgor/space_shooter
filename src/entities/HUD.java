package entities;

import ent_c.Player;
import movementV2.Move;
import movementV2.PlayerMove;
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
	private PlayerMove owner;
	public float[] defaultColor;
	public double hideBBarValue = -194;
	public double hideSBarValue = -106;
	public double shieldRechargeTime = 12000;
	public double shieldRechargeLeft = 0;
	public int checkShieldCharges = 0;
	
	private int player = 1;
	
	public HUD(double newPosX, double newPosY, Move owner, int player) {
		super(newPosX, newPosY);
		this.player = player;
		defaultColor = new float[]{0.5f, 1.0f, 0.5f, 1.0f};
		int scale = 2;
		this.owner = ((PlayerMove)owner);
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
		int_SBar.pos[0] = hideSBarValue;
		 
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
		int_Dot1.color = new float[]{1f, 1f, 1f, 1f};
		int_Dot2 = new LayerData2(this, "interface/int_Dot", 1, 1);
		int_Dot2.spriteDisplayX *= scale;
		int_Dot2.spriteDisplayY *= scale;
		int_Dot2.layer = 8;
		int_Dot2.pos = new double[]{ 54, 10};
		int_Dot2.color = new float[]{1f, 1f, 1f, 1f};

		addNewLayer(int_BG);
		addNewLayer(int_BBar);
		addNewLayer(int_BBarGlow);
		addNewLayer(int_SBar);
		addNewLayer(int_Dot1);
		addNewLayer(int_Dot2);
	}
	
	public void update(double delta){
		super.update(delta);
		check();
	}
	protected void check(){
		Player x = (Player) owner.owner;
		if (checkShieldCharges != x.shieldCharges){
			shieldRechargeLeft = 0;
			checkShieldCharges = x.shieldCharges;
			
			if (x.shieldCharges < 2){
				int_Dot2.color = new float[]{1.0f, 1.0f, 1.0f, 0.0f};
			}
			if (x.shieldCharges < 1){
				int_Dot1.color = new float[]{1.0f, 1.0f, 1.0f, 0.0f};
			}
		}
		
		/*
		 * Shield Recharge Bar Code
		 * */
		if (x.shieldCharges < 2){
			shieldRechargeLeft += delta;
			double percentt = 100/shieldRechargeTime;
			double percent = percentt * shieldRechargeLeft/100;
			System.out.println(percent);
			double smallBarPos = hideSBarValue -(hideSBarValue * percent);
			int_SBar.pos = new double[]{smallBarPos, 0.0};
			if(shieldRechargeLeft >= shieldRechargeTime){
				if(x.shieldCharges == 1){
					int_Dot2.color = new float[]{1f, 1f, 1f, 1f};
				}
				else if(x.shieldCharges == 0){
					int_Dot1.color = new float[]{1f, 1f, 1f, 1f};
				}
				x.shieldCharges += 1;
				shieldRechargeLeft = 0;
			}
		}else{
			if(int_SBar.pos[0] > hideSBarValue) int_SBar.pos[0] += hideSBarValue/20;			
		}
		/*
		 * Charged Shot Bar Code
		 * */
		if(owner.chargedelta <= 2000 && owner.chargedelta != 0){

			double percentage = 100/owner.chargeTime;
			double percent = percentage * owner.chargedelta/100;
			double bigBarPos = hideBBarValue -(hideBBarValue * percent);
			if (percent*100 >= 60) {
    			float[] color = int_BBar.color;
    			if (color[0] < 1.0f) color[0] += 0.01f;
    			if (color[1] > 0.5f) color[1] -= 0.01f;
			}
			if(percent*100 >= 95 && percent*100 < 100){
				if(int_BBarGlow.color[3] < 1.0f) int_BBarGlow.color[3] += 0.10f;
			}
			if(percent*100 >= 100){
				if(int_BBarGlow.color[3] > 0.0f) int_BBarGlow.color[3] -= 0.01f;
			}
			int_BBar.pos = new double[]{bigBarPos, 0.0};
		}else{
			System.arraycopy(defaultColor, 0,int_BBar.color, 0, defaultColor.length );
			int_BBar.pos = new double[]{hideBBarValue, 0.0};
			int_BBarGlow.color[3] = 0.0f;
		
		}
	}
}
