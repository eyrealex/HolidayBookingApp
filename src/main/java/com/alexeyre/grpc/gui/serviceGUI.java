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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.alexeyre.grpc.flight.BookingRequest;
import com.alexeyre.grpc.flight.BookingResponse;
import com.alexeyre.grpc.flight.DetailsRequest;
import com.alexeyre.grpc.flight.DetailsResponse;
import com.alexeyre.grpc.flight.DisplayRequest;
import com.alexeyre.grpc.flight.DisplayResponse;
import com.alexeyre.grpc.flight.FlightServiceGrpc;
import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceBlockingStub;
import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceStub;
import com.alexeyre.grpc.flight.ListRequest;
import com.alexeyre.grpc.flight.ListResponse;
import com.alexeyre.grpc.flight.NumberRequest;
import com.alexeyre.grpc.flight.NumberResponse;
import com.alexeyre.grpc.flight.PassengerHelper;
import com.alexeyre.grpc.flight.SearchRequest;
import com.alexeyre.grpc.flight.SearchResponse;
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

	ArrayList<String> list = new ArrayList();
	ArrayList<String> hotel_booking = new ArrayList();
	ArrayList<String> booking = new ArrayList();
	private static ArrayList<String> booking_list = new ArrayList();
	ArrayList<PassengerHelper> phelper = new ArrayList<>();

	// variables for checking flight availability
	private static String depart, arrival;
	private static String depart_date, arrival_date;
	private static int passengers;

	private static String ticket, seat, fname, sname;
	private int count = 0;
	private int ticketcount = 0;
	private int newtemp = 0;
	private static String hotel_input;

	// variables to create a random flight ID
	private static String randomNumber1 = Long.toHexString(Double.doubleToLongBits(Math.random()));
	private static String randomNumber2 = Long.toHexString(Double.doubleToLongBits(Math.random()));
	private static String randomNumber3 = Long.toHexString(Double.doubleToLongBits(Math.random()));

	// variable to get number of passengers
	private static int position;
	private static String str_passengers = Integer.toString(passengers);

	private static String option;

	// variables for flight A
	private static String depart_time1 = "06.55";
	private static String depart_duration1 = "40Mins";
	private static String arrival_time1 = "07:35";
	private static String return_time1 = "18:30";
	private static String return_duration1 = "35Mins";
	private static String return_arrival_time1 = "19:05";
	private static String price1 = "€213.99";

	// variables for flight B
	private static String depart_time2 = "13:20";
	private static String depart_duration2 = "40Mins";
	private static String arrival_time2 = "14:00";
	private static String return_time2 = "17:00";
	private static String return_duration2 = "35Mins";
	private static String return_arrival_time2 = "17:35";
	private static String price2 = "€229.99";

	// variables for flight C
	private static String depart_time3 = "08:50";
	private static String depart_duration3 = "40Mins";
	private static String arrival_time3 = "09:30";
	private static String return_time3 = "18:20";
	private static String return_duration3 = "35Mins";
	private static String return_arrival_time3 = "18:55";
	private static String price3 = "€207.99";

	// variables for booking panel

	private JFrame jFrame;
	private JTabbedPane tabbedPane;
	private JTextArea flight_list_ta, location_date_ta, no_of_passengers_ta, seat_luggage_ta, hotel_list_ta,
			hotel_booking_ta, amenities_ta, letter_ta, booking_ta, display_ta;
	private JTextField location_date_tf, no_of_passengers_tf, seat_pref_tf, luggage_tf, hotel_booking_tf, breakfast_tf,
			gym_tf, letter_tf, ticket_tf, seat_tf, fname_tf, sname_tf;

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

		// Setting the tabs to a box layout
		BoxLayout b1 = new BoxLayout(tabPanel1, BoxLayout.Y_AXIS);
		BoxLayout b2 = new BoxLayout(tabPanel2, BoxLayout.Y_AXIS);

		////////////////////////////////////////
		// Configurations for tab 1
		///////////////////////////////////////
		// Panel 1
		JPanel list_flights_panel = new JPanel();
		list_flights_panel.setBackground(new Color(128, 128, 128));

		// Panel 2
		JPanel search_flight_panel = new JPanel();
		search_flight_panel.setBackground(Color.LIGHT_GRAY);

		// Panel 3
		JPanel details_flight_panel = new JPanel();
		details_flight_panel.setBackground(new Color(128, 128, 128));

		// Panel 4
		JPanel letter_flight_panel = new JPanel();
		letter_flight_panel.setBackground(Color.LIGHT_GRAY);

		// Panel 5
		JPanel booking_flight_panel = new JPanel();
		booking_flight_panel.setBackground(new Color(128, 128, 128));

		// Panel 6
		JPanel display_flight_panel = new JPanel();
		display_flight_panel.setBackground(Color.LIGHT_GRAY);

		/////////////////////////////////////////
		// Configurations for tab 2
		////////////////////////////////////////
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

		// Formatting panels for tab 1
		list_flights_panel.setAlignmentX(0.6f);
		list_flights_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		search_flight_panel.setAlignmentX(0.6f);
		search_flight_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		details_flight_panel.setAlignmentX(0.6f);
		details_flight_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		letter_flight_panel.setAlignmentX(0.6f);
		letter_flight_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		booking_flight_panel.setAlignmentX(0.6f);
		booking_flight_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		display_flight_panel.setAlignmentX(0.6f);
		display_flight_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

		// Formatting panels for tab 2
		list_hotel_panel.setAlignmentX(0.6f);
		list_hotel_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		booking_hotel_panel.setAlignmentX(0.6f);
		booking_hotel_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		amenities_hotel_panel.setAlignmentX(0.6f);
		amenities_hotel_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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

		flight_list_ta = new JTextArea(4, 20);
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
				ListRequest req = ListRequest.newBuilder().setValue("London").setValue("Paris").setValue("Berlin")
						.setValue("Madrid").build();

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
		 * EVERYTHING TO DO WITH FLIGHT SEARCH PANEL
		 * 
		 */

		// Adding panel 2 to tab 1
		tabPanel1.add(search_flight_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel JLabel2 = new JLabel("(Client-side streaming) Location/Date/Return/Date/Passengers: ");
		search_flight_panel.add(JLabel2);
		location_date_tf = new JTextField();
		search_flight_panel.add(location_date_tf);
		location_date_tf.setColumns(10);

		JButton btnSearch = new JButton("Submit");
		search_flight_panel.add(btnSearch);

		location_date_ta = new JTextArea(4, 20);
		search_flight_panel.add(location_date_ta);
		location_date_ta.setLineWrap(true);
		location_date_ta.setWrapStyleWord(true);
		JScrollPane scrollPane1 = new JScrollPane(location_date_ta);
		search_flight_panel.add(scrollPane1);

		// Initializing array list to be used for setting values

		// creating action when btnSubmit is clicked
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Iterating through the stream of responses for flightBooking
				StreamObserver<SearchResponse> responseObserver = new StreamObserver<SearchResponse>() {

					@Override
					public void onNext(SearchResponse value) {
						location_date_ta.append("Depart: " + value.getDepart());
						System.out.println("\nDepart: " + value.getDepart());
						depart = value.getDepart();

						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						location_date_ta.append("\nDepart date: " + value.getDepartDate());
						System.out.println("Depart date: " + value.getDepartDate());
						depart_date = value.getDepartDate();
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						location_date_ta.append("\nArrival: " + value.getArrival());
						System.out.println("Arrival: " + value.getArrival());
						arrival = value.getArrivalDate();
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						location_date_ta.append("\nArrival date: " + value.getArrivalDate());
						System.out.println("Arrival date: " + value.getArrivalDate());
						arrival_date = value.getArrivalDate();
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						location_date_ta.append("\nNo. of passengers: " + value.getPassengers());
						System.out.println("No. of passengers: " + value.getPassengers());
						passengers = value.getPassengers();
						str_passengers = Integer.toString(value.getPassengers());
						position = Integer.parseInt(str_passengers);
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
						System.out.println("Completed stream");
					}// end on completed
				}; // end Iterating through the stream of responses for flightBooking

				String value = location_date_tf.getText();
				// error validation for empty string in textfield
				if (!(value.isEmpty())) {
					// validate array position 0 to be one of the 4 countries
					if (booking.size() == 0) {
						if (value.equals("Paris") || value.equals("paris") || value.equals("London")
								|| value.equals("london") || value.equals("Berlin") || value.equals("berlin")
								|| value.equals("Madrid") || value.equals("madrid")) {
							booking.add(value);
						} else {
							JOptionPane.showMessageDialog(jFrame,
									"Error, enter one of the four countries listed as your departure ");
						}
						// validate array position 2 to be dublin as the return location
					} else if (booking.size() == 2) {
						if ((value.equals("Dublin")) || (value.equals("dublin"))) {
							booking.add(value);
						} else {
							JOptionPane.showMessageDialog(jFrame, "Error, enter Dublin as the return location");
						}

					}
					// validate array position 4 to be an integer and less that 6
					else if (booking.size() == 4) {

						// try to catch when a user inputs a string instead of an integer
						try {
							// parse value to an integer
							int x = Integer.parseInt(value);
							Boolean size = false;
							while (x < 6) {
								// if the value of x is an integer add value to the array
								if ((x == (int) x)) {
									booking.add(value);
									size = true;
								}
								break;
							}

							// boolean to validate if size is < 6
							if (size == false) {
								JOptionPane.showMessageDialog(jFrame, "Error, 5 passengers or less per booking");
							}

						} catch (NumberFormatException y) {
							JOptionPane.showMessageDialog(jFrame, "Error, please enter a number value for passengers");
						}

					}

					// add value to array if not validating for specific parameters
					else {
						booking.add(value);
					}

					// reset text field after every input
					location_date_tf.setText("");
				} else {
					JOptionPane.showMessageDialog(jFrame, "Error, field(s) cannot be empty");
				}

				// Iterating through the stream of requests for flightBooking
				StreamObserver<SearchRequest> requestObserver = asyncStub1.flightSearch(responseObserver);

				// ensure the array list can run for the 5 input values
				if (booking.size() > 4) {

					// loop through the list size and for each position set the value in the request
					// this prevents overriding values for each user input
					try {
						for (int i = 0; i < booking.size(); i++) {

							requestObserver.onNext(SearchRequest.newBuilder().setValue(booking.get(i)).build());
							Thread.sleep(500);
						} // end for loop

						// Mark the end of requests
						requestObserver.onCompleted();
						btnSearch.setEnabled(false);

						Thread.sleep(3000);

					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				} // end if

			}// end btnSubmit action performed
		}); // end btnSubmit action listener

		/*
		 * 
		 * EVERYTHING TO DO WITH DETAILS PEOPLE PANEL
		 * 
		 */

		tabPanel1.add(details_flight_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel details_label = new JLabel("Get available flights for booking: ");
		details_flight_panel.add(details_label);

		JButton btnDetails = new JButton("Submit");
		details_flight_panel.add(btnDetails);

		JTextArea details_ta = new JTextArea(15, 20);
		details_flight_panel.add(details_ta);
		details_ta.setLineWrap(true);
		details_ta.setWrapStyleWord(true);
		JScrollPane details_scrollpane = new JScrollPane(details_ta);
		details_flight_panel.add(details_scrollpane);

		// creating action when btnSubmit is clicked
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (position == 0) {
					JOptionPane.showMessageDialog(jFrame, "Error, must complete previous services");
				} else {
					// set the details values using the static variables
					DetailsRequest detailsRequest = DetailsRequest.newBuilder().setDetailsValue(depart)
							.setDetailsValue(depart_date).setDetailsValue(depart_time1)
							.setDetailsValue(depart_duration1).setDetailsValue(arrival_time1)
							.setDetailsValue(randomNumber1).setDetailsValue(arrival).setDetailsValue(arrival_date)
							.setDetailsValue(arrival_time1).setDetailsValue(return_duration1)
							.setDetailsValue(return_arrival_time1).setDetailsValue(str_passengers)
							.setDetailsValue(price1).build();

					try {
						Iterator<DetailsResponse> detailsResponse = blockingStub1.flightDetails(detailsRequest);
						System.out.println("\nGetting flight availability from the GUI");
						details_ta.append("Flight A, B and C");

						// for all the different flights A,B and C get the static variables
						while (detailsResponse.hasNext()) {
							DetailsResponse temp = detailsResponse.next();
							details_ta.append("\n" + "Destination: " + temp.getDestination() + "\n" + "Departure date: "
									+ temp.getDepartureDate() + "\n" + "Departure time: " + temp.getDepartureTime()
									+ "\n" + "Flight duration: " + temp.getFlightDuration() + "\n" + "Arrival time: "
									+ temp.getArrivalTime() + "\n" + "Flight ID: " + temp.getFlightNumber() + "\n"
									+ "Return location: " + temp.getReturnLocation() + "\n" + "Return date: "
									+ temp.getReturnDate() + "\n" + "Return time: " + temp.getReturnTime() + "\n"
									+ "Flight duration: " + temp.getFlightReturnDuration() + "\n" + "Arrival time: "
									+ temp.getReturnArrivalTime() + "\n" + "Passengers: " + temp.getPassengers() + "\n"
									+ "Price per person: " + temp.getPrice() + "\n");

							System.out.println("Destination: " + temp.getDestination() + "\n" + "Departure date: "
									+ temp.getDepartureDate() + "\n" + "Departure time: " + temp.getDepartureTime()
									+ "\n" + "Flight duration: " + temp.getFlightDuration() + "\n" + "Arrival time: "
									+ temp.getArrivalTime() + "\n" + "Flight ID: " + temp.getFlightNumber() + "\n"
									+ "Return location: " + temp.getReturnLocation() + "\n" + "Return date: "
									+ temp.getReturnDate() + "\n" + "Return time: " + temp.getReturnTime() + "\n"
									+ "Flight duration: " + temp.getFlightReturnDuration() + "\n" + "Arrival time: "
									+ temp.getReturnArrivalTime() + "\n" + "Passengers: " + temp.getPassengers() + "\n"
									+ "Price per person: " + temp.getPrice() + "\n");

							btnDetails.setEnabled(false);

						}
					} catch (StatusRuntimeException f) {

						f.printStackTrace();
					} // end catch
				}

			}// end action performed

		});

		/*
		 * 
		 * EVERYTHING TO DO WITH FLIGHT LETTER PANEL
		 * 
		 */

		// Adding panel 2 to tab 1
		tabPanel1.add(letter_flight_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel letter_label = new JLabel("(Unary call) Choose flight A/B or C: ");
		letter_flight_panel.add(letter_label);
		letter_tf = new JTextField();
		letter_flight_panel.add(letter_tf);
		letter_tf.setColumns(10);

		JButton btnLetter = new JButton("Submit");
		letter_flight_panel.add(btnLetter);

		letter_ta = new JTextArea(4, 20);
		letter_flight_panel.add(letter_ta);
		letter_ta.setLineWrap(true);
		letter_ta.setWrapStyleWord(true);
		JScrollPane letter_scroll = new JScrollPane(letter_ta);
		letter_flight_panel.add(letter_scroll);

		// creating action when btnSubmit is clicked
		btnLetter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = letter_tf.getText();
				option = letter_tf.getText();

				// validate the check if user only enters in either A, B or C
				if (option.equals("A") || option.equals("a") || option.equals("B") || option.equals("b")
						|| option.equals("C") || option.equals("c")) {

					NumberRequest numberRequest = NumberRequest.newBuilder().setRequestValue(option).build();
					NumberResponse numberResponse = blockingStub1.flightNumber(numberRequest);

					System.out.println("Flight letter has been chosen: " + option);
					letter_ta.append("Flight letter has been chosen: " + option);

					btnLetter.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(jFrame, "Error, enter A, B or C");
					letter_tf.setText("");
				}

			}
		});

		/*
		 * 
		 * EVERYTHING TO DO WITH FLIGHT BOOKING PANEL
		 * 
		 */

		// Adding panel 2 to tab 1
		tabPanel1.add(booking_flight_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel booking_label1 = new JLabel("(Bi-directional) Choose a ticket type (Standard or Premium): ");
		booking_flight_panel.add(booking_label1);
		ticket_tf = new JTextField();
		booking_flight_panel.add(ticket_tf);
		ticket_tf.setColumns(10);

		JLabel booking_label2 = new JLabel("Seat pref (Between A1-A9 and P1 - P9: ");
		booking_flight_panel.add(booking_label2);
		seat_tf = new JTextField();
		booking_flight_panel.add(seat_tf);
		seat_tf.setColumns(10);

		JLabel booking_label3 = new JLabel("First name: ");
		booking_flight_panel.add(booking_label3);
		fname_tf = new JTextField();
		booking_flight_panel.add(fname_tf);
		fname_tf.setColumns(10);

		JLabel booking_label4 = new JLabel("Surname: ");
		booking_flight_panel.add(booking_label4);
		sname_tf = new JTextField();
		booking_flight_panel.add(sname_tf);
		sname_tf.setColumns(10);

		JButton btnBooking = new JButton("Submit");
		booking_flight_panel.add(btnBooking);

		booking_ta = new JTextArea(4, 20);
		booking_flight_panel.add(booking_ta);
		booking_ta.setLineWrap(true);
		booking_ta.setWrapStyleWord(true);
		JScrollPane booking_scroll = new JScrollPane(booking_ta);
		booking_flight_panel.add(booking_scroll);

		btnBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StreamObserver<BookingResponse> responseObserver = new StreamObserver<BookingResponse>() {

					@Override
					public void onNext(BookingResponse value) {
						System.out.println("\nPassenger " + count + "\nTicket Type: " + value.getTicketType() + "\n"
								+ "Seat Preference: " + value.getSeatPref() + "\n" + "First name: "
								+ value.getFirstname() + "\n" + "Surname: " + value.getSurname() + "\n");

						booking_ta.append("Passenger " + count + "\nTicket Type: " + value.getTicketType() + "\n"
								+ "Seat Preference: " + value.getSeatPref() + "\n" + "First name: "
								+ value.getFirstname() + "\n" + "Surname: " + value.getSurname() + "\n");

					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {
						System.out.println("\nBooking completed ... ");

					}

				};

				StreamObserver<BookingRequest> requestObserver = asyncStub1.flightBooking(responseObserver);

				ticket = ticket_tf.getText();
				seat = seat_tf.getText();
				fname = fname_tf.getText();
				sname = sname_tf.getText();

				// validate to all for no empty fields
				if (!(ticket.equals("") && (!(seat.equals("")))) && (!(fname.equals(""))) && (!(sname.equals("")))) {
					booking_list.add(ticket);

					// ensure the array list is less than or equal to no of passengers
					if (booking_list.size() <= position) {

						// create a new instance for passenger model class to include user inputs
						phelper.add(new PassengerHelper(ticket, seat, fname, sname));

						try {

							requestObserver.onNext(BookingRequest.newBuilder().setTicketType(ticket).setSeatPref(seat)
									.setFirstname(fname).setSurname(sname).build());

							ticket_tf.setText("");
							seat_tf.setText("");
							fname_tf.setText("");
							sname_tf.setText("");

							// count to record number of passengers
							count++;

							Thread.sleep(1000);
							// Mark the end of requests

						} catch (RuntimeException e1) {
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						requestObserver.onCompleted();
						System.out.println("All passengers details and ticket info booked" + "\n");
						booking_ta.append("All passengers details and ticket info booked" + "\n");
						btnBooking.setEnabled(false);

					}
				} else {
					JOptionPane.showMessageDialog(jFrame, "Error, field(s) cannot be empty!!!");
				}

			}

		});

		/*
		 * 
		 * EVERYTHING TO DO WITH DISPLAY PEOPLE PANEL
		 * 
		 */

		tabPanel1.add(display_flight_panel);

		// Configuring buttons, labels and text fields for booking flight panel
		JLabel display_label = new JLabel("(Server-side streaming) Display finalized booking: ");
		display_flight_panel.add(display_label);

		JButton btnDisplay = new JButton("Submit");
		display_flight_panel.add(btnDisplay);

		JTextArea display_ta = new JTextArea(15, 20);
		display_flight_panel.add(display_ta);
		display_ta.setLineWrap(true);
		display_ta.setWrapStyleWord(true);
		JScrollPane display_scrollpane = new JScrollPane(display_ta);
		display_flight_panel.add(display_scrollpane);

		btnDisplay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (count == 0) {
					JOptionPane.showMessageDialog(jFrame, "Error, must complete previous services");
				}

				// if user chose flight A print of tickets with necessary deatil
				if (option.equals("A") || option.equals("a")) {
					DisplayRequest displayRequest = DisplayRequest.newBuilder().setDisplayRequest(depart)
							.setDisplayRequest(depart_date).setDisplayRequest(depart_time1)
							.setDisplayRequest(depart_duration1).setDisplayRequest(arrival_time1)
							.setDisplayRequest(randomNumber1).setDisplayRequest(arrival).setDisplayRequest(arrival_date)
							.setDisplayRequest(return_time1).setDisplayRequest(return_duration1)
							.setDisplayRequest(return_arrival_time1).setDisplayRequest(str_passengers)
							.setDisplayRequest(price1).setDisplayRequest(ticket).setDisplayRequest(seat)
							.setDisplayRequest(fname).setDisplayRequest(sname).build();

					try {
						Iterator<DisplayResponse> displayResponse = blockingStub1.flightDisplay(displayRequest);
						System.out.println("\nDisplaying finalized booking");
						display_ta.append("Displaying finalized ticket bookings for each passenger.");

						while (displayResponse.hasNext()) {
							DisplayResponse temp = displayResponse.next();
							display_ta.append("\nDestination: " + temp.getDisplayDestination() + "\n"
									+ "Departure date: " + temp.getDisplayDepartureDate() + "\n" + "Departure time: "
									+ temp.getDisplayDepartureTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayArrivalTime() + "\n" + "Flight ID: " + temp.getDisplayFlightId()
									+ "\n" + "Return location: " + temp.getDisplayReturnLocation() + "\n"
									+ "Return date: " + temp.getDisplayReturnDate() + "\n" + "Return time: "
									+ temp.getDisplayReturnTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightReturnDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayReturnArrivalTime() + "\n" + "Price per person: "
									+ temp.getDisplayPrice() + "\n" + "Ticket type: " + temp.getDisplayTicketType()
									+ "\n" + "Seat Preference: " + temp.getDisplaySeatPref() + "\n" + "Firstname: "
									+ temp.getDisplayFirstname() + "\n" + "Surname: " + temp.getDisplaySurname()
									+ "\n");

							System.out.println("\nDestination: " + temp.getDisplayDestination() + "\n"
									+ "Departure date: " + temp.getDisplayDepartureDate() + "\n" + "Departure time: "
									+ temp.getDisplayDepartureTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayArrivalTime() + "\n" + "Flight ID: " + temp.getDisplayFlightId()
									+ "\n" + "Return location: " + temp.getDisplayReturnLocation() + "\n"
									+ "Return date: " + temp.getDisplayReturnDate() + "\n" + "Return time: "
									+ temp.getDisplayReturnTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightReturnDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayReturnArrivalTime() + "\n" + "Price per person: "
									+ temp.getDisplayPrice() + "\n" + "Ticket type: " + temp.getDisplayTicketType()
									+ "\n" + "Seat Preference: " + temp.getDisplaySeatPref() + "\n" + "Firstname: "
									+ temp.getDisplayFirstname() + "\n" + "Surname: " + temp.getDisplaySurname()
									+ "\n");

							btnDisplay.setEnabled(false);

						}
					} catch (StatusRuntimeException f) {

						f.printStackTrace();
					} // end catch
				} // end if option equals A or a

				// if user chose flight B print of tickets with necessary deatil
				else if (option.equals("B") || option.equals("b")) {
					DisplayRequest displayRequest = DisplayRequest.newBuilder().setDisplayRequest(depart)
							.setDisplayRequest(depart_date).setDisplayRequest(depart_time2)
							.setDisplayRequest(depart_duration2).setDisplayRequest(arrival_time2)
							.setDisplayRequest(randomNumber2).setDisplayRequest(arrival).setDisplayRequest(arrival_date)
							.setDisplayRequest(return_time2).setDisplayRequest(return_duration2)
							.setDisplayRequest(return_arrival_time2).setDisplayRequest(str_passengers)
							.setDisplayRequest(price2).setDisplayRequest(ticket).setDisplayRequest(seat)
							.setDisplayRequest(fname).setDisplayRequest(sname).build();

					try {
						Iterator<DisplayResponse> displayResponse = blockingStub1.flightDisplay(displayRequest);
						System.out.println("\nDisplaying finalized booking");
						display_ta.append("Displaying finalized ticket bookings for each passenger.");

						while (displayResponse.hasNext()) {
							DisplayResponse temp = displayResponse.next();
							display_ta.append("\nDestination: " + temp.getDisplayDestination() + "\n"
									+ "Departure date: " + temp.getDisplayDepartureDate() + "\n" + "Departure time: "
									+ temp.getDisplayDepartureTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayArrivalTime() + "\n" + "Flight ID: " + temp.getDisplayFlightId()
									+ "\n" + "Return location: " + temp.getDisplayReturnLocation() + "\n"
									+ "Return date: " + temp.getDisplayReturnDate() + "\n" + "Return time: "
									+ temp.getDisplayReturnTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightReturnDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayReturnArrivalTime() + "\n" + "Price per person: "
									+ temp.getDisplayPrice() + "\n" + "Ticket type: " + temp.getDisplayTicketType()
									+ "\n" + "Seat Preference: " + temp.getDisplaySeatPref() + "\n" + "Firstname: "
									+ temp.getDisplayFirstname() + "\n" + "Surname: " + temp.getDisplaySurname()
									+ "\n");

							System.out.println("\nDestination: " + temp.getDisplayDestination() + "\n"
									+ "Departure date: " + temp.getDisplayDepartureDate() + "\n" + "Departure time: "
									+ temp.getDisplayDepartureTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayArrivalTime() + "\n" + "Flight ID: " + temp.getDisplayFlightId()
									+ "\n" + "Return location: " + temp.getDisplayReturnLocation() + "\n"
									+ "Return date: " + temp.getDisplayReturnDate() + "\n" + "Return time: "
									+ temp.getDisplayReturnTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightReturnDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayReturnArrivalTime() + "\n" + "Price per person: "
									+ temp.getDisplayPrice() + "\n" + "Ticket type: " + temp.getDisplayTicketType()
									+ "\n" + "Seat Preference: " + temp.getDisplaySeatPref() + "\n" + "Firstname: "
									+ temp.getDisplayFirstname() + "\n" + "Surname: " + temp.getDisplaySurname()
									+ "\n");

							btnDisplay.setEnabled(false);
						}
					} catch (StatusRuntimeException f) {

						f.printStackTrace();
					} // end catch
				} // end if option equals B or b

				// if user chose flight C print of tickets with necessary deatil
				else if (option.equals("C") || option.equals("c")) {
					DisplayRequest displayRequest = DisplayRequest.newBuilder().setDisplayRequest(depart)
							.setDisplayRequest(depart_date).setDisplayRequest(depart_time3)
							.setDisplayRequest(depart_duration3).setDisplayRequest(arrival_time3)
							.setDisplayRequest(randomNumber3).setDisplayRequest(arrival).setDisplayRequest(arrival_date)
							.setDisplayRequest(return_time3).setDisplayRequest(return_duration3)
							.setDisplayRequest(return_arrival_time3).setDisplayRequest(str_passengers)
							.setDisplayRequest(price3).setDisplayRequest(ticket).setDisplayRequest(seat)
							.setDisplayRequest(fname).setDisplayRequest(sname).build();

					try {
						Iterator<DisplayResponse> displayResponse = blockingStub1.flightDisplay(displayRequest);
						System.out.println("\nDisplaying finalized booking");
						display_ta.append("Displaying finalized ticket bookings for each passenger.");

						while (displayResponse.hasNext()) {
							DisplayResponse temp = displayResponse.next();
							display_ta.append("\nDestination: " + temp.getDisplayDestination() + "\n"
									+ "Departure date: " + temp.getDisplayDepartureDate() + "\n" + "Departure time: "
									+ temp.getDisplayDepartureTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayArrivalTime() + "\n" + "Flight ID: " + temp.getDisplayFlightId()
									+ "\n" + "Return location: " + temp.getDisplayReturnLocation() + "\n"
									+ "Return date: " + temp.getDisplayReturnDate() + "\n" + "Return time: "
									+ temp.getDisplayReturnTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightReturnDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayReturnArrivalTime() + "\n" + "Price per person: "
									+ temp.getDisplayPrice() + "\n" + "Ticket type: " + temp.getDisplayTicketType()
									+ "\n" + "Seat Preference: " + temp.getDisplaySeatPref() + "\n" + "Firstname: "
									+ temp.getDisplayFirstname() + "\n" + "Surname: " + temp.getDisplaySurname()
									+ "\n");

							System.out.println("\nDestination: " + temp.getDisplayDestination() + "\n"
									+ "Departure date: " + temp.getDisplayDepartureDate() + "\n" + "Departure time: "
									+ temp.getDisplayDepartureTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayArrivalTime() + "\n" + "Flight ID: " + temp.getDisplayFlightId()
									+ "\n" + "Return location: " + temp.getDisplayReturnLocation() + "\n"
									+ "Return date: " + temp.getDisplayReturnDate() + "\n" + "Return time: "
									+ temp.getDisplayReturnTime() + "\n" + "Flight duration: "
									+ temp.getDisplayFlightReturnDuration() + "\n" + "Arrival time: "
									+ temp.getDisplayReturnArrivalTime() + "\n" + "Price per person: "
									+ temp.getDisplayPrice() + "\n" + "Ticket type: " + temp.getDisplayTicketType()
									+ "\n" + "Seat Preference: " + temp.getDisplaySeatPref() + "\n" + "Firstname: "
									+ temp.getDisplayFirstname() + "\n" + "Surname: " + temp.getDisplaySurname()
									+ "\n");

							btnDisplay.setEnabled(false);
						}
					} catch (StatusRuntimeException f) {

						f.printStackTrace();
					} // end catch
				} // end if option equals C or c
			}// end action performed

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

		hotel_list_ta = new JTextArea(5, 15);
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

		hotel_booking_ta = new JTextArea(5, 15);
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

				String hotel_booking_temp = hotel_booking_tf.getText();
				if (!(hotel_booking_temp.isEmpty())) {
					if (hotel_booking.size() == 0) {
						if (hotel_booking_temp.equals("Marriot International")
								|| hotel_booking_temp.equals("marriot international")
								|| hotel_booking_temp.equals("Hilton Hotel")
								|| hotel_booking_temp.equals("hilton hotel")
								|| hotel_booking_temp.equals("Wyndham Hotel & Resort")
								|| hotel_booking_temp.equals("wyndham hotel & resort")
								|| hotel_booking_temp.equals("Best Western Hotel")
								|| hotel_booking_temp.equals("best western hotel")) {
							hotel_booking.add(hotel_booking_temp);
						} else {
							JOptionPane.showMessageDialog(jFrame, "Error, enter one of the four hotels listed");
						}

					} else if (hotel_booking.size() == 1) {
						if (hotel_booking_temp.equals("Single") || hotel_booking_temp.equals("single")
								|| hotel_booking_temp.equals("Double") || hotel_booking_temp.equals("double")) {
							hotel_booking.add(hotel_booking_temp);
						} else {
							JOptionPane.showMessageDialog(jFrame, "Error, enter single or double room");
						}
					} else {
						hotel_booking.add(hotel_booking_temp);
					}

					hotel_booking_tf.setText("");
				} else {
					JOptionPane.showMessageDialog(jFrame, "Error, field cannot be empty");
				}

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
						btnHotelBooking.setEnabled(false);

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

		amenities_ta = new JTextArea(4, 15);
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

				if (!(breakfast.isEmpty()) && (!(gym.isEmpty()))) {
					if (breakfast.equals("yes") && gym.equals("no") || breakfast.equals("no") && gym.equals("yes")
							|| (breakfast.equals("yes") && gym.equals("yes")
									|| (breakfast.equals("no") && gym.equals("no")))
							|| (breakfast.equals("Yes") && gym.equals("No")
									|| breakfast.equals("No") && gym.equals("Yes")
									|| (breakfast.equals("Yes") && gym.equals("Yes")
											|| (breakfast.equals("No") && gym.equals("No"))))) {
						HotelAmenitiesRequest AmenitiesReq = HotelAmenitiesRequest.newBuilder().setBreakfast(breakfast)
								.setGym(gym).build();
						HotelAmenitiesResponse AmenitiesRes = blockingStub2.hotelAmenities(AmenitiesReq);

						System.out.println("\nAmenties booking has been complete");
						System.out.println("Breakfast included: " + AmenitiesRes.getBreakfast() + "\nGym included: "
								+ AmenitiesRes.getGym());
						amenities_ta.append("Breakfast included: " + AmenitiesRes.getBreakfast() + "\nGym included: "
								+ AmenitiesRes.getGym());

						btnAmenities.setEnabled(false);
					} else {

						System.out.println("Error yes or no only");
						JOptionPane.showMessageDialog(jFrame,
								"Error, yes or no options only, both should start with a capital letter or not");

					}

				} else {
					JOptionPane.showMessageDialog(jFrame, "Error, field(s) must not be empty");
				}

			}// end btnAmenities action performed

		});// end btnAmenities action listener

		// show the frame border
		jFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	}

}
// end FlightGUI class
