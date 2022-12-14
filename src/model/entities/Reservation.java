package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut){
		if (checkIn.isAfter(checkOut)) {
			throw new DomainException("Check-Out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}
	
	public int duration() {
		long diff = checkOut.toEpochDay() - checkIn.toEpochDay();
		return (int) diff;
	}
	
	public void updateDates(LocalDate checkIn, LocalDate checkOut) {
		LocalDate now = LocalDate.now();
		if (now.isAfter(checkOut) || now.isAfter(checkIn)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if (checkIn.isAfter(checkOut)) {
			throw new DomainException("Check-Out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room " + 
				roomNumber +
				", check-in: " +
				checkIn.format(fmt) +
				", check-out: " +
				checkOut.format(fmt) +
				", " +
				duration() +
				" nights";
	}

}
