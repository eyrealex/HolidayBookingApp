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

	private static int position;
	private static ArrayList<String> booking_list = new ArrayList();
	ArrayList<PassengerHelper> phelper = new ArrayList<>();

	private static String depart, arrival;
	private static String depart_date, arrival_date;
	private static int passengers;
	private static String str_passengers = Integer.toString(passengers);
	private static String str_passengers2 = Integer.toString(passengers);
	private static String str_passengers3 = Integer.toString(passengers);

	private static String depart_time1 = "06.55";
	private static String depart_duration1 = "40Mins";
	private static String arrival_time1 = "07:35";
	private static String return_time1 = "18:30";
	private static String return_duration1 = "35Mins";
	private static String return_arrival_time1 = "19:05";
	private static String price1 = "€213.99";

	private static String depart_time2 = "13:20";
	private static String depart_duration2 = "40Mins";
	private static String arrival_time2 = "14:00";
	private static String return_time2 = "17:00";
	private static String return_duration2 = "35Mins";
	private static String return_arrival_time2 = "17:35";
	private static String price2 = "€229.99";

	private static String depart_time3 = "08:50";
	private static String depart_duration3 = "40Mins";
	private static String arrival_time3 = "09:30";
	private static String return_time3 = "18:20";
	private static String return_duration3 = "35Mins";
	private static String return_arrival_time3 = "18:55";
	private static String price3 = "€207.99";

	private static String randomNumber = Long.toHexString(Double.doubleToLongBits(Math.random()));
	private static String randomNumber2 = Long.toHexString(Double.doubleToLongBits(Math.random()));
	private static String randomNumber3 = Long.toHexString(Double.doubleToLongBits(Math.random()));

	private static String firstname, surname, seatNo, ticketType, flightNumber;

	public static void main(String[] args) {

		FlightServer flightserver = new FlightServer();
		Properties prop = flightserver.getProperties();

		flightserver.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("flight_service_port"));

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
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			String flight_service_type = prop.getProperty("flight_service_type");
			String flight_service_name = prop.getProperty("flight_service_name");
			int flight_service_port = Integer.valueOf(prop.getProperty("flight_service_port"));

			String flight_service_description_properties = prop.getProperty("flight_service_description");

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(flight_service_type, flight_service_name, flight_service_port,
					flight_service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", flight_service_type,
					flight_service_name);

			// Wait a bit
			Thread.sleep(1000);

			// Unregister all services
			// jmdns.unregisterAllServices();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void flightList(ListRequest request, StreamObserver<ListResponse> responseObserver) {

		ArrayList<String> flightlist = new ArrayList();
		flightlist.add("London");
		flightlist.add("Paris");
		flightlist.add("Berlin");
		flightlist.add("Madrid");

		for (int i = 0; i < flightlist.size(); i++) {
			String name = flightlist.get(i);
			ListResponse listresponse = ListResponse.newBuilder().setResult(name).build();
			responseObserver.onNext(listresponse);
		}

		System.out.println("\nAll holiday destinations departing from Dublin Airport: " + "\n" + flightlist.get(0)
				+ "\n" + flightlist.get(1) + "\n" + flightlist.get(2) + "\n" + flightlist.get(3));
		responseObserver.onCompleted();
		System.out.println("Please enter one for the following countries in the next service");
	}

	@Override
	public StreamObserver<SearchRequest> flightSearch(StreamObserver<SearchResponse> responseObserver) {
		return new StreamObserver<SearchRequest>() {

			ArrayList<String> bookinglist = new ArrayList();

			@Override
			public void onNext(SearchRequest value) {

				SearchResponse.Builder response = SearchResponse.newBuilder();

				if (bookinglist.size() == 0) {
					System.out.println("\nDeparture location: " + value.getValue());
					System.out.println("Please enter a departure date: ");
					depart = value.getValue();
					bookinglist.add(depart);
				} else if (bookinglist.size() == 1) {
					System.out.println("\nDeparture date: " + value.getValue());
					System.out.println("Please enter Dublin as your return location: ");
					depart_date = value.getValue();
					bookinglist.add(depart_date);
				} else if (bookinglist.size() == 2) {
					System.out.println("\nReturn location: " + value.getValue());
					System.out.println("Please enter a return date: ");
					arrival = value.getValue();
					bookinglist.add(arrival);
				} else if (bookinglist.size() == 3) {
					System.out.println("\nReturn date: " + value.getValue());
					System.out.println("Please enter the number of passengers to be booked: ");
					arrival_date = value.getValue();
					bookinglist.add(arrival_date);
				} else if (bookinglist.size() == 4) {
					System.out.println("Amount of passengers to book: " + value.getValue());
					str_passengers = value.getValue();
					bookinglist.add(str_passengers);
					position = Integer.parseInt(str_passengers);
				}

				else {

				}

				if (bookinglist.size() > 4) {
					onCompleted();
				}
			}

			@Override
			public void onError(Throwable t) {
				// t.printStackTrace();

			}

			@Override
			public void onCompleted() {

				System.out.println("Destination set, now searching for best prices ...");

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

	@Override
	public void flightDetails(DetailsRequest request, StreamObserver<DetailsResponse> responseObserver) {

		System.out.println("Getting Available flights ...");

		ArrayList<String> detail_list = new ArrayList();

		for (int i = 0; i <= 3; i++) {

			if (i == 0) {
				DetailsResponse reply = DetailsResponse.newBuilder().setDestination(depart)
						.setDepartureDate(depart_date).setDepartureTime(depart_time1)
						.setFlightDuration(depart_duration1).setArrivalTime(arrival_time1).setFlightNumber(randomNumber)
						.setReturnLocation(arrival).setReturnDate(arrival_date).setReturnTime(return_time1)
						.setFlightReturnDuration(return_duration1).setReturnArrivalTime(return_arrival_time1)
						.setPassengers(Integer.parseInt(str_passengers)).setPrice(price1).build();

				System.out.println("\nFlight 1: " + "\n" + "Destination: " + reply.getDestination() + "\n"
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
				DetailsResponse reply = DetailsResponse.newBuilder().setDestination(depart)
						.setDepartureDate(depart_date).setDepartureTime(depart_time2)
						.setFlightDuration(depart_duration2).setArrivalTime(arrival_time2)
						.setFlightNumber(randomNumber2).setReturnLocation(arrival).setReturnDate(arrival_date)
						.setReturnTime(return_time2).setFlightReturnDuration(return_duration2)
						.setReturnArrivalTime(return_arrival_time2).setPassengers(Integer.parseInt(str_passengers))
						.setPrice(price2).build();

				System.out.println("\nFlight 2: " + "\n" + "Destination: " + reply.getDestination() + "\n"
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
				DetailsResponse reply = DetailsResponse.newBuilder().setDestination(depart)
						.setDepartureDate(depart_date).setDepartureTime(depart_time3)
						.setFlightDuration(depart_duration3).setArrivalTime(arrival_time3)
						.setFlightNumber(randomNumber3).setReturnLocation(arrival).setReturnDate(arrival_date)
						.setReturnTime(return_time3).setFlightReturnDuration(return_duration3)
						.setReturnArrivalTime(return_arrival_time3).setPassengers(Integer.parseInt(str_passengers))
						.setPrice(price3).build();

				System.out.println("\nFlight 3: " + "\n" + "Destination: " + reply.getDestination() + "\n"
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

	@Override
	public void flightNumber(NumberRequest request, StreamObserver<NumberResponse> responseObserver) {
		flightNumber = request.getRequestValue();

		NumberResponse numberResponse = NumberResponse.newBuilder().setResponseResult(flightNumber).build();
		System.out.println("\nFlight number chosen: " + flightNumber);
		System.out.println("Please enter ticket type, seat preference and names of all passengers on the next service");
		responseObserver.onNext(numberResponse);
		responseObserver.onCompleted();

	}

	@Override
	public StreamObserver<BookingRequest> flightBooking(StreamObserver<BookingResponse> responseObserver) {

		ArrayList<String> temp = new ArrayList();

		return new StreamObserver<BookingRequest>() {

			BookingResponse bookingResponse;

			@Override
			public void onNext(BookingRequest value) {
				ticketType = null;
				firstname = null;
				surname = null;
				seatNo = null;
				booking_list.add(ticketType);

				if (booking_list.size() <= position) {
					System.out.println("\nMaking booking for passenger:" + "\n" + "Ticket type: "
							+ value.getTicketType() + "\n" + "Seat Preference: " + value.getSeatPref() + "\n"
							+ "Firstname: " + value.getFirstname() + "\n" + "Surname: " + value.getSurname());

					ticketType = value.getTicketType();
					seatNo = value.getSeatPref();
					firstname = value.getFirstname();
					surname = value.getSurname();

					phelper.add(new PassengerHelper(ticketType, seatNo, firstname, surname));

					for (int i = 0; i < phelper.size(); i++) {
						bookingResponse = BookingResponse.newBuilder().setTicketType(phelper.get(i).getTType())
								.setSeatPref(phelper.get(i).getSPref()).setFirstname(phelper.get(i).getFName())
								.setSurname(phelper.get(i).getSName()).build();

					}

					responseObserver.onNext(bookingResponse);


				} else {
					onCompleted();
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

	@Override
	public void flightDisplay(DisplayRequest request, StreamObserver<DisplayResponse> responseObserver) {

		System.out.print("\nDisplaying finalized booking");

		ArrayList<String> display_list = new ArrayList();

		for (int i = 0; i < phelper.size(); i++) {

			DisplayResponse reply = DisplayResponse.newBuilder().setDisplayDepartureDate(depart_date)
					.setDisplayDepartureTime(depart_time1).setDisplayFlightDuration(depart_duration1)
					.setDisplayArrivalTime(arrival_time1).setDisplayFlightId(randomNumber)
					.setDisplayReturnLocation(arrival).setDisplayReturnDate(arrival_date)
					.setDisplayReturnTime(return_time1).setDisplayFlightReturnDuration(return_duration1)
					.setDisplayReturnArrivalTime(return_arrival_time1).setDisplayPassengers(passengers)
					.setDisplayPrice(price1)

					.setDisplayTicketType(phelper.get(i).getTType()).setDisplaySeatPref(phelper.get(i).getSPref())
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

		System.out.println("\nDisplay Booking Completed... ");
		responseObserver.onCompleted();

	}

}
