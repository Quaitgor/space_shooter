package test;

import ent_c.Enemy1;
import ent_c.Enemy2;
import entities.Collision;
import junit.framework.TestCase;

public class CollisionTest extends TestCase{
	public void testIntersects(){
		Collision c = new Collision();
		Enemy1 e1 = new Enemy1(20, 20);
		Enemy2 e2 = new Enemy2(20, 20);
		c.intersects(e1, e2);
		assertTrue(e1.health == 29);
		assertTrue(e2.health == 99);
	}
}
