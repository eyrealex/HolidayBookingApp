package com.alexeyre.grpc;

import java.util.logging.Logger;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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
		// TODO Auto-generated method stub

	}

	private static void flightBooking() {
		// TODO Auto-generated method stub

	}

	private static void flightPeople() {
		// TODO Auto-generated method stub

	}

	private static void flightPassenger() {
		// TODO Auto-generated method stub

	}

}
