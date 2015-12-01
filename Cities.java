///////////////////////////////////////////////////////////////////////////////
// Main Class File:  AsTheCrowFlies.java
// File:             Cities.java
//
///////////////////////////////////////////////////////////////////////////////



	/**
	 * Cities class. Creates an instance of the Cities object, includes a 
	 * toString method for writing object information to user or file.
	 *
	 * <p>Bugs: None
	 *
	 * @author Caroline Shea & Shannon Olson
	 */


public class Cities {

	private String state;
	private String city;
	private double lat;
	private double lon;
	
	
	/**
	 * Constructor for the city object. 
	 *
	 * @param (String state) (state name, all upper case)
	 * @param (String city) (city name, all upper case)
	 * @param (double lat) (latitude value)
	 * @param (double lon) (longitude value)
	 */
	public Cities (String state, String city, double lat, double lon){
		this.state = state.toUpperCase();
		this.city = city.toUpperCase();
		this.lat = lat;
		this.lon = lon;
	}
	
	
	String getState(){
		return state.toUpperCase();
	}
	String getCity(){
		return city.toUpperCase();
	}
	double getLat(){
		return lat;
	}
	double getLon(){
		return lon;
	}
	
	/**
	 * toString method for the City object
	 *
	 * @return (String consisting of STATE,CITY,LAT,LON)
	 */
	@Override
	public String toString (){
		String printCity = state.toUpperCase()+","+city.toUpperCase()+
				","+lat+","+lon;
		return printCity;
	}
}
