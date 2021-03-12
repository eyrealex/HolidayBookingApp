package com.alexeyre.grpc.hotel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import com.alexeyre.grpc.hotel.HotelServiceGrpc.HotelServiceImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HotelServer extends HotelServiceImplBase {

	public static void main(String[] args) {
		HotelServer hotelserver = new HotelServer();
		Properties prop = hotelserver.getProperties();

		hotelserver.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {
			Server server = ServerBuilder.forPort(port).addService(hotelserver).build().start();
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

		try (InputStream input = new FileInputStream("src/main/resources/hotel.properties")) {

			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Hotel Service properies ...");
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
	public void hotelList(HotelListRequest request, StreamObserver<HotelListResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.hotelList(request, responseObserver);
	}

	@Override
	public StreamObserver<HotelBookingRequest> hotelBooking(StreamObserver<HotelBookingResponse> responseObserver) {
		// TODO Auto-generated method stub
		return super.hotelBooking(responseObserver);
	}

	@Override
	public StreamObserver<HotelAmenitiesRequest> hotelAmenities(
			StreamObserver<HotelAmenitiesResponse> responseObserver) {
		// TODO Auto-generated method stub
		return super.hotelAmenities(responseObserver);
	}
	
	

}
