package com.alexeyre.grpc.flight;

public class PassengerHelper {

	private String tType, sPref, fName, sName;

	public PassengerHelper() {
	}

	public PassengerHelper(String tType, String sPref, String fName, String sName) {
		super();
		this.tType = tType;
		this.sPref = sPref;
		this.fName = fName;
		this.sName = sName;
	}

	public void setTType(String tType) {
		this.tType = tType;
	}

	public void setSPref(String sPref) {
		this.sPref = sPref;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	public String getTType() {
		return tType;
	}

	public String getSPref() {
		return sPref;
	}

	public String getFName() {
		return fName;
	}

	public String getSName() {
		return sName;
	}
	
	
	
	

}
