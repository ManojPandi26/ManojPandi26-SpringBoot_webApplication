package com.manojlearnings.TLCRoomBooking.DTO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservationDto {

	private int noOfPersons;

	private String roomType;
	private String description;
	private long stayDays;

	private double totalAmount;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkInDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkOutDate;

	public int getNoOfPersons() {
		return noOfPersons;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getStayDays() {
		return stayDays;
	}

	public void setStayDays(long stayDays) {
		this.stayDays = stayDays;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

}
