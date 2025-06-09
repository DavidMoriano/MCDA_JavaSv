package com.entities;

public class Payment {
	private int id;
	private String type;
	private String date;
	private String userUuid;
	private float amount;
	private String description;
	private int mileage;
	private int id_car;

	public Payment() {
		super();
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId_car() {
		return id_car;
	}

	public void setId_car(int id_car) {
		this.id_car = id_car;
	}

	public static String getHeader() {
	    return String.format(
	        "%-5s %-20s %-12s %-12s %-10s %-30s %-10s",
	        "ID", "Tipo", "Fecha", "id_Coche", "Cantidad", "Descripción", "Kilometraje"
	    );
	}

	@Override
	public String toString() {
	    return String.format(
	        "%-5d %-20s %-12s %-12d %-10.2f %-30s %-10d",
	        this.id, (this.type.length() > 17 ? (this.type.substring(0, 17)+"...") : (this.type)), this.date, this.id_car,
	        this.amount, (this.description.length() > 27 ? (this.description.substring(0, 27)+"...") : (this.description))
	        , this.mileage
	    );
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}


}
