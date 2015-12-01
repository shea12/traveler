///////////////////////////////////////////////////////////////////////////////
// Title:            P4
// Files:            AsTheCrowFlies.java, Cities.java, Trip.java
// Semester:         CS302 Spring 2015
//
// Author:           Caroline Shea
// Email:            cashea2@wisc.edu
// CS Login:         caroline
// Lecturer's Name:  Deppeler
// Lab Section:      325
//
///////////////////////////////////////////////////////////////////////////////
//
// Pair Partner:     Shannon Olson
// Email:            smolson6@wisc.edu
// CS Login:         shannon
// Lecturer's Name:  Hobbes Legault
// Lab Section:      335
//
///////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.*;


/**
 * Program that allows user to find the distance between multiple cities.
 * Allows user to load a file, create a file, create cities, and create trips.
 *
 * <p>Bugs: None
 *
 * @author Caroline Shea & Shannon Olson
 */


public class AsTheCrowFlies {
	//cList is an array of object Cities that are available to the user
	//Cities are composed of state name, city name, latitude and longitude
	private static ArrayList<Cities> cList = new ArrayList<Cities>();

	//tList is an array of Trip objects.
	//Trip objects are composed of only the city name, latitude, and longitude
	private static ArrayList<Trip> tList = new ArrayList<Trip>();

	/**
	 * Main method: displays a menu of options for the user to choose from.
	 * Options include: loading a file, displaying cities, creating a trip,
	 * adding cities, and exiting the program. The program does not quit 
	 * until the user enters option 5.  
	 * 
	 * @return (void)
	 */
	
