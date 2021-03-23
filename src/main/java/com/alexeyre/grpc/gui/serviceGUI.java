package com.alexeyre.grpc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.alexeyre.grpc.flight.BookingRequest;
import com.alexeyre.grpc.flight.BookingResponse;
import com.alexeyre.grpc.flight.FlightServiceGrpc;
import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceBlockingStub;
import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceStub;
import com.alexeyre.grpc.flight.ListRequest;
import com.alexeyre.grpc.flight.ListResponse;
import com.alexeyre.grpc.flight.PassengerRequest;
import com.alexeyre.grpc.flight.PassengerResponse;
import com.alexeyre.grpc.flight.PeopleRequest;
import com.alexeyre.grpc.flight.PeopleResponse;
import com.alexeyre.grpc.hotel.HotelAmenitiesRequest;
import com.alexeyre.grpc.hotel.HotelAmenitiesResponse;
import com.alexeyre.grpc.hotel.HotelBookingRequest;
import com.alexeyre.grpc.hotel.HotelBookingResponse;
import com.alexeyre.grpc.hotel.HotelListRequest;
import com.alexeyre.grpc.hotel.HotelListResponse;
import com.alexeyre.grpc.hotel.HotelServiceGrpc;
import com.alexeyre.grpc.hotel.HotelServiceGrpc.HotelServiceBlockingStub;
import com.alexeyre.grpc.hotel.HotelServiceGrpc.HotelServiceStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class serviceGUI {

	private static FlightServiceBlockingStub blockingStub1;
	private static FlightServiceStub asyncStub1;
	private static HotelServiceBlockingStub blockingStub2;
	private static HotelServiceStub asyncStub2;

	private static ServiceInfo serviceInfo;
	private JFrame jFrame;
	private JTabbedPane tabbedPane;
	private JTextArea flight_list_ta, location_date_ta, no_of_passengers_ta, seat_luggage_ta, hotel_list_ta,
			hotel_booking_ta, amenities_ta;
	private JTextField location_date_tf, no_of_passengers_tf, seat_pref_tf, luggage_tf, hotel_booking_tf, breakfast_tf,
			gym_tf;
	private static int position;
	ArrayList<String> list = new ArrayList();
	ArrayList<String> hotel_booking = new ArrayList();
	ArrayList<String> booking = new ArrayList();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serviceGUI serviceGUI = new serviceGUI();
					serviceGUI.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public serviceGUI() {

		String service_type = "_http._tcp.local.";
		discoverFlightService(service_type);

		String host = serviceInfo.getHostAddresses()[0];

		ManagedChannel channel1 = ManagedChannelBuilder.forAddress(host, 60001).usePlaintext().build();
		ManagedChannel channel2 = ManagedChannelBuilder.forAddress(host, 60002).usePlaintext().build();
		ManagedChannel channel3 = ManagedChannelBuilder.forAddress(host, 60003).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub1 = FlightServiceGrpc.newBlockingStub(channel1);
		asyncStub1 = FlightServiceGrpc.newStub(channel1);
		blockingStub2 = HotelServiceGrpc.newBlockingStub(channel2);
		asyncStub2 = HotelServiceGrpc.newStub(channel2);

		initGUI();
		ServiceController();

	}

	private void discoverFlightService(String service_type) {

		try {

			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Flight Service resolved: " + event.getInfo());

					serviceInfo = event.getInfo();
					int port = serviceInfo.getPort();

					System.out.println("\t Resolving for: " + service_type + " with properties ...");
					System.out.println("\t Port: " + port);
					System.out.println("\t Type: " + event.getType());
					System.out.println("\t Name: " + event.getName());
					System.out.println("\t Description/Properties: " + serviceInfo.getNiceTextString());
					System.out.println("\t Host: " + serviceInfo.getHostAddresses()[0]);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Flight Service removed: " + event.getInfo());

				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Flight Service added: " + event.getInfo());

				}

			});

			// Wait a bit
			Thread.sleep(2000);
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initGUI() {
		jFrame = new JFrame();
		jFrame.setTitle("Holiday Booking App");
		jFrame.setBounds(400, 400, 757, 742);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void ServiceController() {

		// Create JTabbedPane object
		JTabbedPane tabbedPane = new JTabbedPane();

		// Initialize the tabs
		JPanel tabPanel1 = new JPanel(false);
		JPanel tabPanel2 = new JPanel(false);
		JPanel tabPanel3 = new JPanel(false);

		// panels for tab 3
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();

		// Setting the tabs to a box layout
		BoxLayout b1 = new BoxLayout(tabPanel1, BoxLayout.Y_AXIS);
		BoxLayout b2 = new BoxLayout(tabPanel2, BoxLayout.Y_AXIS);
		BoxLayout b3 = new BoxLayout(tabPanel3, BoxLayout.Y_AXIS);

		// Configurations for tab 1

		// Panel 1
		JPanel list_flights_panel = new JPanel();
		list_flights_panel.setBackground(new Color(128, 128, 128));

		// Panel 2
		JPanel booking_flight_panel = new JPanel();
		booking_flight_panel.setBackground(Color.LIGHT_GRAY);

		// Panel 3
		JPanel people_flight_panel = new JPanel();
		people_flight_panel.setBackground(new Color(128, 128, 128));

		// Panel 4
		JPanel passengers_flight_panel = new JPanel();
		passengers_flight_panel.setBackground(Color.LIGHT_GRAY);

		// Configurations for tab 2
		JPanel list_hotel_panel = new JPanel();
		list_hotel_panel.setBackground(new Color(128, 128, 128));

		// Panel 2
		JPanel booking_hotel_panel = new JPanel();
		booking_hotel_panel.setBackground(Color.LIGHT_GRAY);

		// Panel 3
		JPanel amenities_hotel_panel = new JPanel();
		amenities_hotel_panel.setBackground(new Color(128, 128, 128));

		// Adding tab 1 Frame
		tabPanel1.setLayout(b1);
		tabbedPane.addTab("Flight", null, tabPanel1, "Tab 1 tooltip");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		// Adding tab 2 Frame
		tabPanel2.setLayout(b2);
		tabbedPane.addTab("Hotel", null, tabPanel2, "Tab 2 tooltip");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

		// Adding tab 3 Frame
		tabPanel3.setLayout(b3);
		tabbedPane.addTab("Rental", null, tabPanel3, "Tab 3 tooltip");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);

		// Formatting panels for tab 1
		list_flights_panel.setAlignmentX(0.6f);
		list_flights_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		booking_flight_panel.setAlignmentX(0.6f);
		booking_flight_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		people_flight_panel.setAlignmentX(0.6f);
		people_flight_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		passengers_flight_panel.setAlignmentX(0.6f);
		passengers_flight_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

		// Formatting panels for tab 2
		list_hotel_panel.setAlignmentX(0.6f);
		list_hotel_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		booking_hotel_panel.setAlignmentX(0.6f);
		booking_hotel_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		amenities_hotel_panel.setAlignmentX(0.6f);
		amenities_hotel_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Formatting panels for tab 3
		p7.setAlignmentX(0.6f);
		p7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p8.setAlignmentX(0.6f);
		p8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p9.setAlignmentX(0.6f);
		p9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		/*
		 * 
		 * EVERYTHING TO DO WITH FLIGHT LIST PANEL
		 * 
		 */

		// Adding panel 1 to tab 1
		tabPanel1.add(list_flights_panel);

		JLabel JLabel1 = new JLabel("(Server-side streaming) Get list of Flight Locations: ");
		list_flights_panel.add(JLabel1);

		JButton btnList = new JButton("Submit");
		list_flights_panel.add(btnList);

		flight_list_ta = new JTextArea(3, 15);
		list_flights_panel.add(flight_list_ta);
		flight_list_ta.setLineWrap(true);
		flight_list_ta.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(flight_list_ta);
		list_flights_panel.add(scrollPane);

		// creating action when btnList is clicked
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Setting each value in flightList requests to pre determined values for server
				// side streaming
				ListRequest req = ListRequest.newBuilder().setLocation1("London").setLocation2("Paris")
						.setLocation3("Berlin").setLocation4("Madrid").build();

				// Iterate through the response and print off all pre determined values at once
				// using the blockingStub
				try {
					Iterator<ListResponse> response = blockingStub1.flightList(req);
					System.out.println("\nGetting list of holdiay destinations from the GUI");

					// while there is another response.. print off the values to the console and
					// text area
					while (response.hasNext()) {
						ListResponse temp = response.next();
						flight_list_ta.append("Location: " + temp.getResult() + "\n");

						System.out.println("Location: " + temp.getResult());
					} // end while

				} catch (StatusRuntimeException f) {
					f.printStackTrace();
				} // end catch

			}// end action performed for btnList
		}); // end action listener for btnList

		/*
		 * 
		 * EVERYTHING TO DO WITH FLIGHT BOOKING PANEL
		 * 
		 */

		// Adding panel 2 to tab 1
		tabPanel1.add(booking_flight_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel JLabel2 = new JLabel("(Client-side streaming) Location/Date: ");
		booking_flight_panel.add(JLabel2);
		location_date_tf = new JTextField();
		booking_flight_panel.add(location_date_tf);
		location_date_tf.setColumns(10);

		JButton btnLocation = new JButton("Submit");
		booking_flight_panel.add(btnLocation);

		location_date_ta = new JTextArea(3, 15);
		booking_flight_panel.add(location_date_ta);
		location_date_ta.setLineWrap(true);
		location_date_ta.setWrapStyleWord(true);
		JScrollPane scrollPane1 = new JScrollPane(location_date_ta);
		booking_flight_panel.add(scrollPane1);

		// Initializing array list to be used for setting values

		// creating action when btnSubmit is clicked
		btnLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Iterating through the stream of responses for flightBooking
				StreamObserver<BookingResponse> responseObserver = new StreamObserver<BookingResponse>() {

					@Override
					public void onNext(BookingResponse value) {
						location_date_ta.append("Depart: " + value.getDepart());
						System.out.println("\nDepart: " + value.getDepart());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						location_date_ta.append("\nDepart date: " + value.getDepartDate());
						System.out.println("Depart date: " + value.getDepartDate());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						location_date_ta.append("\nArrival: " + value.getArrival());
						System.out.println("Arrival: " + value.getArrival());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						location_date_ta.append("\nArrival date: " + value.getArrivalDate());
						System.out.println("Arrival date: " + value.getArrivalDate());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}// end on next

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}// end on error

					@Override
					public void onCompleted() {
						System.out.println("\nCompleted stream");
					}// end on completed
				}; // end Iterating through the stream of responses for flightBooking

				String value = location_date_tf.getText();
				if (booking.add(value)) {
					location_date_tf.setText("");
				} // end if for removing text from text fields

				// Iterating through the stream of requests for flightBooking
				StreamObserver<BookingRequest> requestObserver = asyncStub1.flightBooking(responseObserver);

				// if the list of requests is greater than 3 do ...
				if (booking.size() > 3) {

					// loop through the list size and for each position set the value in the request
					// this prevents overriding values for each user input
					try {
						for (int i = 0; i < booking.size(); i++) {
							requestObserver.onNext(BookingRequest.newBuilder().setValue(booking.get(i)).build());
							Thread.sleep(500);
						} // end for loop

						// Mark the end of requests
						requestObserver.onCompleted();
						btnLocation.setEnabled(false);

						Thread.sleep(3000);

					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				} // end if the list of requests is greater than 3 do ...

			}// end btnSubmit action performed
		}); // end btnSubmit action listener

		/*
		 * 
		 * EVERYTHING TO DO WITH FLIGHT PEOPLE PANEL
		 * 
		 */

		// Adding panel 2 to tab 1
		tabPanel1.add(people_flight_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel JLabel3 = new JLabel("(Unary call) Number of passengers: ");
		people_flight_panel.add(JLabel3);
		no_of_passengers_tf = new JTextField();
		people_flight_panel.add(no_of_passengers_tf);
		no_of_passengers_tf.setColumns(10);

		JButton btnPassengers = new JButton("Submit");
		people_flight_panel.add(btnPassengers);

		no_of_passengers_ta = new JTextArea(3, 10);
		people_flight_panel.add(no_of_passengers_ta);
		no_of_passengers_ta.setLineWrap(true);
		no_of_passengers_ta.setWrapStyleWord(true);
		JScrollPane scrollPane2 = new JScrollPane(no_of_passengers_ta);
		people_flight_panel.add(scrollPane2);

		// creating action when btnSubmit is clicked
		btnPassengers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String input = no_of_passengers_tf.getText();
				int num = 0;

				if (!input.matches("[\\-]?[1-8]+")) {
					no_of_passengers_ta.append("ERROR, 1-8 passengers per booking" + "\n");
					System.out.println("\nERROR, 1-8 passengers per booking");
					no_of_passengers_ta.setText("");

				} else {
					num = Integer.parseInt(no_of_passengers_tf.getText());
					PeopleRequest peopleReq = PeopleRequest.newBuilder().setPassengers(num).build();
					position = num;
					PeopleResponse peopleRes = blockingStub1.flightPeople(peopleReq);

					btnPassengers.setEnabled(false);
					System.out.println("\nNumber of people booked:" + peopleRes.getPassengers());
					no_of_passengers_ta.append("Number of people booked: " + peopleRes.getPassengers());
				}

			}// end btnSubmit2 action performed

		});// end btnSubmit2 action listener

		/*
		 * 
		 * EVERYTHING TO DO WITH FLIGHT PASSENGERS PREFERENCE PANEL
		 * 
		 */

		tabPanel1.add(passengers_flight_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel JLabel4 = new JLabel("(Bi-directional) Seat preference: ");
		JLabel JLabel5 = new JLabel("Amount of luggage: ");
		passengers_flight_panel.add(JLabel4);
		seat_pref_tf = new JTextField();
		passengers_flight_panel.add(seat_pref_tf);
		seat_pref_tf.setColumns(10);

		passengers_flight_panel.add(JLabel5);
		luggage_tf = new JTextField();
		passengers_flight_panel.add(luggage_tf);
		luggage_tf.setColumns(10);

		JButton btnSeatLuggage = new JButton("Submit");
		passengers_flight_panel.add(btnSeatLuggage);

		seat_luggage_ta = new JTextArea(3, 15);
		passengers_flight_panel.add(seat_luggage_ta);
		seat_luggage_ta.setLineWrap(true);
		seat_luggage_ta.setWrapStyleWord(true);
		JScrollPane scrollPane3 = new JScrollPane(seat_luggage_ta);
		passengers_flight_panel.add(scrollPane3);

		btnSeatLuggage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StreamObserver<PassengerResponse> responseObserver = new StreamObserver<PassengerResponse>() {

					@Override
					public void onNext(PassengerResponse value) {
						System.out.println("Passenger seat preference: " + value.getSeat() + " and luggage taken: "
								+ value.getLuggage() + "\n");

						seat_luggage_ta.append("Passenger seat preference: " + value.getSeat() + " and luggage taken: "
								+ value.getLuggage() + "\n");

					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {

					}

				};

				StreamObserver<PassengerRequest> requestObserver = asyncStub1.flightPassenger(responseObserver);

				String seat = "";
				int luggage = Integer.parseInt(luggage_tf.getText());
				seat = seat_pref_tf.getText();
				list.add(seat);

				if (list.size() <= position) {

					try {

						requestObserver.onNext(PassengerRequest.newBuilder().setSeat(seat).setLuggage(luggage).build());

						if (list.size() >= position) {
							requestObserver.onCompleted();
							System.out.println("All passengers seat preference and luggage have been booked" + "\n");
							seat_luggage_ta
									.append("All passengers seat preference and luggage have been booked" + "\n");
							btnSeatLuggage.setEnabled(false);
						}

						Thread.sleep(1000);
						// Mark the end of requests

					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}

		});

		/*
		 * 
		 * EVERYTHING TO DO WITH HOTEL LIST PANEL
		 * 
		 */

		tabPanel2.add(list_hotel_panel);
		// Configuring buttons, labels and text fields for booking flight panel
		JLabel hotel_list_label = new JLabel("(Server-side) Get a list of Hotels: ");
		list_hotel_panel.add(hotel_list_label);
		JButton btnHotelList = new JButton("Submit");
		list_hotel_panel.add(btnHotelList);
		hotel_list_ta = new JTextArea(3, 20);
		list_hotel_panel.add(hotel_list_ta);
		hotel_list_ta.setLineWrap(true);
		hotel_list_ta.setWrapStyleWord(true);
		JScrollPane hotel_list_scrollpane = new JScrollPane(hotel_list_ta);
		list_hotel_panel.add(hotel_list_scrollpane);

		// creating action when btnHotelList is clicked
		btnHotelList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Setting each value in hotelList requests to pre determined values for server
				// side streaming
				HotelListRequest hotel_list_req = HotelListRequest.newBuilder().setHotel1("Marriot International")
						.setHotel2("Hilton Hotel").setHotel3("Wyndham Hotel & Resort").setHotel4("Best Western Hotel")
						.build();

				// Iterate through the response and print off all pre determined values at once
				// using the blockingStub
				try {
					Iterator<HotelListResponse> hotel_list_res = blockingStub2.hotelList(hotel_list_req);
					System.out.println("\nGetting list of holdiay destinations from the GUI");

					// while there is another response.. print off the values to the console and
					// text area
					while (hotel_list_res.hasNext()) {
						HotelListResponse temp = hotel_list_res.next();
						hotel_list_ta.append("Hotel: " + temp.getResult() + "\n");
						System.out.println("Location: " + temp.getResult());
						btnHotelList.setEnabled(false);
					} // end while

				} catch (StatusRuntimeException f) {
					f.printStackTrace();
				} // end catch

			}// end action performed for btnHotelList
		}); // end action listener for btnHotelList

		/*
		 * 
		 * EVERYTHING TO DO WITH HOTEL BOOKING PANEL
		 * 
		 */

		tabPanel2.add(booking_hotel_panel);
		// Configuring buttons, labels and text fields for booking flight panel
		JLabel hotel_booking_label = new JLabel("(Client-side) Hotel/Room type/Dates: ");
		booking_hotel_panel.add(hotel_booking_label);

		hotel_booking_tf = new JTextField();
		booking_hotel_panel.add(hotel_booking_tf);
		hotel_booking_tf.setColumns(10);

		JButton btnHotelBooking = new JButton("Submit");
		booking_hotel_panel.add(btnHotelBooking);
		hotel_booking_ta = new JTextArea(3, 20);
		booking_hotel_panel.add(hotel_booking_ta);
		hotel_booking_ta.setLineWrap(true);
		hotel_booking_ta.setWrapStyleWord(true);
		JScrollPane hotel_booking_scrollpane = new JScrollPane(hotel_booking_ta);
		booking_hotel_panel.add(hotel_booking_scrollpane);

		// creating action when btnSubmit is clicked
		btnHotelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Iterating through the stream of responses for hotelBooking
				StreamObserver<HotelBookingResponse> responseObserver = new StreamObserver<HotelBookingResponse>() {

					@Override
					public void onNext(HotelBookingResponse value) {
						hotel_booking_ta.append("Hotel: " + value.getHotel());
						System.out.println("\nGetting hotel, room and dates from client gui");
						System.out.println("Hotel: " + value.getHotel());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						hotel_booking_ta.append("\nRoom type: " + value.getRoomType());
						System.out.println("Room type: " + value.getRoomType());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						hotel_booking_ta.append("\nCheck in date: " + value.getArrivalDate());
						System.out.println("Check in date: " + value.getArrivalDate());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						hotel_booking_ta.append("\nCheck out date: " + value.getLeavingDate());
						System.out.println("Check out date: " + value.getLeavingDate());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}// end on next

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}// end on error

					@Override
					public void onCompleted() {
						System.out.println("\nCompleted hotel booking");
					}// end on completed
				}; // end Iterating through the stream of responses for hotelBooking

				String value = hotel_booking_tf.getText();
				if (hotel_booking.add(value)) {
					hotel_booking_tf.setText("");
				} // end if for removing text from text fields

				// Iterating through the stream of requests for flightBooking
				StreamObserver<HotelBookingRequest> requestObserver = asyncStub2.hotelBooking(responseObserver);

				// if the list of requests is greater than 3 do ...
				if (hotel_booking.size() > 3) {
					btnHotelBooking.setEnabled(false);

					// loop through the list size and for each position set the value in the request
					// this prevents overriding values for each user input
					try {
						for (int i = 0; i < hotel_booking.size(); i++) {
							requestObserver
									.onNext(HotelBookingRequest.newBuilder().setValue(hotel_booking.get(i)).build());
							Thread.sleep(500);
						} // end for loop

						// Mark the end of requests
						requestObserver.onCompleted();
						btnLocation.setEnabled(false);

						Thread.sleep(3000);

					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				} // end if the list of requests is greater than 3 do ...

			}// end btnSubmit action performed
		}); // end btnSubmit action listener

		/*
		 * 
		 * EVERYTHING TO DO WITH HOTEL AMENITIIES PANEL
		 * 
		 */

		// Adding panel 2 to tab 1
		tabPanel2.add(amenities_hotel_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel amenities_hotel_label = new JLabel("(Unary call) Breakfast included: ");
		amenities_hotel_panel.add(amenities_hotel_label);
		breakfast_tf = new JTextField();
		amenities_hotel_panel.add(breakfast_tf);
		breakfast_tf.setColumns(10);
		JLabel amenities_hotel_label2 = new JLabel("Gym included: ");
		amenities_hotel_panel.add(amenities_hotel_label2);
		gym_tf = new JTextField();
		amenities_hotel_panel.add(gym_tf);
		gym_tf.setColumns(10);

		JButton btnAmenities = new JButton("Submit");
		amenities_hotel_panel.add(btnAmenities);

		amenities_ta = new JTextArea(3, 15);
		amenities_hotel_panel.add(amenities_ta);
		amenities_ta.setLineWrap(true);
		amenities_ta.setWrapStyleWord(true);
		JScrollPane amenities_scroll = new JScrollPane(amenities_ta);
		amenities_hotel_panel.add(amenities_scroll);

		// creating action when btnSubmit is clicked
		btnAmenities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String breakfast = breakfast_tf.getText();
				String gym = gym_tf.getText();

				while (!(breakfast.equals("")) && !(gym.equals(""))) {
					if (breakfast.equals("yes") || breakfast.equals("no") && gym.equals("yes") || gym.equals("no")) {
						HotelAmenitiesRequest AmenitiesReq = HotelAmenitiesRequest.newBuilder().setBreakfast(breakfast)
								.setGym(gym).build();
						HotelAmenitiesResponse AmenitiesRes = blockingStub2.hotelAmenities(AmenitiesReq);

						System.out.println("\nAmenties booking has been complete");
						System.out.println("Breakfast included: " + AmenitiesRes.getBreakfast() + "\nGym included: "
								+ AmenitiesRes.getGym());
						amenities_ta.append("Breakfast included: " + AmenitiesRes.getBreakfast() + "\nGym included: "
								+ AmenitiesRes.getGym());

						// btnAmenities.setEnabled(false);
					} else {

						System.out.println("Error yes or no only");
						amenities_ta.append("Error yes or no only" + "\n");

					}

					break;

				}

			}// end btnAmenities action performed

		});// end btnAmenities action listener

		// adding the sections to the third tab
		tabPanel3.add(p7);
		tabPanel3.add(p8);
		tabPanel3.add(p9);

		// show the frame border
		jFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	}

}
// end FlightGUI class
