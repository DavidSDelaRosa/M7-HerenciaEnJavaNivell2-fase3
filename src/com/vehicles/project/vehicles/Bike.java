package com.vehicles.project.vehicles;

import com.vehicles.project.exceptions.InvalidBikeWheelsException;

public class Bike extends Vehicle {

	public Bike(String plate, String brand, String color) throws Exception {
		super(plate, brand, color);
	}
	
	public void addWheels(Wheel frontWheels, Wheel backWheels) throws InvalidBikeWheelsException{
		
		if(frontWheels.getDiameter()!=backWheels.getDiameter()) throw new InvalidBikeWheelsException();
		
		this.wheels.add(frontWheels);
		this.wheels.add(backWheels);
	}
	
	@Override
	public boolean suitableNumberOfWheels() {
		boolean corrrectNumber = false;
		
		if (wheels.size()==2) corrrectNumber = true;
		
		return corrrectNumber;
	}

	@Override
	public String toString() {
		return "Bike [plate=" + plate + ", brand=" + brand + ", color=" + color + ", wheels=" + wheels + "]";
	}

}
