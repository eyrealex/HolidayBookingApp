package com.alexeyre.grpc.flight;

import java.util.Iterator;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class FlightClient {

	private static FlightServiceGrpc.FlightServiceBlockingStub blockingStub;
	private static FlightServiceGrpc.FlightServiceStub asyncStub;

	public static void main(String[] args) {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 60001).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub = FlightServiceGrpc.newBlockingStub(channel);

		asyncStub = FlightServiceGrpc.newStub(channel);

		flightList();
		flightBooking();
//		flightPeople();
//		flightPassenger();

	}

	private static void flightList() {
		String location1 = "London";
		String location2 = "Paris";
		String location3 = "Berlin";
		String location4 = "Madrid";

		ListRequest request = ListRequest.newBuilder().setValue(location1).setValue(location2).setValue(location3)
				.setValue(location4).build();

		try {
			Iterator<ListResponse> response = blockingStub.flightList(request);

			System.out.println("Receiving list of possible holiday destinations from Dublin on the Client: ");
			while (response.hasNext()) {
				ListResponse temp = response.next();
				System.out.print(temp.getResult() + ", ");
			}
			System.out.println("\n\nMaking a holiday booking for one of the countries listed: ");

		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}
	}

	private static void flightBooking() {

		StreamObserver<SearchResponse> responseObserver = new StreamObserver<SearchResponse>() {

			@Override
			public void onNext(SearchResponse value) {
				System.out.println("Departure location" + value.getDepart());
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Departure date" + value.getDepartDate());
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Arrival location" + value.getArrival());
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Departure date" + value.getArrivalDate());
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("\nCompleted flight booking");

			}

		};

		StreamObserver<SearchRequest> requestObserver = asyncStub.flightSearch(responseObserver);
		try {
			requestObserver.onNext(SearchRequest.newBuilder().setValue("Paris").build());
			Thread.sleep(500);

			requestObserver.onNext(SearchRequest.newBuilder().setValue("10/01/2022").build());
			Thread.sleep(500);

			requestObserver.onNext(SearchRequest.newBuilder().setValue("Dublin").build());
			Thread.sleep(500);

			requestObserver.onNext(SearchRequest.newBuilder().setValue("19/01/2022").build());
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

//	private static void flightPeople() {
//		int passengers = 3;
//
//		PeopleRequest req = PeopleRequest.newBuilder().setPassengers(passengers).build();
//
//		//PeopleResponse response = blockingStub.flightPeople(req);
//
//		System.out.println("\nHow many people do you want to book onto the flight: ");
//		//System.out.println("Recieving number of people: " + response.getPassengers());
//
//	}
//
//	private static void flightPassenger() {
//
//		StreamObserver<PassengerResponse> responseObserver = new StreamObserver<PassengerResponse>() {
//
//			int count = 0;
//
//			@Override
//			public void onNext(PassengerResponse value) {
//				System.out.println(
//						"Passenger seat preference: " + value.getSeat() + " and luggage taken: " + value.getLuggage());
//
//				count++;
//
//			}
//
//			@Override
//			public void onError(Throwable t) {
//				t.printStackTrace();
//
//			}
//
//			@Override
//			public void onCompleted() {
//				System.out.println("You have been successful in booking " + count + " people onto the flight.");
//				System.out.println("\nBooking has now been complete");
//
//			}
//
//		};

//		//StreamObserver<PassengerRequest> requestObserver = asyncStub.flightPassenger(responseObserver);
//
//		System.out.println("\nChoose preferences for each person that was booked onto the flight: ");
//
//		try {
//
//			Thread.sleep(800);
//			//requestObserver.onNext(PassengerRequest.newBuilder().setSeat("C4").setLuggage(2).build());
//			Thread.sleep(800);
//			//requestObserver.onNext(PassengerRequest.newBuilder().setSeat("D1").setLuggage(1).build());
//			Thread.sleep(800);
//			//requestObserver.onNext(PassengerRequest.newBuilder().setSeat("E1").setLuggage(1).build());
//			Thread.sleep(800);
//
//			// Mark the end of requests
//			//requestObserver.onCompleted();
//
//			// Sleep for a bit before sending the next one.
//			Thread.sleep(new Random().nextInt(1000) + 500);
//
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
}
