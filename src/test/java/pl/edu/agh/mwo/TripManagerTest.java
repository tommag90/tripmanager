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
		trip = new Trip("nazwa", "opis", 2000);
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
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		tripManager.findTrips("");
		assertEquals(2, tripManager.getTrips().size());
	}
	
	@Test
	public void testFindTripsMatchingName() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		List<Trip> results = tripManager.findTrips("second");
		assertEquals(trip1, results.get(0));
	    assertEquals(1, results.size());
	}
	
	@Test
	public void testFindTripsMatchingDesc() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
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
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		trip.addPhoto(photo);
		trip.addPhoto(photo1);
		trip1.addPhoto(photo2);
		trip1.addPhoto(photo3);
		
		List<Trip> results = tripManager.findTrips("second photo");
		assertEquals(trip, results.get(0));
	    assertEquals(1, results.size());
	}
	
	@Test
	public void testTripsNotFound() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		List<Trip> results = tripManager.findTrips("something other");
	    assertEquals(0, results.size());
	}
	
	@Test
	public void testFindTripsMultiple() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "nazwa", 1500);
		tripManager.add(trip1);
		Trip trip2 = new Trip("third", "trip", 2200);
		Photo photo = new Photo("nazwa");
		trip2.addPhoto(photo);
		tripManager.add(trip2);
		
		List<Trip> results = tripManager.findTrips("nazwa");
	    assertEquals(3, results.size());
	}
	
	@Test
	public void testFindTripsNoDuplicates() throws TripAlreadyExistsException {
		Trip trip1 = new Trip("nazwa", "nazwa", 1500);
		Photo photo = new Photo("nazwa");
		trip1.addPhoto(photo);
		tripManager.add(trip1);
		
		List<Trip> results = tripManager.findTrips("nazwa");
	    assertEquals(1, results.size());
	}
	
	@Test
	public void testFindByPriceBetween() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		
		List<Trip> results = tripManager.findTripsByMaxPrice(1800);
		assertEquals(trip1, results.get(0));
	    assertEquals(1, results.size());
	}
	
	@Test
	public void testFindByPriceEquals() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		
		List<Trip> results = tripManager.findTripsByMaxPrice(1500);
		assertEquals(trip1, results.get(0));
	    assertEquals(1, results.size());
	}
	
	@Test
	public void testFindByPriceTooLow() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		
		List<Trip> results = tripManager.findTripsByMaxPrice(200);
	    assertEquals(0, results.size());
	}
	
	@Test
	public void testFindByBoth() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		Trip trip2 = new Trip("third", "trip", 1300);
		tripManager.add(trip2);
		
		List<Trip> results = tripManager.findTripsByMaxPriceAndKeyword("trip", 1400);
		assertEquals(trip2, results.get(0));
	    assertEquals(1, results.size());
	}
	
	@Test
	public void testFindByBothTooLowPrice() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		Trip trip2 = new Trip("third", "trip", 1300);
		tripManager.add(trip2);
		
		List<Trip> results = tripManager.findTripsByMaxPriceAndKeyword("trip", 1000);
	    assertEquals(0, results.size());
	}
	
	@Test
	public void testFindByBothNoKeyword() throws TripAlreadyExistsException {
		tripManager.add(trip);
		Trip trip1 = new Trip("second", "trip", 1500);
		tripManager.add(trip1);
		Trip trip2 = new Trip("third", "trip", 1300);
		tripManager.add(trip2);
		
		List<Trip> results = tripManager.findTripsByMaxPriceAndKeyword("fifth", 9000);
	    assertEquals(0, results.size());
	}

}
