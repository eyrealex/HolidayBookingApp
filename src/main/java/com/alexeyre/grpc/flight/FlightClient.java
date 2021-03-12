package com.alexeyre.grpc.flight;

import java.util.Iterator;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class FlightClient {

	private static FlightServiceGrpc.FlightServiceBlockingStub blockingStub;
	private static FlightServiceGrpc.FlightServiceStub asyncStub;

	public static void main(String[] args) throws Exception {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub = FlightServiceGrpc.newBlockingStub(channel);

		asyncStub = FlightServiceGrpc.newStub(channel);

		flightList();
		flightBooking();
		flightPeople();
		flightPassenger();

	}

	private static void flightList() {
		String location1 = "London";
		String location2 = "Paris";
		String location3 = "Berlin";
		String location4 = "Madrid";

		ListRequest request = ListRequest.newBuilder().setLocation1(location1).setLocation2(location2)
				.setLocation3(location3).setLocation4(location4).build();

		try {
			Iterator<ListResponse> response = blockingStub.flightList(request);

			System.out.println("Receiving list of possible holiday destinations from Dublin on the Client: ");
			while (response.hasNext()) {
				ListResponse temp = response.next();
				System.out.print(temp.getResult() + ", ");
			}
			System.out.println("\nAll possible destinations ... Choose one to continue ...");

		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}
	}

	private static void flightBooking() {

		StreamObserver<BookingResponse> responseObserver = new StreamObserver<BookingResponse>() {

			@Override
			public void onNext(BookingResponse value) {
				System.out.println("\n\nReceiving booking response ... \n" + "Departure destination: " + value.getDepart() + "\nDeparture date: " 
			+ value.getDepartDate() + "\nReturn destination: " + value.getArrival() + "\nReturn date: " + value.getArrivalDate());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println(
						"\nFlight destination and date have been booked ... Setting the amount of people to be booked ...");

			}

		};
		

		StreamObserver<BookingRequest> requestObserver = asyncStub.flightBooking(responseObserver);
		try {
			requestObserver.onNext(BookingRequest.newBuilder().setValue("Paris").build());
			Thread.sleep(500);

			requestObserver.onNext(BookingRequest.newBuilder().setValue("10/01/2022").build());
			Thread.sleep(500);

			requestObserver.onNext(BookingRequest.newBuilder().setValue("Dublin").build());
			Thread.sleep(500);

			requestObserver.onNext(BookingRequest.newBuilder().setValue("19/01/2022").build());
			Thread.sleep(500);

			// Mark the end of requests
			requestObserver.onCompleted();

			Thread.sleep(3000);

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void flightPeople() {
		int passengers = 3;

		PeopleRequest req = PeopleRequest.newBuilder().setPassengers(passengers).build();

		PeopleResponse response = blockingStub.flightPeople(req);

		System.out
				.println("\n\nRecieving number of people that were booked onto the flight: " + response.getPassengers());

	}

	private static void flightPassenger() {
		// TODO Auto-generated method stub

	}

}
