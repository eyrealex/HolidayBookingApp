package com.alexeyre.grpc.flight;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceBlockingStub;
import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class FlightGUI {

	private static FlightServiceBlockingStub blockingStub;
	private static FlightServiceStub asyncStub;
	private static ServiceInfo serviceInfo;
	private JFrame jFrame;
	private JTextArea jTextArea, jTextArea2;
	private JTextField textNumber1, textNumber2, textNumber3, textNumber4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightGUI flightGUI = new FlightGUI();
					flightGUI.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public FlightGUI() {

		String flight_service_type = "_flight._tcp.local.";
		discoverFlightService(flight_service_type);

		String host = serviceInfo.getHostAddresses()[0];
		int port = serviceInfo.getPort();

		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub = FlightServiceGrpc.newBlockingStub(channel);

		asyncStub = FlightServiceGrpc.newStub(channel);

		initialize();

	}

	private void discoverFlightService(String flight_service_type) {

		try {

			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(flight_service_type, new ServiceListener() {

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Flight Service added: " + event.getInfo());

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Flight Service removed: " + event.getInfo());

				}

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Flight Service resolved: " + event.getInfo());

					serviceInfo = event.getInfo();
					int port = serviceInfo.getPort();

					System.out.println("Resolving " + flight_service_type);
					System.out.println("\t Port " + port);
					System.out.println("\t Type " + event.getType());
					System.out.println("\t Name " + event.getName());
					System.out.println("\t Desc/Props " + serviceInfo.getNiceTextString());
					System.out.println("\t Host " + serviceInfo.getHostAddresses()[0]);

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

	private void initialize() {

		jFrame = new JFrame();
		jFrame.setTitle("Flight Client - Service Controller");
		jFrame.setBounds(400, 400, 500, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BoxLayout bl = new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS);
		jFrame.getContentPane().setLayout(bl);

		// ******************************************
		// SETTING THE JPANEL FOR THE FIRST SERVICE
		// ******************************************

		JPanel jPanel1 = new JPanel();
		jFrame.getContentPane().add(jPanel1);
		jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel JLabel1 = new JLabel("Get list of Flight Locations: ");
		jPanel1.add(JLabel1);

		JButton btnList = new JButton("Destinations");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ListRequest req = ListRequest.newBuilder().setLocation1("London").setLocation2("Paris")
						.setLocation3("Berlin").setLocation4("Madrid").build();

				try {
					Iterator<ListResponse> response = blockingStub.flightList(req);
					System.out.println("\nGetting list of holdiay destinations from the GUI");

					while (response.hasNext()) {
						ListResponse temp = response.next();
						jTextArea.append("\nLocation: " + temp.getResult());

						System.out.println("Location: " + temp.getResult());
					}

				} catch (StatusRuntimeException f) {
					f.printStackTrace();
				}

			}
		});

		jPanel1.add(btnList);

		jTextArea = new JTextArea(3, 20);
		jTextArea.setLineWrap(true);
		jTextArea.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(jTextArea);
		// textResponse.setSize(new Dimension(15, 30));
		jPanel1.add(scrollPane);

		// ******************************************
		// SETTING THE JPANEL FOR THE SECOND SERVICE
		// ******************************************

		JPanel jPanel2 = new JPanel();
		jFrame.getContentPane().add(jPanel2);
		jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel JLabel2 = new JLabel("Departure Destination: ");
		jPanel2.add(JLabel2);
		textNumber1 = new JTextField();
		jPanel2.add(textNumber1);
		textNumber1.setColumns(10);

		JLabel JLabel3 = new JLabel("Departure Date: ");
		jPanel2.add(JLabel3);
		textNumber2 = new JTextField();
		jPanel2.add(textNumber2);
		textNumber2.setColumns(10);

		JLabel JLabel4 = new JLabel("Arrival Destination: ");
		jPanel2.add(JLabel4);
		textNumber3 = new JTextField();
		jPanel2.add(textNumber3);
		textNumber3.setColumns(10);

		JLabel JLabel5 = new JLabel("Arrival Date: ");
		jPanel2.add(JLabel5);
		textNumber4 = new JTextField();
		jPanel2.add(textNumber4);
		textNumber4.setColumns(10);

		JButton btnBooking = new JButton("Book");
		btnBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String val1 = textNumber1.getText();
				String val2 = textNumber2.getText();
				String val3 = textNumber3.getText();
				String val4 = textNumber4.getText();

				StreamObserver<BookingResponse> response = new StreamObserver<BookingResponse>() {

					@Override
					public void onNext(BookingResponse value) {
						jTextArea2.append("TESTING" + value.getDepart() + value.getDepartDate() + value.getArrival() + value.getArrivalDate());
						System.out.println("TESTING" + value.getDepart() + value.getDepartDate() + value.getArrival() + value.getArrivalDate());

					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {
						System.out.print("Booking Completed!!");

					}

				};

				StreamObserver<BookingRequest> request = asyncStub.flightBooking(response);
				try {

					request.onNext(BookingRequest.newBuilder().setValue(val1).build());
					Thread.sleep(500);

					request.onNext(BookingRequest.newBuilder().setValue(val2).build());
					Thread.sleep(500);

					request.onNext(BookingRequest.newBuilder().setValue(val3).build());
					Thread.sleep(500);

					request.onNext(BookingRequest.newBuilder().setValue(val4).build());
					Thread.sleep(500);

					// Mark the end of requests
					request.onCompleted();

					Thread.sleep(3000);

				} catch (RuntimeException t) {
					t.printStackTrace();
				} catch (InterruptedException t) {
					t.printStackTrace();
				}
			}
		});

		jPanel2.add(btnBooking);

		jTextArea2 = new JTextArea(3, 20);
		jTextArea2.setLineWrap(true);
		jTextArea2.setWrapStyleWord(true);

		JScrollPane scrollPane2 = new JScrollPane(jTextArea2);
		jPanel2.add(scrollPane2);

		JPanel jPanel3 = new JPanel();
		jFrame.getContentPane().add(jPanel3);

		JPanel jPanel4 = new JPanel();
		jFrame.getContentPane().add(jPanel4);

	}

}// end FlightGUI class
