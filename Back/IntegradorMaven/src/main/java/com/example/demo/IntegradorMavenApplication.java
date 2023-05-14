package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.text.SimpleDateFormat;
import java.util.Date;
@SpringBootApplication
public class IntegradorMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegradorMavenApplication.class, args);

		//calculating the days
		Date checkIn = new Date(2023,05,10);
		Date checkOut = new Date(2023,05,13);

		long startTime = checkIn.getTime() ;
		long endTime = checkOut.getTime();
		long daysSince = (long) Math.floor(startTime / (1000*60*60*24)); // convert to days, so that time changes do not affect
		long daysUntil = (long) Math.floor(endTime / (1000*60*60*24)); // convert to days, so that time changes do not affect
		long totalDays = daysUntil-daysSince;

		//calculating the total price according to the days
		double totalPrice = (totalDays * 30000.0);
		System.out.println("los dias totales son: " + totalDays + ", el precio total es: "+  totalPrice);
	}
}
