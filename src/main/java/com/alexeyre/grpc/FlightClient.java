package com.alexeyre.grpc;

import java.util.Random;
import java.util.logging.Logger;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class FlightClient {

	private static Logger logger = Logger.getLogger(FlightClient.class.getName());

	private static FlightServiceGrpc.FlightServiceBlockingStub blockingStub;
	private static FlightServiceGrpc.FlightServiceStub asyncStub;

	public static void main(String[] args) throws Exception {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub = FlightServiceGrpc.newBlockingStub(channel);
		asyncStub = FlightServiceGrpc.newStub(channel);

	}

	// Async client side streaming
	public static void flightDate() {

		StreamObserver<BookingResponse> responseObserver = new StreamObserver<BookingResponse>() {

			@Override
			public void onNext(BookingResponse value) {
				System.out.println("Receving date request: " + value.getDate());

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCompleted() {
				System.out.println("completed ");

			}
		};
		
		//
		StreamObserver<BookingRequest> requestObserver = asyncStub.flightBooking(responseObserver);
		
		try {

			requestObserver.onNext(BookingRequest.newBuilder().setDate("01/01/2021").build());
			requestObserver.onNext(BookingRequest.newBuilder().setDate("02/02/2022").build());
			requestObserver.onNext(BookingRequest.newBuilder().setDate("03/03/2023").build());
			requestObserver.onNext(BookingRequest.newBuilder().setDate("04/04/2024").build());

			System.out.println("SENDING EMSSAGES");

			// Mark the end of requests
			requestObserver.onCompleted();


			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000) + 500);


		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}

	}
}
