package graphics;

import moveables.Movable;

import org.newdawn.slick.opengl.Texture;

/**
 * @author  vmadmin
 */
public class LayerData {
	/**
	 * @uml.property  name="owner"
	 * @uml.associationEnd  
	 */
	public Movable owner;
	public double[] pos;
	public double[] size;
	public double rotation;
	public int layer;
	public Texture tex;
	public float[] color;
	public float[] texCords;
	public boolean enableDepth;
	public int numberSpritesX;
	public int numberSpritesY;
	

	public LayerData(Movable owner, double[] pos, int layer, double rotation, double[] size, Texture tex, float[] color, float[] texCords, boolean disableDepth) {
		this.owner = owner;
		this.pos = pos;
		this.layer = layer;
		this.rotation = rotation;
		this.size = size;
		this.tex = tex;
		this.color = color;
		this.texCords = texCords;
		this.enableDepth = disableDepth;
	}
	public double getPosX(){
		return owner.posX + pos[0];
	}
	public double getPosY(){
		return owner.posY + pos[1];
	}
}
