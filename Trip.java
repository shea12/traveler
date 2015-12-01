///////////////////////////////////////////////////////////////////////////////
// Main Class File:  AsTheCrowFlies.java
// File:             Trip.java
// 
///////////////////////////////////////////////////////////////////////////////


	/**
	 * Trip class. Creates an instance of Trip object, includes a 
	 * toString method for writing object information to user or file.
	 * Also includes a haversine method to compute distance.
	 *
	 * <p>Bugs: None
	 *
	 * @author Caroline Shea & Shannon Olson
	 */
	

public class Trip {
	
	private String tCity;
	private double lat;
	private double lon;

	
	/**
	 * Constructor for the trip object. 
	 *
	 * @param (String tCity) (City name, all upper case)
	 * @param (double lat) (latitude value)
	 * @param (double lon) (longitude value)
	 */
	
	public Trip (String tCity, double lat, double lon){
		this.tCity = tCity.toUpperCase();
		this.lat = lat;
		this.lon = lon;
	}

	String getTCity (){
		return tCity;
	}

	double getLat (){
		return lat;
	}

	double getLon (){
		return lon;
	}

	
	
	/**
	 * Haversine formulatC for finding Great Circle distance between two points.
	 * Each set of points are coordinates, changed to radians, and distance
	 * between them is computed with heavy use of Math.methods. 
	 *
	 * @param (double latA) (latitude value for first location)
	 * @param (double lonA) (longitude value for first location)
	 * @param (double latB) (latitude value for second location)
	 * @param (double lonB) (longitude value for second location)
	 * @return (double, distance in meters between the two points)
	 */
	public static final double RADIUS = 6371000; // In meters
	public static double getDist (double latA, double lonA, 
			double latB, double lonB){
		double dist = 0;
		double latChange = Math.toRadians(latB - latA);
		double lonChange = Math.toRadians(lonB - lonA);
		latA = Math.toRadians(latA);
		latB = Math.toRadians(latB);
		double firstMath = Math.sin(latChange / 2) * Math.sin(latChange / 2) + 
				Math.sin(lonChange / 2) * Math.sin(lonChange / 2) 
				* Math.cos(latA) * Math.cos(latB);
		double secondMath = 2 * Math.asin(Math.sqrt(firstMath));
		dist =  RADIUS * secondMath;
		return dist;
	}

	

	/**
	 * toString method for the Trip object
	 * 
	 * @param (String cityA) (first city)
	 * @param (String cityB) (second city)
	 * @param (int dist) (distance between the cities in meters)
	 * @param (int miles) (distance between the cities in miles)
	 * @return (String formatted w/ output according to program specifications)
	 */
	public static String toStrings (String cityA, String cityB, 
			int dist, int miles){
		String pTrip = "";
		pTrip = ((cityA+" to "+cityB+" as the crow flies is about "+dist+
				" meters (~ "+miles+" miles)"));
		return pTrip;
	}
}
