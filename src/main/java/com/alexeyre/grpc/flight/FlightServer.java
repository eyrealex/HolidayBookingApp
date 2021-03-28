package com.alexeyre.grpc.flight;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class FlightServer extends FlightServiceImplBase {

	// setting static variables so variables can be accesses in multiple methods

	// variable to get the number of passengers in an array
	private static int position;

	// init array list and array list for setting an object for passengers
	private static ArrayList<String> booking_list = new ArrayList();
	ArrayList<PassengerHelper> phelper = new ArrayList<>();

	// variables for checking flight availability
	private static String depart, arrival;
	private static String depart_date, arrival_date;
	private static int passengers;

	// variables to convert ints to strings and to choose which flight the user
	// should go on (a,b,c)
	private static String str_passengers = Integer.toString(passengers);
	private static String flightLetter;
	private static int newtemp;
	private static int count;

	// variables for flight A
	private static String depart_time1 = "06.55";
	private static String depart_duration1 = "40Mins";
	private static String arrival_time1 = "07:35";
	private static String return_time1 = "18:30";
	private static String return_duration1 = "35Mins";
	private static String return_arrival_time1 = "19:05";
	private static String price1 = "€213.99";

	// variables for flight B
	private static String depart_time2 = "13:20";
	private static String depart_duration2 = "40Mins";
	private static String arrival_time2 = "14:00";
	private static String return_time2 = "17:00";
	private static String return_duration2 = "35Mins";
	private static String return_arrival_time2 = "17:35";
	private static String price2 = "€229.99";

	// variables for flight C
	private static String depart_time3 = "08:50";
	private static String depart_duration3 = "40Mins";
	private static String arrival_time3 = "09:30";
	private static String return_time3 = "18:20";
	private static String return_duration3 = "35Mins";
	private static String return_arrival_time3 = "18:55";
	private static String price3 = "€207.99";

	// variables to create a random flight ID
	private static String randomNumber = Long.toHexString(Double.doubleToLongBits(Math.random()));
	private static String randomNumber2 = Long.toHexString(Double.doubleToLongBits(Math.random()));
	private static String randomNumber3 = Long.toHexString(Double.doubleToLongBits(Math.random()));

	// variables for the object list of passengers
	private static String firstname, surname, seatNo, ticketType, flightNumber;

	public static void main(String[] args) {

		// initializing the server and properties file for register and discovery
		FlightServer flightserver = new FlightServer();
		Properties prop = flightserver.getProperties();

		flightserver.registerService(prop);

		// init the port located in the properties file
		int port = Integer.valueOf(prop.getProperty("flight_service_port"));

		// starting the server using the port and address
		try {
			Server server = ServerBuilder.forPort(port).addService(flightserver).build().start();

			System.out.println("Flight server started, listening on " + port);
			server.awaitTermination();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private Properties getProperties() {

		Properties prop = null;

		// locating the properties file
		try (InputStream input = new FileInputStream("src/main/resources/flight.properties")) {

			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Flight Service properies ...");
			System.out.println("\t flight_service_type: " + prop.getProperty("flight_service_type"));
			System.out.println("\t flight_service_name: " + prop.getProperty("flight_service_name"));
			System.out.println("\t flight_service_description: " + prop.getProperty("flight_service_description"));
			System.out.println("\t flight_service_port: " + prop.getProperty("flight_service_port"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}

	private void registerService(Properties prop) {

		try {
			// Create a JmDNS instance to get local host address
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			// init variables types to ones declared in properties file
			String flight_service_type = prop.getProperty("flight_service_type");
			String flight_service_name = prop.getProperty("flight_service_name");
			int flight_service_port = Integer.valueOf(prop.getProperty("flight_service_port"));
			String flight_service_description_properties = prop.getProperty("flight_service_description");

			// Registering a service
			ServiceInfo serviceInfo = ServiceInfo.create(flight_service_type, flight_service_name, flight_service_port,
					flight_service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("Registrering Service with Type: %s and Name: %s \n", flight_service_type,
					flight_service_name);

			// Wait a bit
			Thread.sleep(1000);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method to get a list back of all possible flights from dublin, server side
	// streaming
	@Override
	public void flightList(ListRequest request, StreamObserver<ListResponse> responseObserver) {

		ArrayList<String> flightlist = new ArrayList();

		// adding 4 locations to an array list
		flightlist.add("London");
		flightlist.add("Paris");
		flightlist.add("Berlin");
		flightlist.add("Madrid");

		// for each position in the array list print of a location and send it to the
		// response on completed
		for (int i = 0; i < flightlist.size(); i++) {
			String name = flightlist.get(i);
			ListResponse listresponse = ListResponse.newBuilder().setResult(name).build();
			responseObserver.onNext(listresponse);
		}

		// print out showing the 4 locations
		System.out.println("\nAll holiday destinations departing from Dublin Airport: " + "\n" + flightlist.get(0)
				+ "\n" + flightlist.get(1) + "\n" + flightlist.get(2) + "\n" + flightlist.get(3));
		responseObserver.onCompleted();
		System.out.println("Please enter one for the following countries in the next service");
	}

	// method to allow the user to check for available flights using the given 4
	// countries, client side streaming
	@Override
	public StreamObserver<SearchRequest> flightSearch(StreamObserver<SearchResponse> responseObserver) {
		return new StreamObserver<SearchRequest>() {

			// creating an array list to I can end the stream after a certain amount of
			// inputs from the user, to replicate a real world booking system
			ArrayList<String> bookinglist = new ArrayList();
			String input = "";

			@Override
			public void onNext(SearchRequest value) {

				SearchResponse.Builder response = SearchResponse.newBuilder();
				input = value.getValue();

				// this method takes in 5 inputs, the array indexes at 0 and ends at 4... so 5
				// total inputs all together
				// for the first input into the system .. index 0
				if (bookinglist.size() == 0) {

					// check if the user enters in one of the following 4 countries, then add that
					// value to the array list to move to the next position in the array
					if (input.equals("Paris") || input.equals("paris") || input.equals("London")
							|| input.equals("london") || input.equals("Berlin") || input.equals("berlin")
							|| input.equals("Madrid") || input.equals("madrid")) {
						System.out.println("\nDeparture location: " + value.getValue());
						System.out.println("Please enter a departure date: ");
						depart = value.getValue();
						bookinglist.add(depart);

						// if the user has not inputed one of the given 4 countries, display and error
						// and have then try again.
					} else {
						System.out.println("\nError, enter one of the 4 countries from the list");
					}

					// if the user inputs a correct country, the position of that array is now 1
					// make sure the user inputs a string for a departure date and add it to the
					// array list to move to position 2
				} else if (bookinglist.size() == 1) {
					System.out.println("\nDeparture date: " + value.getValue());
					System.out.println("Please enter Dublin as your return location: ");
					depart_date = value.getValue();
					bookinglist.add(depart_date);
				}
				// if the position is two, make sure the user enters in dublin as the return
				// location, if so add it to the array to move to position 3
				else if (bookinglist.size() == 2) {
					if (input.equals("Dublin") || input.equals("dublin")) {
						System.out.println("\nReturn location: " + value.getValue());
						System.out.println("Please enter a return date: ");
						arrival = value.getValue();
						bookinglist.add(arrival);
					}
					// if the user does not enter dublin, display error and have them try again
					else {
						System.out.println("\nError, return location must be Dublin!");
					}

					// if the array position is 3, have the user input their return date, then add
					// it to the array list to move to position 4
				} else if (bookinglist.size() == 3) {
					System.out.println("\nReturn date: " + value.getValue());
					System.out.println("Please enter the number of passengers to be booked: " + "\n");
					arrival_date = value.getValue();
					bookinglist.add(arrival_date);
				}
				// if array is position 4, have the user enter in number for amount of
				// passengers for the booking. Add it to the array then end
				else if (bookinglist.size() == 4) {

					str_passengers = value.getValue();

					try {
						int x = Integer.parseInt(str_passengers);
						Boolean size = false;
						while (x < 6) {
							if ((x == (int) x)) {
								bookinglist.add(str_passengers);
								System.out.println("\nAmount of passengers to book: " + value.getValue());
								position = Integer.parseInt(str_passengers);
								size = true;
							}
							break;
						}
						if (size == false) {
							System.out.println("Error, 5 passengers or less per booking");
						}

					} catch (NumberFormatException y) {
						System.out.println("Error, please enter a number value for passengers");
					}

				} // end else if == 4

				else {
					System.out.println("Error, array list should not have reached this");
				}

				// if the array position is greater than 4, cut it off and send it to the
				// onCompleted
				if (bookinglist.size() > 4) {
					onCompleted();
				}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {

				System.out.println("Destination set, now searching for available flights ...");

				// create a temp variable and get each variable that has been added at each
				// array position, then set this as the varibles inside the proto before
				// completing
				String temp1 = bookinglist.get(0);
				String temp2 = bookinglist.get(1);
				String temp3 = bookinglist.get(2);
				String temp4 = bookinglist.get(3);
				String temp5 = bookinglist.get(4);

				SearchResponse response = SearchResponse.newBuilder().setDepart(temp1).setDepartDate(temp2)
						.setArrival(temp3).setArrivalDate(temp4).setPassengers(Integer.parseInt(temp5)).build();
				responseObserver.onNext(response);
				responseObserver.onCompleted();

			}

		};
	}

	// method to print out different holidays prices and times of departure and
	// return etc, using given country and dates the user has previously entered in
	@Override
	public void flightDetails(DetailsRequest request, StreamObserver<DetailsResponse> responseObserver) {

		System.out.println("Getting Available flights ...");

		ArrayList<String> detail_list = new ArrayList();

		// set the size of the list to three and loop through each of the 3 variables
		for (int i = 0; i <= 3; i++) {

			if (i == 0) {
				// if the position of the list is 0 print of holiday A
				DetailsResponse reply = DetailsResponse.newBuilder().setDestination(depart)
						.setDepartureDate(depart_date).setDepartureTime(depart_time1)
						.setFlightDuration(depart_duration1).setArrivalTime(arrival_time1).setFlightNumber(randomNumber)
						.setReturnLocation(arrival).setReturnDate(arrival_date).setReturnTime(return_time1)
						.setFlightReturnDuration(return_duration1).setReturnArrivalTime(return_arrival_time1)
						.setPassengers(Integer.parseInt(str_passengers)).setPrice(price1).build();

				System.out.println("\nFlight A: " + "\n" + "Destination: " + reply.getDestination() + "\n"
						+ "Departure date: " + reply.getDepartureDate() + "\n" + "Departure time: "
						+ reply.getDepartureTime() + "\n" + "Flight duration: " + reply.getFlightDuration() + "\n"
						+ "Arrival time: " + reply.getArrivalTime() + "\n" + "Flight number: " + reply.getFlightNumber()
						+ "\n" + "Return location: " + reply.getReturnLocation() + "\n" + "Return date: "
						+ reply.getReturnDate() + "\n" + "Return departure time: " + reply.getReturnTime() + "\n"
						+ "Return duration: " + reply.getFlightReturnDuration() + "\n" + "Return arrival time: "
						+ reply.getReturnArrivalTime() + "\n" + "No. of passengers: " + reply.getPassengers() + "\n"
						+ "Price per person: " + reply.getPrice());
				responseObserver.onNext(reply);
			} else if (i == 1) {
				// if the position of the list is 1 print of holiday B
				DetailsResponse reply = DetailsResponse.newBuilder().setDestination(depart)
						.setDepartureDate(depart_date).setDepartureTime(depart_time2)
						.setFlightDuration(depart_duration2).setArrivalTime(arrival_time2)
						.setFlightNumber(randomNumber2).setReturnLocation(arrival).setReturnDate(arrival_date)
						.setReturnTime(return_time2).setFlightReturnDuration(return_duration2)
						.setReturnArrivalTime(return_arrival_time2).setPassengers(Integer.parseInt(str_passengers))
						.setPrice(price2).build();

				System.out.println("\nFlight B: " + "\n" + "Destination: " + reply.getDestination() + "\n"
						+ "Departure date: " + reply.getDepartureDate() + "\n" + "Departure time: "
						+ reply.getDepartureTime() + "\n" + "Flight duration: " + reply.getFlightDuration() + "\n"
						+ "Arrival time: " + reply.getArrivalTime() + "\n" + "Flight number: " + reply.getFlightNumber()
						+ "\n" + "Return location: " + reply.getReturnLocation() + "\n" + "Return date: "
						+ reply.getReturnDate() + "\n" + "Return departure time: " + reply.getReturnTime() + "\n"
						+ "Return duration: " + reply.getFlightReturnDuration() + "\n" + "Return arrival time: "
						+ reply.getReturnArrivalTime() + "\n" + "No. of passengers: " + reply.getPassengers() + "\n"
						+ "Price per person: " + reply.getPrice());
				responseObserver.onNext(reply);
			} else if (i == 2) {
				// if the position of the list is 2 print of holiday C
				DetailsResponse reply = DetailsResponse.newBuilder().setDestination(depart)
						.setDepartureDate(depart_date).setDepartureTime(depart_time3)
						.setFlightDuration(depart_duration3).setArrivalTime(arrival_time3)
						.setFlightNumber(randomNumber3).setReturnLocation(arrival).setReturnDate(arrival_date)
						.setReturnTime(return_time3).setFlightReturnDuration(return_duration3)
						.setReturnArrivalTime(return_arrival_time3).setPassengers(Integer.parseInt(str_passengers))
						.setPrice(price3).build();

				System.out.println("\nFlight C: " + "\n" + "Destination: " + reply.getDestination() + "\n"
						+ "Departure date: " + reply.getDepartureDate() + "\n" + "Departure time: "
						+ reply.getDepartureTime() + "\n" + "Flight duration: " + reply.getFlightDuration() + "\n"
						+ "Arrival time: " + reply.getArrivalTime() + "\n" + "Flight number: " + reply.getFlightNumber()
						+ "\n" + "Return location: " + reply.getReturnLocation() + "\n" + "Return date: "
						+ reply.getReturnDate() + "\n" + "Return departure time: " + reply.getReturnTime() + "\n"
						+ "Return duration: " + reply.getFlightReturnDuration() + "\n" + "Return arrival time: "
						+ reply.getReturnArrivalTime() + "\n" + "No. of passengers: " + reply.getPassengers() + "\n"
						+ "Price per person: " + reply.getPrice());
				responseObserver.onNext(reply);
			}

		}

		System.out.println("\nAll available flights for your given booking completed... "
				+ "\nPlease choose one of the available flights in the next stream to finalise the booking");
		responseObserver.onCompleted();

	}

	// method to allow the user to choose one of the following holidays from the
	// list above (A, B or C)
	@Override
	public void flightNumber(NumberRequest request, StreamObserver<NumberResponse> responseObserver) {
		flightNumber = request.getRequestValue();
		String input = request.getRequestValue();

		// if the user enters in one of the following inputs (A, B or C), set the result
		// and complete the method
		if (input.equals("A") || input.equals("a") || input.equals("B") || input.equals("b") || input.equals("C")
				|| input.equals("c")) {
			NumberResponse numberResponse = NumberResponse.newBuilder().setResponseResult(flightNumber).build();
			flightLetter = flightNumber;
			System.out.println("\nFlight number chosen: " + flightNumber);
			System.out.println(
					"Please enter ticket type, seat preference and names of all passengers on the next service");
			responseObserver.onNext(numberResponse);
			responseObserver.onCompleted();
		} else {
			// if the user does not enter a, b or c return an error message
			System.out.println("\nError, enter on of the following options: A, B or C");
		}

	}

	// method to allow the user book passengers onto the flight, the passengers
	// limit is set when the user enters the number of passengers in the flight
	// search method
	@Override
	public StreamObserver<BookingRequest> flightBooking(StreamObserver<BookingResponse> responseObserver) {
		return new StreamObserver<BookingRequest>() {

			BookingResponse bookingResponse;

			@Override
			public void onNext(BookingRequest value) {

				// set the variables to the user input
				ticketType = value.getTicketType();
				seatNo = value.getSeatPref();
				firstname = value.getFirstname();
				surname = value.getSurname();

				if (!(ticketType.equals("Hello")) && (!(seatNo.equals("Hello")))
						&& (!(firstname.equals("Hello")) && (!(surname.equals("Hello"))))) {

					// add the ticket type to the array list so we can limit amount of inputs
					booking_list.add(ticketType);

					// if the list is less or equal to the amount of passengers
					if (booking_list.size() <= position) {
						System.out.println("\nMaking booking for passenger:" + count + "\n" + "Ticket type: "
								+ value.getTicketType() + "\n" + "Seat Preference: " + value.getSeatPref() + "\n"
								+ "Firstname: " + value.getFirstname() + "\n" + "Surname: " + value.getSurname());

						// create a new instance of the PassengerHelper class and add the variables the
						// user has inputed into the list
						phelper.add(new PassengerHelper(ticketType, seatNo, firstname, surname));

						// loop through the size of the array and for each passenger set the variables
						// and increment until the varaibles have been set for all the passengers
						for (int i = 0; i < phelper.size(); i++) {
							bookingResponse = BookingResponse.newBuilder().setTicketType(phelper.get(i).getTType())
									.setSeatPref(phelper.get(i).getSPref()).setFirstname(phelper.get(i).getFName())
									.setSurname(phelper.get(i).getSName()).build();

							count++;

						}

						// send the response of the set variables to the on next which will check if the
						// list is less or equal to the limit again
						responseObserver.onNext(bookingResponse);

					}
					// if the list is greater than the amount of passengers end the method
					else {
						onCompleted();
					}

				} else {
					System.out.println("Error");
				}

			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("\nFlight Booking completed for all passengers ...");
				System.out.println("\nDisplay booking information on the next service ...");
				responseObserver.onCompleted();

			}

		};

	}

	// method to display all information entered by the user and the number of
	// passengers, creating a details log for the user to see after booking has been
	// complete, server side streaming
	@Override
	public void flightDisplay(DisplayRequest request, StreamObserver<DisplayResponse> responseObserver) {

		if ((count == 0) && (flightLetter.equals(""))){
			System.out.println("Error, ensure flight number and passenger booking details have been chosen");
		} else {
			System.out.print("\nDisplaying finalized booking");

			// create an array list to loop through the amount of displays to show
			ArrayList<String> display_list = new ArrayList();

			// for loop to go through all the passengers books onto flight
			for (int i = 0; i < phelper.size(); i++) {

				// if the user entered A as their flight choice, print of all the relevant
				// details to that particular flight
				// this flight display will be duplicated to each of the passengers booked on to
				// the flight previously, shown in multiple responses, all contain same info
				// apart from passenger info

				if (flightLetter.equals("A") || flightLetter.equals("a")) {
					DisplayResponse reply = DisplayResponse.newBuilder().setDisplayDestination(depart)
							.setDisplayDepartureDate(depart_date).setDisplayDepartureTime(depart_time1)
							.setDisplayFlightDuration(depart_duration1).setDisplayArrivalTime(arrival_time1)
							.setDisplayFlightId(randomNumber).setDisplayReturnLocation(arrival)
							.setDisplayReturnDate(arrival_date).setDisplayReturnTime(return_time1)
							.setDisplayFlightReturnDuration(return_duration1)
							.setDisplayReturnArrivalTime(return_arrival_time1).setDisplayPassengers(passengers)
							.setDisplayPrice(price1)

							.setDisplayTicketType(phelper.get(i).getTType())
							.setDisplaySeatPref(phelper.get(i).getSPref())
							.setDisplayFirstname(phelper.get(i).getFName()).setDisplaySurname(phelper.get(i).getSName())
							.build();

					System.out.println("\nDeparture: " + depart);
					System.out.println("Depart date: " + depart_date);
					System.out.println("Depart time: " + depart_time1);
					System.out.println("Depart duration : " + depart_duration1);
					System.out.println("Arrival time: " + arrival_time1);
					System.out.println("Flight id: " + randomNumber);
					System.out.println("Return location: " + arrival);
					System.out.println("Return date: " + arrival_date);
					System.out.println("Return departure time: " + return_time1);
					System.out.println("Return flight duration: " + return_duration1);
					System.out.println("Return arrival time: " + return_arrival_time1);
					System.out.println("No. of passengers: " + passengers);
					System.out.println("Price per passenger: " + price1);
					System.out.println("Ticket type: " + phelper.get(i).getTType());
					System.out.println("Seat preference: " + phelper.get(i).getSPref());
					System.out.println("Firstname: " + phelper.get(i).getFName());
					System.out.println("Surname: " + phelper.get(i).getSName());

					responseObserver.onNext(reply);

				}
				// if the user entered B as their flight choice, print of all the relevant
				// details to that particular flight
				// this flight display will be duplicated to each of the passengers booked on to
				// the flight previously, shown in multiple responses, all contain same info
				// apart from passenger info

				else if (flightLetter.equals("B") || flightLetter.equals("b")) {
					DisplayResponse reply = DisplayResponse.newBuilder().setDisplayDepartureDate(depart_date)
							.setDisplayDepartureTime(depart_time2).setDisplayFlightDuration(depart_duration2)
							.setDisplayArrivalTime(arrival_time2).setDisplayFlightId(randomNumber)
							.setDisplayReturnLocation(arrival).setDisplayReturnDate(arrival_date)
							.setDisplayReturnTime(return_time2).setDisplayFlightReturnDuration(return_duration2)
							.setDisplayReturnArrivalTime(return_arrival_time1).setDisplayPassengers(passengers)
							.setDisplayPrice(price2)

							.setDisplayTicketType(phelper.get(i).getTType())
							.setDisplaySeatPref(phelper.get(i).getSPref())
							.setDisplayFirstname(phelper.get(i).getFName()).setDisplaySurname(phelper.get(i).getSName())
							.build();

					System.out.println("\nDeparture: " + depart);
					System.out.println("Depart date: " + depart_date);
					System.out.println("Depart time: " + depart_time2);
					System.out.println("Depart duration : " + depart_duration2);
					System.out.println("Arrival time: " + arrival_time2);
					System.out.println("Flight id: " + randomNumber);
					System.out.println("Return location: " + arrival);
					System.out.println("Return date: " + arrival_date);
					System.out.println("Return departure time: " + return_time2);
					System.out.println("Return flight duration: " + return_duration2);
					System.out.println("Return arrival time: " + return_arrival_time2);
					System.out.println("No. of passengers: " + passengers);
					System.out.println("Price per passenger: " + price2);
					System.out.println("Ticket type: " + phelper.get(i).getTType());
					System.out.println("Seat preference: " + phelper.get(i).getSPref());
					System.out.println("Firstname: " + phelper.get(i).getFName());
					System.out.println("Surname: " + phelper.get(i).getSName());

					responseObserver.onNext(reply);
				}
				// if the user entered C as their flight choice, print of all the relevant
				// details to that particular flight
				// this flight display will be duplicated to each of the passengers booked on to
				// the flight previously, shown in multiple responses, all contain same info
				// apart from passenger info
				else if (flightLetter.equals("C") || flightLetter.equals("c")) {
					DisplayResponse reply = DisplayResponse.newBuilder().setDisplayDepartureDate(depart_date)
							.setDisplayDepartureTime(depart_time3).setDisplayFlightDuration(depart_duration3)
							.setDisplayArrivalTime(arrival_time3).setDisplayFlightId(randomNumber)
							.setDisplayReturnLocation(arrival).setDisplayReturnDate(arrival_date)
							.setDisplayReturnTime(return_time3).setDisplayFlightReturnDuration(return_duration3)
							.setDisplayReturnArrivalTime(return_arrival_time3).setDisplayPassengers(passengers)
							.setDisplayPrice(price3)

							.setDisplayTicketType(phelper.get(i).getTType())
							.setDisplaySeatPref(phelper.get(i).getSPref())
							.setDisplayFirstname(phelper.get(i).getFName()).setDisplaySurname(phelper.get(i).getSName())
							.build();

					System.out.println("\nDeparture: " + depart);
					System.out.println("Depart date: " + depart_date);
					System.out.println("Depart time: " + depart_time3);
					System.out.println("Depart duration : " + depart_duration3);
					System.out.println("Arrival time: " + arrival_time3);
					System.out.println("Flight id: " + randomNumber);
					System.out.println("Return location: " + arrival);
					System.out.println("Return date: " + arrival_date);
					System.out.println("Return departure time: " + return_time3);
					System.out.println("Return flight duration: " + return_duration3);
					System.out.println("Return arrival time: " + return_arrival_time3);
					System.out.println("No. of passengers: " + passengers);
					System.out.println("Price per passenger: " + price3);
					System.out.println("Ticket type: " + phelper.get(i).getTType());
					System.out.println("Seat preference: " + phelper.get(i).getSPref());
					System.out.println("Firstname: " + phelper.get(i).getFName());
					System.out.println("Surname: " + phelper.get(i).getSName());

					responseObserver.onNext(reply);
				}
				// if there has been no flight number selected previously return an error
				// message
				else {

					System.out.println("Error has occured when choosing flight number");
				}

			}

			System.out.println("\nDisplay Booking Completed... ");
			responseObserver.onCompleted();
		}

	}

}
