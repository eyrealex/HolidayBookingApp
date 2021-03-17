package com.alexeyre.grpc.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;

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
import javax.swing.SwingConstants;

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
import com.alexeyre.grpc.hotel.HotelServiceGrpc;
import com.alexeyre.grpc.hotel.HotelServiceGrpc.HotelServiceBlockingStub;
import com.alexeyre.grpc.hotel.HotelServiceGrpc.HotelServiceStub;
import com.alexeyre.grpc.rental.RentalServiceGrpc;
import com.alexeyre.grpc.rental.RentalServiceGrpc.RentalServiceBlockingStub;
import com.alexeyre.grpc.rental.RentalServiceGrpc.RentalServiceStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.awt.Color;

public class serviceGUI {

	private static FlightServiceBlockingStub blockingStub1;
	private static FlightServiceStub asyncStub1;
	private static HotelServiceBlockingStub blockingStub2;
	private static HotelServiceStub asyncStub2;
	private static RentalServiceBlockingStub blockingStub3;
	private static RentalServiceStub asyncStub3;
	private static ServiceInfo serviceInfo;
	private JFrame jFrame;
	private JTabbedPane tabbedPane;
	private JTextArea jTextArea, jTextArea2, jTextArea3, jTextArea4;
	private JTextField jTextField1, jTextField2, jTextField3, jTextField4;
	private static int position = 0;

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
		int port = serviceInfo.getPort();

		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub1 = FlightServiceGrpc.newBlockingStub(channel);
		asyncStub1 = FlightServiceGrpc.newStub(channel);
		blockingStub2 = HotelServiceGrpc.newBlockingStub(channel);
		asyncStub2 = HotelServiceGrpc.newStub(channel);
		blockingStub3 = RentalServiceGrpc.newBlockingStub(channel);
		asyncStub3 = RentalServiceGrpc.newStub(channel);

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
		jFrame.setBounds(400, 400, 600, 550);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void ServiceController() {

		// Create JTabbedPane object
		JTabbedPane tabbedPane = new JTabbedPane();

		// Initialize the tabs
		JPanel tabPanel1 = new JPanel(false);
		JPanel tabPanel2 = new JPanel(false);
		JPanel tabPanel3 = new JPanel(false);

		// panels for tab 2
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
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
		p4.setAlignmentX(0.6f);
		p4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p5.setAlignmentX(0.6f);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p6.setAlignmentX(0.6f);
		p6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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

		jTextArea = new JTextArea(3, 15);
		list_flights_panel.add(jTextArea);
		jTextArea.setLineWrap(true);
		jTextArea.setWrapStyleWord(true);

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
						jTextArea.append("Location: " + temp.getResult() + "\n");

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
		jTextField1 = new JTextField();
		booking_flight_panel.add(jTextField1);
		jTextField1.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		booking_flight_panel.add(btnSubmit);

		jTextArea2 = new JTextArea(3, 15);
		booking_flight_panel.add(jTextArea2);
		jTextArea2.setLineWrap(true);
		jTextArea2.setWrapStyleWord(true);

		// Initializing array list to be used for setting values
		ArrayList<String> list = new ArrayList();

		// creating action when btnSubmit is clicked
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Iterating through the stream of responses for flightBooking
				StreamObserver<BookingResponse> responseObserver = new StreamObserver<BookingResponse>() {

					@Override
					public void onNext(BookingResponse value) {
						jTextArea2.append("Depart: " + value.getDepart());
						System.out.println("\nDepart: " + value.getDepart());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						jTextArea2.append("\nDepart date: " + value.getDepartDate());
						System.out.println("Depart date: " + value.getDepartDate());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						jTextArea2.append("\nArrival: " + value.getArrival());
						System.out.println("Arrival: " + value.getArrival());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						jTextArea2.append("\nArrival date: " + value.getArrivalDate());
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

				String value = jTextField1.getText();
				if (list.add(value)) {
					jTextField1.setText("");
				} // end if for removing text from text fields

				// Iterating through the stream of requests for flightBooking
				StreamObserver<BookingRequest> requestObserver = asyncStub1.flightBooking(responseObserver);

				// if the list of requests is greater than 3 do ...
				if (list.size() > 3) {

					// loop through the list size and for each position set the value in the request
					// this prevents overriding values for each user input
					try {
						for (int i = 0; i < list.size(); i++) {
							requestObserver.onNext(BookingRequest.newBuilder().setValue(list.get(i)).build());
							Thread.sleep(500);
						} // end for loop

						// Mark the end of requests
						requestObserver.onCompleted();
						btnSubmit.setEnabled(false);

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
		jTextField2 = new JTextField();
		people_flight_panel.add(jTextField2);
		jTextField2.setColumns(10);

		JButton btnSubmit2 = new JButton("Submit");
		people_flight_panel.add(btnSubmit2);

		jTextArea3 = new JTextArea(3, 10);
		people_flight_panel.add(jTextArea3);
		jTextArea3.setLineWrap(true);
		jTextArea3.setWrapStyleWord(true);

		// creating action when btnSubmit is clicked
		btnSubmit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String input = jTextField2.getText();
				int num = 0;

				if (!input.matches("[\\-]?[1-8]+")) {
					jTextArea3.append("ERROR, 1-8 passengers per booking" + "\n");
					System.out.println("\nERROR, 1-8 passengers per booking");
					jTextField2.setText("");

				} else {
					num = Integer.parseInt(jTextField2.getText());
					position = num;
					PeopleRequest peopleReq = PeopleRequest.newBuilder().setPassengers(num).build();
					PeopleResponse peopleRes = blockingStub1.flightPeople(peopleReq);

					btnSubmit2.setEnabled(false);
					System.out.println("\nNumber of people booked:" + peopleRes.getPassengers());
					jTextArea3.append("\nNumber of people booked: " + peopleRes.getPassengers());
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
		jTextField3 = new JTextField();
		passengers_flight_panel.add(jTextField3);
		jTextField3.setColumns(10);

		passengers_flight_panel.add(JLabel5);
		jTextField4 = new JTextField();
		passengers_flight_panel.add(jTextField4);
		jTextField4.setColumns(10);

		JButton btnSubmit3 = new JButton("Submit");
		passengers_flight_panel.add(btnSubmit3);

		jTextArea4 = new JTextArea(3, 15);
		passengers_flight_panel.add(jTextArea4);
		jTextArea4.setLineWrap(true);
		jTextArea4.setWrapStyleWord(true);

		btnSubmit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StreamObserver<PassengerResponse> responseObserver = new StreamObserver<PassengerResponse>() {

					@Override
					public void onNext(PassengerResponse value) {
						System.out.println("Passenger seat preference: " + value.getSeat() + " and luggage taken: "
								+ value.getLuggage());

					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {

						System.out.println("\nBooking has now been complete");

					}

				};

				StreamObserver<PassengerRequest> requestObserver = asyncStub1.flightPassenger(responseObserver);

				ArrayList<String> list = new ArrayList();
				String seat = "";
				int luggage = 0;
				luggage = Integer.parseInt(jTextField4.getText());

				if (list.size() < position - 1) {
					seat = jTextField3.getText();
					list.add(seat);

					for (int i = 0; i < list.size(); i++) {
						requestObserver
								.onNext(PassengerRequest.newBuilder().setSeat(list.get(i)).setLuggage(luggage).build());

					}
					
					if(list.size() > position) {
						requestObserver.onCompleted();
						btnSubmit.setEnabled(false);
					}

					

				}
			}

		});

		// adding the sections to the second tab
		tabPanel2.add(p4);
		tabPanel2.add(p5);
		tabPanel2.add(p6);

		// adding the sections to the third tab
		tabPanel3.add(p7);
		tabPanel3.add(p8);
		tabPanel3.add(p9);

		// show the frame border
		jFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	}

}
// end FlightGUI class