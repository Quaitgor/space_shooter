package movementV2;

import entities.Entity;
import entities.MoveableDecor;
import graphics.LayerData2;



public class InfiniteScroll extends Move{
	private LayerData2 clone;
	private LayerData2 mainTexture;
	private double speedX;
	
	public InfiniteScroll(Entity getOwner, double moveX, double moveY) {
		super(getOwner);
		speedX = moveX;
		
		clone = new LayerData2(owner, owner.LayerDatas.get(0).texturepath, 1, 1);
		clone.spriteDisplayX = owner.LayerDatas.get(0).spriteDisplayX;
		clone.spriteDisplayY = owner.LayerDatas.get(0).spriteDisplayY;
		clone.pos = new double[]{owner.LayerDatas.get(0).spriteDisplayX,0};
		clone.layer = owner.LayerDatas.get(0).layer;
		owner.addNewLayer(clone);
	}
	protected void calculateMove(){
	}
	protected void makeMove(){

		if(((MoveableDecor)owner).mainTexture.pos[0] < 0-((MoveableDecor)owner).mainTexture.spriteDisplayX){
			((MoveableDecor)owner).mainTexture.pos[0] = ((MoveableDecor)owner).mainTexture.spriteDisplayX;
		}else{
			((MoveableDecor)owner).mainTexture.pos[0] -= speedX;
		}
		if(clone.pos[0] < 0-clone.spriteDisplayX){
			clone.pos[0] = clone.spriteDisplayX;
		}else{
			clone.pos[0] -= speedX;
		}
	}
}
