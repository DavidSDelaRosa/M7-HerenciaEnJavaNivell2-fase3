package com.vehicles.project;

import java.util.*;
import java.util.Scanner;

import com.vehicles.project.exceptions.InvalidWheelDiameterException;
import com.vehicles.project.exceptions.MissingLicenseException;
import com.vehicles.project.people.Driver;
import com.vehicles.project.people.License;
import com.vehicles.project.people.VehicleHolder;
import com.vehicles.project.vehicles.Bike;
import com.vehicles.project.vehicles.Car;
import com.vehicles.project.vehicles.Truck;
import com.vehicles.project.vehicles.Wheel;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception{
		
		System.out.println("Datos para la identificación: ");
		System.out.println("Introduce tu nombre: ");
		String name = sc.nextLine();
		
		System.out.println("Introduce tu apellido: ");
		String surName = sc.nextLine();
		
		System.out.println("Año de nacimiento: ");
		int year = sc.nextInt();
		sc.nextLine();
		System.out.println("Mes del año de nacimiento: ");
		int month = sc.nextInt();
		sc.nextLine();
		System.out.println("Día del mes:");
		int day = sc.nextInt();
		sc.nextLine();
		GregorianCalendar birthDate = new GregorianCalendar(year, month, day);
		
		
		System.out.println("De acuerdo " + name + ", ahora necesitamos los datos de tu licencia de conducir. Introduce el ID: ");
		int idLicense = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Ahora el tipo de licencia: A, B ó C");
		String typeLicense = sc.nextLine().toUpperCase();
		
		System.out.println(typeLicense);
		
		System.out.println("Nombre completo de la licencia: ");
		String nameLicense = sc.nextLine();
		
		License license = new License(idLicense, typeLicense, nameLicense);
		
		System.out.println("Tienes seguro?");
		boolean hasInsurance;
		String rsp1 = sc.nextLine();
		hasInsurance = rsp1.toLowerCase().equals("si")? true : false;
		
		boolean hasGarage;
		System.out.println("Tienes garage?");
		String rsp2 = sc.nextLine();
		hasGarage = rsp2.toLowerCase().equals("si") ? true : false;
		
		VehicleHolder titular = new VehicleHolder(name, surName, birthDate, license, hasInsurance, hasGarage);
		
		System.out.println(titular.toString());
		
		System.out.println("¿Qué quieres crear? \n1. Un coche\n2. Una moto\n3. Un camion");
		int eleccion = sc.nextInt();
		sc.nextLine();
		
		if(eleccion==1) {
			
			if(titular.getLicense().getType().equals("B")) {
				
				System.out.println("Introduce la matrícula del coche: ");
				String matriculaCoche = sc.nextLine();
				
				System.out.println("Introduce ahora la marca: ");
				String marcaCoche = sc.nextLine();
				
				System.out.println("Especifica el color del coche: ");
				String colorCoche = sc.nextLine();
				
				Car car = new Car(matriculaCoche, marcaCoche, colorCoche);
				
				System.out.println("Ahora vamos a ponerle las ruedas delanteras...");
				Wheel ruedaDelantera = pedirRueda();
				
				System.out.println("Vamos con las traseras...");
				Wheel ruedaTrasera = pedirRueda();
				
				List<Wheel> ruedasDelanteras = new ArrayList<>();
				ruedasDelanteras.add(ruedaDelantera);
				ruedasDelanteras.add(ruedaDelantera);
				
				List<Wheel> ruedasTraseras = new ArrayList<>();
				ruedasTraseras.add(ruedaTrasera);
				ruedasTraseras.add(ruedaTrasera);
				
				car.addWheels(ruedasDelanteras, ruedasTraseras);
				
				System.out.println(car.toString());
				
				if(isAlsoDriver()) {
					System.out.println("Perfecto, pues ya estaría todo! Muchas gracias");
				}else {
					Driver driver = createDriverForLicense("B");
					System.out.println("Muchas gracias " + driver.getName() + ", estos son tus datos: ");
					System.out.println(driver.toString());
					System.out.println("Ya puedes conducir tu coche");
				}
			}else {
				throw new MissingLicenseException();
			}
			
		}else if(eleccion==2) {
			
			if(titular.getLicense().getType().equals("A")) {
				
				System.out.println("Introduce la matrícula de la moto: ");
				String matriculaMoto = sc.nextLine();
				
				System.out.println("Introduce la marca: ");
				String marcaMoto = sc.nextLine();
				
				System.out.println("Color de la moto: ");
				String colorMoto = sc.nextLine();
				
				Bike bike = new Bike(matriculaMoto, marcaMoto, colorMoto);
				
				System.out.println("Ahora vamos a ponerle las ruedas delanteras...");
				Wheel ruedaDelantera = pedirRueda();
				
				System.out.println("Vamos con las traseras...");
				Wheel ruedaTrasera = pedirRueda();
				
				bike.addWheels(ruedaDelantera, ruedaTrasera);
				
				System.out.println(bike.toString());
				
				if(isAlsoDriver()) {
					System.out.println("Perfecto, pues ya estaría todo! Muchas gracias");
				}else {
					Driver driver = createDriverForLicense("A");
					System.out.println("Muchas gracias " + driver.getName() + ", estos son tus datos: ");
					System.out.println(driver.toString());
					System.out.println("Ya puedes conducir tu moto");
				}
				
			}else {
				throw new MissingLicenseException();
			}
			
		}else if(eleccion==3){
			
			if(titular.getLicense().getType().equals("C")) {
				
				System.out.println("Introduce la matrícula del camión: ");
				String matriculaCamion = sc.nextLine();
				
				System.out.println("Introduce ahora la marca: ");
				String marcaCamion = sc.nextLine();
				
				System.out.println("Especifica el color del camión: ");
				String colorCamion = sc.nextLine();
				
				Truck truck = new Truck(matriculaCamion, marcaCamion, colorCamion);
				
				System.out.println("Cuántas ruedas tiene el camión: 4, 6, 8, 10 ó 12 ?");
				
				int ruedasTotalesCamion = sc.nextInt();
				sc.nextLine();
				
				List<Wheel> ruedasCamion = new ArrayList<>();
				
				System.out.println("Necesitaré la marca y diámetro de las ruedas... ");
				
				for(int i=0;i<ruedasTotalesCamion/2;i++) {
					
					System.out.println("Pidiendo par de ruedas nº " + (i+1));
					Wheel rueda = pedirRueda();
					ruedasCamion.add(rueda);
					ruedasCamion.add(rueda);
				}
				
				truck.addWheels(ruedasCamion);
				
				System.out.println("Número correcto de ruedas: " + truck.suitableNumberOfWheels());
				
				System.out.println(truck.toString());
				
				if(isAlsoDriver()) {
					System.out.println("Perfecto, pues ya estaría todo! Muchas gracias");
				}else {
					Driver driver = createDriverForLicense("C");
					System.out.println("Muchas gracias " + driver.getName() + ", estos son tus datos: ");
					System.out.println(driver.toString());
					System.out.println("Ya puedes conducir tu camión");
				}
			}else {
				
				throw new MissingLicenseException();
			}
			
		}else {
			System.err.println("Sólo hay tred opciones válidas: [1 -> Coche | 2 -> Moto | 3 -> Camion]");
		}
		
		sc.close();
	}
	
	public static Wheel pedirRueda() throws InvalidWheelDiameterException {
		
		Wheel rueda;
		
		System.out.println("De qué marca quieres la rueda: ");
		String marcaRueda = sc.nextLine();
		
		System.out.println("Diámetro de la rueda trasera: ");
		double diametroRueda = sc.nextDouble();
		sc.nextLine();
		
		rueda = new Wheel(marcaRueda, diametroRueda);
		
		return rueda;
	}
	
	public static boolean isAlsoDriver() {
		
		boolean isDriver;
		System.out.println("Además del titular del vehículo, eres el conductor del mismo? ");
		String rsp3 = sc.nextLine();
		
		isDriver = (rsp3.toLowerCase().equals("si")) ? true : false;
		
		return isDriver;
	}
	
	public static Driver createDriverForLicense(String typeOfLicense) throws Exception {
		
		System.out.println("Datos para la identificación: ");
		System.out.println("Introduce tu nombre: ");
		String nameDriver = sc.nextLine();
		
		System.out.println("Introduce tu apellido: ");
		String surNameDriver = sc.nextLine();
		
		System.out.println("Año de nacimiento: ");
		int yearDriver = sc.nextInt();
		sc.nextLine();
		System.out.println("Mes del año de nacimiento: ");
		int monthDriver = sc.nextInt();
		sc.nextLine();
		System.out.println("Día del mes:");
		int dayDriver = sc.nextInt();
		sc.nextLine();
		GregorianCalendar birthDateDriver = new GregorianCalendar(yearDriver, monthDriver, dayDriver);
		
		
		System.out.println("De acuerdo " + nameDriver + ", ahora necesitamos los datos de tu licencia de conducir. Introduce el ID: ");
		int idLicenseDriver = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Necesitas una licencia de tipo " + typeOfLicense + " para conducir este vehículo. Escribe tu tipo de licencia: ");
		String typeLicenseDriver = sc.nextLine().toUpperCase();
		
		while(!typeLicenseDriver.toUpperCase().equals(typeOfLicense.toUpperCase())) {
			System.out.println("Tu licencia: " + typeLicenseDriver + " no coincide con: " + typeOfLicense + "\nIntroduce el tipo de licencia: ");
			typeLicenseDriver = sc.nextLine();
		}
		
		System.out.println("Nombre completo de la licencia: ");
		String nameLicenseDriver = sc.nextLine();
		
		License licenseDriver = new License(idLicenseDriver, typeLicenseDriver, nameLicenseDriver);
		
		Driver driver = new Driver(nameDriver, surNameDriver, birthDateDriver, licenseDriver);
		
		return driver;
	}
}
