package com.alexeyre.grpc.hotel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import com.alexeyre.grpc.flight.BookingRequest;
import com.alexeyre.grpc.flight.BookingResponse;
import com.alexeyre.grpc.flight.PassengerResponse;
import com.alexeyre.grpc.flight.PeopleResponse;
import com.alexeyre.grpc.hotel.HotelServiceGrpc.HotelServiceImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HotelServer extends HotelServiceImplBase {

	public static void main(String[] args) {
		HotelServer hotel_server = new HotelServer();
		Properties hotel_properties = hotel_server.getProperties();

		hotel_server.registerService(hotel_properties);

		int port_hotel = Integer.valueOf(hotel_properties.getProperty("hotel_service_port"));

		try {
			Server server_hotel = ServerBuilder.forPort(port_hotel).addService(hotel_server).build().start();
			System.out.println("Hotel server started, listening on " + port_hotel);
			server_hotel.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private Properties getProperties() {

		Properties hotel_properties = null;

		try (InputStream input_hotel = new FileInputStream("src/main/resources/hotel.properties")) {

			hotel_properties = new Properties();

			// load a properties file
			hotel_properties.load(input_hotel);

			// get the property value and print it out
			System.out.println("Hotel Service properies ...");
			System.out.println("\t hotel_service_type: " + hotel_properties.getProperty("hotel_service_type"));
			System.out.println("\t hotel_service_name: " + hotel_properties.getProperty("hotel_service_name"));
			System.out.println(
					"\t hotel_service_description: " + hotel_properties.getProperty("hotel_service_description"));
			System.out.println("\t hotel_service_port: " + hotel_properties.getProperty("hotel_service_port"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return hotel_properties;
	}

	private void registerService(Properties prop) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns_hotel = JmDNS.create(InetAddress.getLocalHost());

			String hotel_service_type = prop.getProperty("hotel_service_type");
			String hotel_service_name = prop.getProperty("hotel_service_name");
			// int service_port = 1234;
			int hotel_service_port = Integer.valueOf(prop.getProperty("hotel_service_port"));

			String hotel_service_description_properties = prop.getProperty("hotel_service_description");

			// Register a service
			ServiceInfo serviceInfo_hotel = ServiceInfo.create(hotel_service_type, hotel_service_name,
					hotel_service_port, hotel_service_description_properties);
			jmdns_hotel.registerService(serviceInfo_hotel);

			System.out.printf("registrering service with type %s and name %s \n", hotel_service_type,
					hotel_service_name);

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

		ArrayList<String> hotel = new ArrayList();
		hotel.add("Marriot International");
		hotel.add("Hilton Hotel");
		hotel.add("Wyndham Hotel & Resort");
		hotel.add("Best Western Hotel");

		for (int i = 0; i < hotel.size(); i++) {
			String name = hotel.get(i);
			HotelListResponse listresponse = HotelListResponse.newBuilder().setResult(name).build();
			responseObserver.onNext(listresponse);
		}

		System.out.println("Receiving list of all possible flight destinations out of Dublin Airport: " + "\n"
				+ hotel.get(0) + ", " + hotel.get(1) + ", " + hotel.get(2) + ", " + hotel.get(3));
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<HotelBookingRequest> hotelBooking(StreamObserver<HotelBookingResponse> responseObserver) {
		return new StreamObserver<HotelBookingRequest>() {

			ArrayList<String> hotel_booking_list = new ArrayList();
			String hotel, room, check_in_date, check_out_date;

			@Override
			public void onNext(HotelBookingRequest value) {
				HotelBookingResponse.Builder response = HotelBookingResponse.newBuilder();

				if (hotel_booking_list.size() == 0) {
					System.out.println("Hotel booking requested: " + value.getValue());
					hotel = value.getValue();
					hotel_booking_list.add(hotel);
				} else if (hotel_booking_list.size() == 1) {
					System.out.println("Hotel room type requested: " + value.getValue());
					room = value.getValue();
					hotel_booking_list.add(room);
				} else if (hotel_booking_list.size() == 2) {
					System.out.println("Check in date requested: " + value.getValue());
					check_in_date = value.getValue();
					hotel_booking_list.add(check_in_date);
				} else if (hotel_booking_list.size() == 3) {
					System.out.println("Check out date requested: " + value.getValue());
					check_out_date = value.getValue();
					hotel_booking_list.add(check_out_date);

				} else {

				}

				if (hotel_booking_list.size() > 3) {
					onCompleted();
				}

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCompleted() {
				System.out.println("Hotel has been booked");

				String temp1 = hotel_booking_list.get(0);
				String temp2 = hotel_booking_list.get(1);
				String temp3 = hotel_booking_list.get(2);
				String temp4 = hotel_booking_list.get(3);

				HotelBookingResponse response = HotelBookingResponse.newBuilder().setHotel(temp1).setRoomType(temp2)
						.setArrivalDate(temp3).setLeavingDate(temp4).build();
				responseObserver.onNext(response);
				responseObserver.onCompleted();

			}

		};
	}

	@Override
	public void hotelAmenities(HotelAmenitiesRequest request, StreamObserver<HotelAmenitiesResponse> responseObserver) {

		String breakfast = request.getBreakfast();
		String gym = request.getGym();

		while (!(breakfast.equals("")) && !(gym.equals(""))) {
			if (breakfast.equals("yes") || breakfast.equals("no") && gym.equals("yes") || gym.equals("no")) {
				System.out.println("\nReceiving amenities ... " + "\nDo you want breakfast: " + breakfast
						+ "\nDo you want to use the gym: " + gym);
				HotelAmenitiesResponse response = HotelAmenitiesResponse.newBuilder().setBreakfast(breakfast)
						.setGym(gym).build();

				responseObserver.onNext(response);
				responseObserver.onCompleted();
			} else {
				System.out.println("Error yes or no only");
			}
			break;
		}
		

	}

}
