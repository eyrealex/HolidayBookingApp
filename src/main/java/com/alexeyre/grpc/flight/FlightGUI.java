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

import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceBlockingStub;
import com.alexeyre.grpc.flight.FlightServiceGrpc.FlightServiceStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class FlightGUI {

	private static FlightServiceBlockingStub blockingStub;
	private static FlightServiceStub asyncStub;
	private static ServiceInfo serviceInfo;
	private JFrame jFrame;
	private JTextArea jTextArea;

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
		jFrame.setBounds(100, 100, 500, 300);
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

	}

}// end FlightGUI class
