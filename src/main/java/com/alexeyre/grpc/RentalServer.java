package com.alexeyre.grpc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import com.alexeyre.grpc.RentalServiceGrpc.RentalServiceImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class RentalServer extends RentalServiceImplBase{

	public static void main(String[] args) {
		RentalServer rentalserver = new RentalServer();
		Properties prop = rentalserver.getProperties();
		rentalserver.registerService(prop);
		int port = Integer.valueOf(prop.getProperty("service_port"));
		
		try {
			Server server = ServerBuilder.forPort(port).addService(rentalserver).build().start();
			System.out.println("Hotel server started, listening on " + port);
			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private Properties getProperties() {

		Properties prop = null;

		try (InputStream input = new FileInputStream("src/main/resources/rental.properties")) {

			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Rental Service properies ...");
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
	public void rentalList(RentalListRequest request, StreamObserver<RentalListResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.rentalList(request, responseObserver);
	}

	@Override
	public void rentalAvailability(RentalAvailabilityRequest request,
			StreamObserver<RentalAvailabilityResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.rentalAvailability(request, responseObserver);
	}

	@Override
	public StreamObserver<RentalBookingDateRequest> rentalBooking(
			StreamObserver<RentalBookingDateResponse> responseObserver) {
		// TODO Auto-generated method stub
		return super.rentalBooking(responseObserver);
	}

	@Override
	public StreamObserver<RentalDetailsRequest> rentalDetails(StreamObserver<RentalDetailsResponse> responseObserver) {
		// TODO Auto-generated method stub
		return super.rentalDetails(responseObserver);
	}
	
	

}
