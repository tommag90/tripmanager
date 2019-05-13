package pl.edu.agh.mwo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TripTest {

	@Test
	public void testConstructor() {
		Trip trip = new Trip("nazwa", "opis", 1500);
		assertEquals("nazwa", trip.getName());
		assertEquals("opis", trip.getDescription());
	}
	
	@Test
	public void testSetters() {
		Trip trip = new Trip("nazwa1", "opis1", 1600);
		trip.setName("changedName");
		trip.setDescription("changedDescription");
		assertEquals("changedName", trip.getName());
		assertEquals("changedDescription", trip.getDescription());
	}

}
