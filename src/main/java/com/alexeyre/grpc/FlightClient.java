package com.alexeyre.grpc;

import java.util.Iterator;
import java.util.logging.Logger;

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

			while (response.hasNext()) {
				ListResponse temp = response.next();
				System.out.println(temp.getResult());
			}

		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}
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
