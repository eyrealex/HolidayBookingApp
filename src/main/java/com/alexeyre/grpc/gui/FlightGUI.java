package com.alexeyre.grpc.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.alexeyre.grpc.flight.BookingRequest;
import com.alexeyre.grpc.flight.BookingResponse;
import com.alexeyre.grpc.flight.FlightServiceGrpc;
import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceBlockingStub;
import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceStub;
import com.alexeyre.grpc.flight.ListRequest;
import com.alexeyre.grpc.flight.ListResponse;
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

public class FlightGUI {

	private static FlightServiceBlockingStub blockingStub1;
	private static FlightServiceStub asyncStub1;
	private static HotelServiceBlockingStub blockingStub2;
	private static HotelServiceStub asyncStub2;
	private static RentalServiceBlockingStub blockingStub3;
	private static RentalServiceStub asyncStub3;
	private static ServiceInfo serviceInfo;
	private JFrame jFrame;
	private JTextArea jTextArea, jTextArea2;

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

		getList();
		flightBooking();

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

	private void getList() {

		jFrame = new JFrame();
		jFrame.setTitle("Flight Client - Service Controller");
		jFrame.setBounds(400, 400, 500, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BoxLayout bl = new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS);
		jFrame.getContentPane().setLayout(bl);

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
					Iterator<ListResponse> response = blockingStub1.flightList(req);
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

	}

	private void flightBooking() {

		JPanel jPanel2 = new JPanel();
		jFrame.getContentPane().add(jPanel2);
		jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JTextField jTextField;
		JLabel JLabel2 = new JLabel("Location/Date: ");
		jPanel2.add(JLabel2);
		jTextField = new JTextField();
		jPanel2.add(jTextField);
		jTextField.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		ArrayList<String> list = new ArrayList();

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {
						System.out.println("\nCompleted stream");

					}

				};

				String value = jTextField.getText();

				if (list.add(value)) {
					jTextField.setText("");
				}

				StreamObserver<BookingRequest> requestObserver = asyncStub1.flightBooking(responseObserver);

				if (list.size() > 3) {

					try {

						for (int i = 0; i < list.size(); i++) {
							requestObserver.onNext(BookingRequest.newBuilder().setValue(list.get(i)).build());
							Thread.sleep(500);
						}

						// Mark the end of requests
						requestObserver.onCompleted();
						btnSubmit.setEnabled(false);

						Thread.sleep(3000);

					} catch (RuntimeException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		jPanel2.add(btnSubmit);

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
