package com.alexeyre.grpc.flight;

public class FlightHelper {

	private String depart, departDate, arrival, arrivalDate, departTime, flightDuration, arrivalTime, flightId,
			returnDate, returnTime, returnDuration, returnArrivalTime, price;

	public FlightHelper() {
	}

	public FlightHelper(String depart, String departDate, String arrival, String arrivalDate, String departTime,
			String flightDuration, String arrivalTime, String flightId, String returnDate, String returnTime,
			String returnDuration, String returnArrivalTime, String price) {

		this.depart = depart;
		this.departDate = departDate;
		this.arrival = arrival;
		this.arrivalDate = arrivalDate;
		this.departTime = departTime;
		this.flightDuration = flightDuration;
		this.arrivalTime = arrivalTime;
		this.flightId = flightId;
		this.returnDate = returnDate;
		this.returnTime = returnTime;
		this.returnDuration = returnDuration;
		this.returnArrivalTime = returnArrivalTime;
		this.price = price;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public void setFlightDuration(String flightDuration) {
		this.flightDuration = flightDuration;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public void setReturnDuration(String returnDuration) {
		this.returnDuration = returnDuration;
	}

	public void setReturnArrivalTime(String returnArrivalTime) {
		this.returnArrivalTime = returnArrivalTime;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
	

}
