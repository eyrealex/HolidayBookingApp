package com.alexeyre.grpc;

import java.io.IOException;
import java.util.logging.Logger;
import com.alexeyre.grpc.FlightServiceGrpc.FlightServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class FlightServer extends FlightServiceImplBase {

	private static int port = 50051;
	private static final Logger logger = Logger.getLogger(FlightServer.class.getName());

	public static void main(String[] args) throws IOException, InterruptedException {

		FlightServer flightserver = new FlightServer();

		Server server = ServerBuilder.forPort(port).addService(flightserver).build().start();

		logger.info("Server started, listening on " + port);

		server.awaitTermination();

	}

	@Override
	public void flightLocation(LocationRequest request, StreamObserver<LocationResponse> responseObserver) {
		StringBuilder stb = new StringBuilder(request.getDestination());

		String output = stb.toString();

		LocationResponse reply = LocationResponse.newBuilder().setDestination(output).build();
		System.out.print("Holiday destination request: " + request.getDestination());

		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<DateRequest> flightDate(StreamObserver<DateResponse> responseObserver) {

		return new StreamObserver<DateRequest>() {

			String date = "";

			@Override
			public void onNext(DateRequest value) {
				System.out.println("\nDeparture date request: " + value.getDate());
				String date = value.getDate();

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCompleted() {
				DateResponse dateresponse = DateResponse.newBuilder().setDate(date).build();
				responseObserver.onNext(dateresponse);
				responseObserver.onCompleted();

			}

		};
	}

}