	public static void main (String args[]) throws IOException{
		
		//create a scanner object to get input from user (keyboard)
		Scanner scnr = new Scanner (System.in);

		//welcome message prints once only at the beginning 
		System.out.println("As The Crow Flies");
		System.out.println("");
		
		//while loop to keep the program running, displaying menu options	
		boolean running = true;
		while (running){
			System.out.println("1. Load available cities from a file");
			System.out.println("2. Display available cities");
			System.out.println("3. Create a trip");
			System.out.println("4. Add a city to available cities");
			System.out.println("5. Exit Program");
			System.out.print("Enter choice as integer [1-5]: ");

			//creates & initializes input String variable to hold user input
			String input = "";

			//while loop to check user input, allows infinite attempts
			boolean checking = true;
			while (checking){
				//gets user's menu choice
				input = scnr.nextLine();
				//checks if user input is not empty
				if (input.length() != 0){
					//changes first char of input to variable
					char firstChar = input.charAt(0);
					//checks if firstChar is 1,2,3,4, or 5
					if ((int)firstChar < 49 || (int)firstChar > 53){
						//prints try again statement if invalid
						System.out.println("Invalid input. Try again.");
						System.out.print("Enter choice as integer [1-5]: ");
					}
					//checks if input is greater than 1 char long
					else if (input.length() > 1){
						//prints try again statement if invalid
						System.out.println("Invalid input. Try again.");
						System.out.print("Enter choice as integer [1-5]: ");
					}
					//breaks out if input passes all tests
					else break;
				}
			}

			
//////////////////////////    MENU CHOICE 1     ///////////////////////////////		
			if (input.equals("1")){
				//int variable to keep track of the number of cities in file
				int count = 0;
				//prompt user for file name
				System.out.print("Enter the filename: ");
				//set user entered file name to local string variable
				String userFile = scnr.nextLine();
				//Create new File object with user entered filename
				File fileName = new File (userFile);
				//create new scanner for reading file contents
				Scanner fileScnr = null;
				//set up try catch to ensure that file exists and is readable
				try {
					//initialize fileScnr with file from user
					fileScnr = new Scanner(fileName);
					//loops as long as there is another line in the file
					while (fileScnr.hasNextLine()) {
						//two doubles for latitude and longitude values
						double lat = 0;
						double lon = 0;
						//string variable fileLine, next line in the scanner
						String fileLine = fileScnr.nextLine();
						//string array to hold the line, splits by commas
						String[] line = fileLine.split(",");
						//string for state name, first item in array
						String state = line[0];
						//string for city name, second item in array
						String city = line[1];
						
						try{
							//tries to parse the third item into a double
							lat = Double.parseDouble(line[2]);
							//if can be parsed, checks that it is within range
							if (lat >= -90 && lat <=90){
							}
							//if not within range, continues
							else {
								continue;
							}
						}
						//catches out of bounds if there is nothing at index 2
						catch (ArrayIndexOutOfBoundsException aob){
							continue;
						}
						//catches if item cannot be parsed into a double
						catch (NumberFormatException nfe){
							continue;
						}
						
						try {
							//tries to parse the fourth item into a double
							lon = Double.parseDouble(line[3]);
							//if can be parsed, checks that it is within range
							if (lon >= -180 && lon <=180){
							}
							else {
								//if not within range, continues
								continue;
							}
						}
						//catches out of bounds if there is nothing at index 3
						catch (ArrayIndexOutOfBoundsException aob){
							continue;
						}
						//catches if item cannot be parsed into a double
						catch (NumberFormatException nfe){
							continue;
						}
						//if passes all try/catches, creates new city object
						Cities newCity = new Cities (state, city, lat, lon);

						//adds newCity object to the arrayList cList
						cList.add(newCity);
						//increments the count of the number of cities
						count ++;
					}
					
					//catches file not found error
				} catch (FileNotFoundException e){
					System.out.println("Unable to read file");
				} finally {
					//closes scanner if the file was found and read
					if (fileScnr != null){
						fileScnr.close();
					}
					//prints the count of the cities to the user
					System.out.println(count+" cities added");
					System.out.println(" ");
				}	
			}


//////////////////////////    MENU CHOICE 2     ///////////////////////////////
			if (input.equals("2")){
				//for loop to display available cities
				if (cList.size() == 0){
					//if cList is empty, prints a blank line
					System.out.println("");
				}
				//if cList is not empty, loops and prints the contents
				else {
					for (int i = 0; i < cList.size(); i ++){
						System.out.println((cList.get(i)).toString());
					}
					System.out.println("");
				}
			}

			
//////////////////////////    MENU CHOICE 3     ///////////////////////////////
			if (input.equals("3")){
				//gettingCities used later for a while loop to get user input
				boolean gettingCities = true;
				//used to check if there are cities in the cList array
				boolean checkingAvailable = true;
				while (checkingAvailable){
					//immediately tells user how many cities are in the array
					System.out.println("There are "+cList.size()+" "
							+ "cities to choose from.");
					//if array is empty, tells user, kicks back to main menu
					if (cList.size() == 0){
						System.out.println("Must have at least 2 cities"
								+ " to choose from.");
						System.out.println("");
						gettingCities = false;
						break;
					}
					//if the cList array is populated but the tList array is 
					//empty, creates new trip. Goes directly to gettingCities.
					else if (cList.size() > 0 && tList.size() == 0){
						System.out.println("New trip created, needs at least"
								+ " two cities.");
						gettingCities = true;
						break;
					}
					//if cList and tList arrays are populated, asks if user 
					//wants to add to current trip or create a new trip
					else if (cList.size() > 0 && tList.size() > 0){
						System.out.print("Add to current trip (y/n)? ");
						//gets user input 
						String yesNo = scnr.nextLine();
						//user wants to add, sends to gettingCities.
						if (yesNo.equalsIgnoreCase("y")){
							gettingCities = true;
							break;
						}
						//user wants to make new trip
						else if (yesNo.equalsIgnoreCase("n")){
							System.out.println("New trip created, needs at "
									+ "least two cities.");
							//clears the previous trip, sends to gettingCities
							gettingCities = true;
							tList.clear();
							break;
						}
					}
				}
				
				//loop for getting city names from user to create trip
				while(gettingCities){
					//new array list to hold strings of trip info to print
					ArrayList<String> printArray = new ArrayList<String>();
					System.out.print("Enter next city name "
							+ "(or enter to end): ");
					//userIn variable to null 
					String userIn = null;
					//checks if userIn is not empty
					if ((userIn = scnr.nextLine()).length() > 0){
						//loops through cList looking for a match to userIn
						for (int i = 0; i < cList.size(); i++){
							//if a match is found, creates a new Trip object
							//with city, lat, and lon
							if (userIn.toUpperCase()
									.equals((cList.get(i)).getCity())){
								Trip newTrip = new Trip 
										((cList.get(i)).getCity(), 
										(cList.get(i)).getLat(), 
										(cList.get(i)).getLon());
								//adds this trip object to tList
								tList.add(newTrip);
								break;
							}
						}
					}
					
					//if user presses enter w/o typing anything
					else if (userIn.length() == 0){
						//and tList has been filled w/ at least 2 trip objects
						if (tList.size() >= 2){
							System.out.println("There are "+tList.size()+" "
									+ "cities in this trip.");

							//local variables for keeping track of trip objects
							//and total distances
							int a = 0;
							int b = 1;
							int totDist = 0;
							int totMi = 0;
							//loops through tList, calculates distance between
							//each trip object in tList
							for (int g = b; g < tList.size(); g++){
								double dist = Trip.getDist((tList.get(a))
										.getLat(), (tList.get(a)).getLon(), 
										(tList.get(b)).getLat(), 
										(tList.get(b)).getLon());
								
								//rounds off the double to an int
								int rDist = (int)dist;
								if ((dist - rDist) > .5){
									rDist = rDist + 1;
								}
								
								//converts distance in meters to miles
								int miles = (int)mTm(rDist);
								//string variable for printing distances
								String printDistance = "";
								//sends info to toStrings method in Trip class
								printDistance = Trip.toStrings(tList.get(a)
										.getTCity(), tList.get(b).getTCity(), 
										rDist, miles);
								//adds printDistance to printArray & prints
								printArray.add(printDistance);
								System.out.println(printDistance);
								//increments a & b to calculate next distances
								a = a +1; b = b +1;
								//adds distance found to the total distance
								totDist = totDist + rDist;
								//adds miles to total miles
								totMi = totMi + miles;
							}
							//after all the distances have been calculated
							//calculates the final return distance
							//from the last trip object in tList to the first
							double lastDist = Trip.getDist((tList.get(0))
									.getLat(), (tList.get(0)).getLon(), 
									(tList.get(tList.size()-1).getLat()), 
									(tList.get(tList.size()-1).getLon()));
							
							//rounds off the double
							int rLastDist = (int)lastDist;
							if ((lastDist - rLastDist) > .5){
								rLastDist = rLastDist + 1;
							}
							//converts meters to miles
							int lastMi = (int)mTm(lastDist);
							String printLastCity = "";
							printLastCity = Trip.toStrings(((tList
									.get(tList.size()-1)).getTCity()),
									(tList.get(0).getTCity()), 
									rLastDist, lastMi);
							//adds to print array & prints
							printArray.add(printLastCity);
							System.out.println(printLastCity);
							//adds last distance to totals to totals
							totDist = totDist + rLastDist;
							totMi = totMi + lastMi;
							//prints totals
							System.out.println("Total Distance: "+totDist+
									" meters (~ "+totMi+" miles)");
							//prompts to save trip in file
							System.out.print("Write trip details "
									+ "to file (y/n)? ");
							String yesNo = scnr.nextLine();
							//if user wants to save trip
							if (yesNo.equals("y")){
								System.out.print("Enter filename: ");
								//gets user input for fileName
								String uFileName = scnr.nextLine();
								//creates a new fileWriter 
								FileWriter fileWr = new FileWriter(uFileName);
								//prints number of cities in trip at top
								fileWr.write("There are "+tList.size()+
										" cities in this trip.");
								fileWr.write("\n");
								//loops through tList writing each line to file
								for(int i = 0; i < tList.size(); i ++) {
									//uses the printArray for trip info
									fileWr.write(printArray.get(i));
									fileWr.write("\n");
								}
								//finally writes total distance to file
								fileWr.write("Total Distance: "+totDist+
										" meters (~"+totMi+" miles)");
								fileWr.close();
								System.out.println("");
							}
							//if user does not want to make a file
							else {
								//prints a blank line, return to main menu
								System.out.println("");
								gettingCities = false;
								break;
							}
							gettingCities = false;
							break;
						}//finishes if tList.size() > 2l loop
						else if (tList.size() < 2){
							//if tList is less than 2 breaks to main menu
							System.out.println("Must have at least"
									+ " 2 cities in a trip.");
							System.out.println("");
							gettingCities = false;
							break;
						}
					}
				}
			}
			
			
//////////////////////////    MENU CHOICE 4     ///////////////////////////////
			if (input.equals("4")){
				//two double variables to hold lat and lon
				double lat1 = 0;
				double lon1 = 0;
				//gets state and city name from user
				System.out.print("Enter state name: ");
				String stateName = scnr.nextLine();
				System.out.print("Enter city name: ");
				String cityName = scnr.nextLine();

				System.out.print("Enter latitude as double (-90.0 to 90.0): ");
				boolean checkLat = true;
				//checking latitude validity
				while (checkLat){
					//takes user input in as a string
					String latStr = scnr.nextLine();
					try {
						//tries to parse into a double
						lat1 = Double.parseDouble(latStr);
					}
					catch (NumberFormatException nfe){
						//if can't be parsed, returns to top of checkLat
						System.out.println("Invalid input. Try again.");
						System.out.print("Enter latitude as double "
								+ "(-90.0 to 90.0): ");
						continue;
					}
					if (lat1 >= -90 && lat1 <=90){
						//if within range, ends loop, sends to checkLon
						checkLat = false;
					}
					else {
						//if outside range, returns to top, tries again
						System.out.println("Invalid input. Try again.");
						System.out.print("Enter latitude as double "
								+ "(-90.0 to 90.0): ");
						continue;
					}
				}
				
				//if latitude passes tests, prints enter longitude prompt
				System.out.print("Enter longitude as double "
						+ "(-180.0 to 180.0): ");
				boolean checkLon = true;
				//while checking longitude validity
				while (checkLon){
					//takes longitude as a string
					String lonStr = scnr.nextLine();
					try {
						//tries to parse longitude into a double
						lon1 = Double.parseDouble(lonStr);
					}
					catch (NumberFormatException nfe){
						//if cannot be parsed, returns to top of checkLon
						System.out.println("Invalid input. Try again.");
						System.out.print("Enter longitude as "
								+ "double (-180.0 to 180.0): ");
						continue;
					}
					if (lon1 >= -180 && lon1 <= 180){
						//if longitude within range, creates new city instance
						Cities userCity = new Cities 
								(stateName, cityName, lat1, lon1);
						//adds this new city to cList
						cList.add(userCity);
						//prints the city that has been added
						System.out.println("Added: "+userCity.toString());
						System.out.println("");
						//ends checkLon loop, breaks back to main menu
						checkLon = false;
						break;
					}
					else {
						//if outside range, returns to top of checkLon
						System.out.println("Invalid input. Try again.");
						System.out.print("Enter longitude as"
								+ " double (-180.0 to 180.0): ");
					}
				}
			}

			
//////////////////////////    MENU CHOICE 5     ///////////////////////////////
			if (input.equals("5")){
				System.out.println("Thank you for your business.");
				//checks if there is anything in the array to save 
				if (cList.size() > 0){
					System.out.println("Saved available cities "
							+ "to available_cities.txt");
					//creates new file writer with predefined name
					FileWriter fileWr = new FileWriter("available_cities.txt");
					//loops through cList, writing each city item to file
					for(int i = 0; i < cList.size(); i ++) {
						fileWr.write(((cList.get(i)).getState())+",");
						fileWr.write(((cList.get(i)).getCity())+",");
						fileWr.write((String.valueOf((cList.get(i))
								.getLat()))+",");
						fileWr.write(String.valueOf((cList.get(i)).getLon()));
						fileWr.write("\n");
					}
					//when loop is finished, closes fileWriter
					fileWr.close();
				}
				//sets main running loop to false, terminates program
				running = false;
				System.exit(0);
			}//end if input is 5	
		}//end while running loop
	}//end main method


	/**
	 * Simple method to convert distances in meters to distances in miles.
	 *
	 * @param (double dist) (distance in meters)
	 * @return (distance in miles)
	 */
	public static final double CONVERT = 0.000621371;
	public static double mTm (double dist){
		double m2m = 0;
		m2m = dist * CONVERT;
		return m2m;
	}//end mTm method

}//end class
