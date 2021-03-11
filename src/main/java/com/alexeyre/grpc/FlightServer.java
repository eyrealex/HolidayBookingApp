package com.alexeyre.grpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import com.alexeyre.grpc.FlightServiceGrpc.FlightServiceImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class FlightServer extends FlightServiceImplBase {

	private static int port = 50051;
	private static final Logger logger = Logger.getLogger(FlightServer.class.getName());
	private int position = 0;

	public static void main(String[] args) throws IOException, InterruptedException {

		FlightServer flightserver = new FlightServer();

		Server server = ServerBuilder.forPort(port).addService(flightserver).build().start();

		logger.info("Server started, listening on " + port);

		server.awaitTermination();

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
		System.out.println("Receiving amount of passengers per booking ... ");

		int passenger = request.getPassengers();
		position = passenger;
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

				if (list.size() < position) {
					seat = value.getSeat();
					list.add(seat);

					PassengerResponse reply = PassengerResponse.newBuilder().setSeat(seat)
							.setLuggage(value.getLuggage()).build();

					responseObserver.onNext(reply);
				}

			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("receiving convertBase completed ");

				// completed too
				responseObserver.onCompleted();

			}

		};
	}

}
