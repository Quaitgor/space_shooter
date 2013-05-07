package test;

import observer.DeltaUpdater;
import observer.Observer;
import junit.framework.TestCase;

public class DeltaUpdaterTest extends TestCase{
	public void testDeltaUpdater(){
		DeltaUpdater deltaUpdater = new DeltaUpdater();
		class TestObserver implements Observer{
			public double deltaSumme = 0;
			public void update(double delta) {
				deltaSumme += delta;
			}
		}
		
		TestObserver to1 = new TestObserver();
		TestObserver to2 = new TestObserver();
		deltaUpdater.register(to1);
		deltaUpdater.register(to2);
		deltaUpdater.setDelta(10.0);
		assertTrue(to1.deltaSumme == 10.0);
		assertTrue(to2.deltaSumme == 10.0);
		deltaUpdater.setDelta(20.0);
		assertTrue(to1.deltaSumme == 30.0);
		assertTrue(to2.deltaSumme == 30.0);
		assertTrue(deltaUpdater.getObserverNumber() == 2);
		deltaUpdater.unregister(to1);
		assertTrue(deltaUpdater.getObserverNumber() == 1);
		deltaUpdater.clearObserver();
		assertTrue(deltaUpdater.getObserverNumber() == 0);
	}
}
