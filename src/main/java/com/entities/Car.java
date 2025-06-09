package com.entities;


public class Car {
	private int id_car;
	private int year;
	private String brand;
	private String model;
	private String plate;
	
	public Car () {
		super();
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public int getId_car() {
		return id_car;
	}

	public void setId_car(int id_car) {
		this.id_car = id_car;
	}
	
	public static String getHeader() {
		return String.format("%-10s %-25s %-25s %-15s", "ID", "MARCA", "MODELO", "MATRÍCULA");
	}

	@Override
	public String toString() {
		return String.format("%-10s %-25s %-25s %-15s", this.id_car, this.brand, this.model, this.plate);
	}
	
}
