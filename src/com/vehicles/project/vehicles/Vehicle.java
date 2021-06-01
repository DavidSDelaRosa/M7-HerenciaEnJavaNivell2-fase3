package com.vehicles.project.vehicles;

import java.util.ArrayList;
import java.util.List;
import com.vehicles.project.exceptions.*;

public abstract class Vehicle {

	protected String plate;
	protected String brand;
	protected String color;
	protected List<Wheel> wheels = new ArrayList<Wheel>();

	public Vehicle(String plate, String brand, String color) throws InvalidPlateException {
		
		this.plate = plate;
		this.brand = brand;
		this.color = color;
		
		if(!validatePlate(plate)) throw new InvalidPlateException();
		
	}
	
	public boolean validatePlate(String plate) {
		
		boolean correctPlate = false;
		final int NUMBER_OF_DIGITS = 4;
		int digitsCounter=0;
		int lettersCounter=0;
		
		for(int i=0;i<plate.length();i++) {
			if(Character.isDigit(plate.charAt(i))) digitsCounter ++;
			if(Character.isLetter(plate.charAt(i))) lettersCounter++;
		}
		
		
		
		if(digitsCounter==NUMBER_OF_DIGITS && (lettersCounter==2 || lettersCounter==3)) correctPlate=true;
		
		return correctPlate;
	}
	
	public abstract boolean suitableNumberOfWheels();
}
