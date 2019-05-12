package pl.edu.agh.mwo;
import java.util.*;

public class TripManager {
	private HashMap<String,Trip> tripList;
	
	public TripManager() {
		tripList = new HashMap<String,Trip>();
	}
	
	
	public void add(Trip trip) throws TripAlreadyExistsException {
		if (tripList.get(trip.getName()) != null) {
			throw new TripAlreadyExistsException();
		}
		else {
			tripList.put(trip.getName(),trip);
		}
	}
	
	public HashMap<String,Trip> getTrips() {
		return tripList;
	}

	public void remove(String name) {
		tripList.remove(name);
	}
	
	public List<Trip> findTrips(String keyword) {
		List<Trip> tripsSearchResults = new LinkedList<>();
		String keywordLowerCase = keyword.toLowerCase().trim();
		
		for (String tripName : tripList.keySet()) {
			String tripNameLowerCase = tripName.toLowerCase().trim();
			String tripDescriptionLowerCase = tripList.get(tripName).getDescription()
					.toLowerCase().trim();
			
			if(keywordLowerCase == "") {
				tripsSearchResults.add(tripList.get(tripName));
			}
			if(tripNameLowerCase.contains(keyword)) {
				tripsSearchResults.add(tripList.get(tripName));
			}
			if(tripDescriptionLowerCase.contains(keywordLowerCase)) {
				tripsSearchResults.add(tripList.get(tripName));
			}
			
			LinkedList<Photo> photos = new LinkedList<>();
			photos.addAll(tripList.get(tripName).getPhotos());
			for (Photo photo : photos) {
				String photoCommentLowerCase = photo.getComment()
						.toLowerCase().trim();
				if(photoCommentLowerCase.contains(keywordLowerCase)) {
					tripsSearchResults.add(tripList.get(tripName));
				}
			}
		}
		return tripsSearchResults;
	}
	
}
