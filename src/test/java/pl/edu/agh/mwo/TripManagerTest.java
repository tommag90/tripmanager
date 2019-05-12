package pl.edu.agh.mwo;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TripManagerTest {

	TripManager tripManager;
	Trip trip;
	
	@Before
	public void setUp() {
		tripManager = new TripManager();
		trip = new Trip("nazwa", "opis");
	}
	
	@Test
	public void testAdd() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
	}

	@Test(expected = TripAlreadyExistsException.class)
	public void testAddTripTwice() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.add(trip);
	}

	@Test
	public void testRemoveTrip() throws Exception {
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.remove(trip.getName());
		assertEquals(0, tripManager.getTrips().size());
		//fail("chcemy zespuc");
		}
	
	@Test
	public void testFindTripsEmptyKeyword() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip");
		tripManager.add(trip1);
		tripManager.findTrips("");
		assertEquals(2, tripManager.getTrips().size());
	}
	
	@Test
	public void testFindTripsMatchingName() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip");
		tripManager.add(trip1);
		List<Trip> results = tripManager.findTrips("second");
		assertEquals(trip1, results.get(0));
	    assertEquals(1, results.size());
	}
	
	@Test
	public void testFindTripsMatchingDesc() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip");
		tripManager.add(trip1);
		List<Trip> results = tripManager.findTrips("trip");
		assertEquals(trip1, results.get(0));
	    assertEquals(1, results.size());
	}
	
	@Test
	public void testFindTripsMatchingPhoto() throws TripAlreadyExistsException {
		Photo photo = new Photo("first photo");
		Photo photo1 = new Photo("second photo");
		Photo photo2 = new Photo("third photo");
		Photo photo3 = new Photo("fourth photo");
		
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip");
		tripManager.add(trip1);
		trip.addPhoto(photo);
		trip.addPhoto(photo1);
		trip1.addPhoto(photo2);
		trip1.addPhoto(photo3);
		
		List<Trip> results = tripManager.findTrips("second photo");
		assertEquals(trip, results.get(0));
	    assertEquals(1, results.size());
	}


}
