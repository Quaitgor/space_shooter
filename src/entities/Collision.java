package entities;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import ent_c.Powerup;
import javax.imageio.ImageIO;

/**
 * This Class checks every frame of the game if objects are colliding and
 * acts depening on the objects.
 * The collision is checked in 3 steps:
 * First: it checks if the objects themselve intersect
 * Second: it checks if the hitboxes ( a smaller rectangle than the objects
 * themselve) collide
 * Third: it checks if the pixels of the objects collide
 * 
 * With the 3 step testing the computing power intense pixel check is delayed
 * until the objects are closer together.
 * */
public class Collision {
	
	/**
	 * 
	 * */
	public void intersects(Moveable friend, Moveable enemy) {
		double twidth = friend.mainTexture.spriteDisplayX;
		double theight = friend.mainTexture.spriteDisplayY;
		double owidth = enemy.mainTexture.spriteDisplayX;
		double oheight = enemy.mainTexture.spriteDisplayY;
		
		if(friend.posX+twidth/2 > enemy.posX - owidth/2 
				&& friend.posX-twidth/2 < enemy.posX + owidth/2 
				&& friend.posY+theight/2 > enemy.posY - oheight/2 
				&& friend.posY-theight/2 < enemy.posY + oheight/2){
			if( friend.hitbox.width > enemy.hitbox.x 
					&& friend.hitbox.x < enemy.hitbox.width 
					&& friend.hitbox.height > enemy.hitbox.y 
					&& friend.hitbox.y < enemy.hitbox.height){
				Rectangle crasher1 = new Rectangle();
				Rectangle crasher2 = new Rectangle();
				crasher1.setRect(
					friend.posX-twidth/2,
					friend.posY-theight/2,
					friend.posX+twidth/2,
					friend.posY+theight/2
				);
				crasher2.setRect(
					enemy.posX - owidth/2,
					enemy.posY - oheight/2,
					enemy.posX + owidth/2,
					enemy.posY + oheight/2
				);
				File file1 = new File("res/sprites/"+friend.mainTexture.collisionTexPath+".png");
				File file2 = new File("res/sprites/"+enemy.mainTexture.collisionTexPath+".png");
				
				boolean isHit = false;
				
				if(!enemy.alreadyHit.contains(friend)){
					if(friend.dontPixelCheck || enemy.dontPixelCheck){
						isHit = true;
					}
					else if (isPixelCollide(crasher1, file1, crasher2, file2)){
						enemy.alreadyHit.add(friend);
						if(!enemy.isPowerup){
							isHit = true;
						}else{
							if(!friend.isProjectile){
								((Powerup)enemy).pickedUp(friend);
							}
						}
					}else{
						System.out.println("already hit");
					}
				}
				
				if(isHit && enemy.isAlive && friend.isAlive){
					enemy.getDamage(friend.damage, friend);
        			friend.getDamage(enemy.damage, enemy);
				}
			}
		}
	}
	
	/**
	 * Method for pixelchecking 2 graphics for collision.
	 * using 2 retangles to reduce the nessesary computing power and 2 files
	 * this method checks if 2 non-alpha pixels are colliding.
	 * */
	public boolean isPixelCollide(Rectangle crasher1, File file1, Rectangle crasher2, File file2) {
		// initialization
		BufferedImage image1 = null, image2 = null;
		try {
			image1 = ImageIO.read(file1);
			image2 = ImageIO.read(file2);
		} catch (IOException e) {
			System.out.println("error");
		}
		double x1 = crasher1.x;
		double x2 = crasher2.x;
		double y1 = crasher1.y;
		double y2 = crasher2.y;
		double width1 = x1 + image1.getWidth() -1,
				height1 = y1 + image1.getHeight() -1,
				width2 = x2 + image2.getWidth() -1,
				height2 = y2 + image2.getHeight() -1;
		int xstart = (int) Math.max(x1, x2),
				ystart = (int) Math.max(y1, y2),
				xend   = (int) Math.min(width1, width2),
				yend   = (int) Math.min(height1, height2);
		// intersection rectangle
		int totx = Math.abs(xend - xstart);
		int toty = Math.abs(yend - ystart);
		// loop through each pixel in the intersection and check
		// if both are alpha, if not => collision

		boolean check = false;
		for (int y=1; y < toty-1;y++){
			int ny = Math.abs(ystart - (int) y1) + y;
			int ny1 = Math.abs(ystart - (int) y2) + y;
			for (int x=1;x < totx-1;x++) {
				int nx = Math.abs(xstart - (int) x1) + x;
				int nx1 = Math.abs(xstart - (int) x2) + x;
				Color pixelColour1 = new Color(image1.getRGB(nx, ny), true);
				int color1 = pixelColour1.getAlpha();
				Color pixelColour2 = new Color(image2.getRGB(nx1, ny1), true);
				int color2 = pixelColour2.getAlpha();
				if((color1 != 0) && color2 != 0){
					check = true;
				}
				if(check)continue;
			}	
		}
		return check;
	}
}
