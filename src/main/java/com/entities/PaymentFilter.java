package com.entities;

public class PaymentFilter {
	private String type;
	private String date;
	private Integer mileage; // usa Integer para permitir null

	public PaymentFilter() {
		super();
	}

	public String getType() {
		return type;
	}

	public String getDate() {
		return date;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	//VALIDACIONES
	
	public boolean hasType() { 
		return type != null && !(type.isEmpty()); //TIENE QUE SER !NULL Y !EMPTY
	}

	public boolean hasDate() {
		return date != null && !(date.isEmpty()); //TIENE QUE SER !NULL Y !EMPTY
	}

	public boolean hasMileage() { //TIENE QUE SER !NULL
		return mileage != null; 
	}
}