package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate now = LocalDate.now();
		
		System.out.print("Room number: ");
		int number = sc.nextInt();
		System.out.print("Check-in date (dd/MM/yyyy) "); 
		LocalDate checkIn = LocalDate.parse(sc.next(), fmt);
		System.out.print("Check-out date (dd/MM/yyyy) ");
		LocalDate checkOut = LocalDate.parse(sc.next(), fmt);
		
		if (!checkOut.isAfter(checkIn)) {
			System.out.println("Error in reservation: Check-Out date must be after check-in date");
		} else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy) "); 
			LocalDate upCheckIn = LocalDate.parse(sc.next(), fmt);
			System.out.print("Check-out date (dd/MM/yyyy) ");
			LocalDate upCheckOut = LocalDate.parse(sc.next(), fmt);
			
			if (!upCheckOut.isAfter(upCheckIn)) {
				System.out.println("Error in reservation: Check-Out date must be after check-in date");
			} else {
				if (now.isAfter(upCheckOut) || now.isAfter(upCheckIn)) {
					System.out.println("Reservation dates for update must be future dates");
				} else {
					reservation.updateDates(upCheckIn, upCheckOut);
					System.out.println("Reservation: " + reservation);
				}
			}
		}
		
		
		sc.close();
	}

}
