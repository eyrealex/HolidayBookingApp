package com.alexeyre.grpc.hotel;

import java.util.Iterator;
import java.util.Random;

import com.alexeyre.grpc.flight.BookingRequest;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class HotelClient {

	private static HotelServiceGrpc.HotelServiceBlockingStub blockingStub;
	private static HotelServiceGrpc.HotelServiceStub asyncStub;

	public static void main(String[] args) {
		ManagedChannel hotel_channel = ManagedChannelBuilder.forAddress("localhost", 60002).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub = HotelServiceGrpc.newBlockingStub(hotel_channel);

		asyncStub = HotelServiceGrpc.newStub(hotel_channel);

		hotelList();
		hotelBooking();
		hotelAmenities();

	}

	private static void hotelList() {
		String hotel1 = "Marriot International";
		String hotel2 = "Hilton Hotel";
		String hotel3 = "Wyndham Hotel & Resort";
		String hotel4 = "Best Western Hotel";

		HotelListRequest request = HotelListRequest.newBuilder().setHotel1(hotel1).setHotel2(hotel2).setHotel3(hotel3)
				.setHotel4(hotel4).build();

		try {
			Iterator<HotelListResponse> response = blockingStub.hotelList(request);

			System.out.println("Receiving list of all hotels available in each country: ");
			while (response.hasNext()) {
				HotelListResponse temp = response.next();
				System.out.print(temp.getResult() + ", ");
			}
			System.out.println("\n\nMaking a hotel booking for the chosen country: ");

		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}

	}

	private static void hotelBooking() {

		StreamObserver<HotelBookingResponse> responseObserver = new StreamObserver<HotelBookingResponse>() {

			@Override
			public void onNext(HotelBookingResponse value) {
				System.out.println("Hotel: " + value.getHotel());
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Room type: " + value.getRoomType());
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Check in date: " + value.getArrivalDate());
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Check out date: " + value.getLeavingDate());
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
				System.out.println("\nCompleted hotel booking");

			}

		};

		StreamObserver<HotelBookingRequest> requestObserver = asyncStub.hotelBooking(responseObserver);

		try {

			requestObserver.onNext(HotelBookingRequest.newBuilder().setValue("Wyndham Hotel & Resort").build());
			Thread.sleep(500);

			requestObserver.onNext(HotelBookingRequest.newBuilder().setValue("Double").build());
			Thread.sleep(500);

			requestObserver.onNext(HotelBookingRequest.newBuilder().setValue("10/01/2022").build());
			Thread.sleep(500);

			requestObserver.onNext(HotelBookingRequest.newBuilder().setValue("19/01/2022").build());
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

	private static void hotelAmenities() {
		String breakfast = "Yes";
		String gym = "No";

		HotelAmenitiesRequest req = HotelAmenitiesRequest.newBuilder().setBreakfast(breakfast).setGym(gym).build();

		HotelAmenitiesResponse res = blockingStub.hotelAmenities(req);

		System.out.println("\nRecieving amenities: ");
		System.out.println("\nBreakfast included: " + res.getBreakfast() + "\nGym included: " + res.getGym());

	}
}
