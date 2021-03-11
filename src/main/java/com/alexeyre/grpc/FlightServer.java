package com.alexeyre.grpc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import com.alexeyre.grpc.FlightServiceGrpc.FlightServiceImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class FlightServer extends FlightServiceImplBase {

	private static int position = 0;

	public static void main(String[] args) {

		FlightServer flightserver = new FlightServer();
		Properties prop = flightserver.getProperties();
	

		flightserver.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

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
			System.out.println("\t service_type: " + prop.getProperty("service_type"));
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_port: " + prop.getProperty("service_port"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}

	private void registerService(Properties prop) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");
			// int service_port = 1234;
			int service_port = Integer.valueOf(prop.getProperty("service_port"));

			String service_description_properties = prop.getProperty("service_description");

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);

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

		ArrayList<String> list = new ArrayList();
		list.add("London");
		list.add("Paris");
		list.add("Berlin");
		list.add("Madrid");

		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i);
			ListResponse listresponse = ListResponse.newBuilder().setLocation(name).build();
			responseObserver.onNext(listresponse);
		}

		System.out.println("Receiving list of all possible flight destinations out of Dublin Airport: " + list.get(0)
				+ ", " + list.get(1) + ", " + list.get(2) + ", " + list.get(3));
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<BookingRequest> flightBooking(StreamObserver<BookingResponse> responseObserver) {
		return new StreamObserver<BookingRequest>() {

			ArrayList<String> list = new ArrayList();
			String depart, arrival;
			String depart_date, arrival_date;
			String message;

			@Override
			public void onNext(BookingRequest value) {

				if (list.size() == 0) {

					System.out.println("\nHoliday destination departure request: " + value.getLocation());
					depart = value.getLocation();
					list.add(depart);
					System.out.println("Holiday date departure request : " + value.getDate());
					depart_date = value.getDate();
					list.add(depart_date);
				} else if (list.size() == 2) {

					System.out.println("\nArrival destination return request: " + value.getLocation());
					arrival = value.getLocation();
					list.add(arrival);
					BookingResponse.Builder response = BookingResponse.newBuilder();
					if (arrival.matches(depart)) {
						response.setResponseCode(100)
								.setResponseMessage("INVALID, Arrival location cannot be the same as Departure");
					}
					if (response.getResponseCode() == 100) {
						System.out.println("Arrival date return request : " + response.getResponseMessage());
						responseObserver.onNext(response.build());
						responseObserver.onCompleted();

					} else {
						System.out.println("Arrival date return request : " + value.getDate());
						arrival_date = value.getDate();
						list.add(arrival_date);
					}

				}

				if (list.size() > 3) {
					onCompleted();
				}
			}

			@Override
			public void onError(Throwable t) {
				// t.printStackTrace();

			}

			@Override
			public void onCompleted() {

				System.out.println("\nReceiving holiday booking completed ");
				BookingResponse bookingresponse = BookingResponse.newBuilder().build();
				responseObserver.onNext(bookingresponse);
				responseObserver.onCompleted();

			}

		};
	}

	@Override
	public void flightPeople(PeopleRequest request, StreamObserver<PeopleResponse> responseObserver) {

		int passenger = request.getPassengers();
		position = passenger;

		System.out.println("Receiving amount of passengers per booking: " + passenger);
		PeopleResponse response = PeopleResponse.newBuilder().setPassengers(passenger).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();

	}

	@Override
	public StreamObserver<PassengerRequest> flightPassenger(StreamObserver<PassengerResponse> responseObserver) {
		return new StreamObserver<PassengerRequest>() {
			ArrayList<String> list = new ArrayList();
			String seat, luggage;

			@Override
			public void onNext(PassengerRequest value) {

				System.out.println("\nReceving passenger information...\n" + "Seat preference: " + value.getSeat()
						+ "\nAmount of luggage bags taken: " + value.getLuggage());

				if (list.size() < position - 1) {
					seat = value.getSeat();
					list.add(seat);

					PassengerResponse reply = PassengerResponse.newBuilder().setSeat(seat)
							.setLuggage(value.getLuggage()).build();

					responseObserver.onNext(reply);
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

				responseObserver.onCompleted();

			}

		};
	}

}
