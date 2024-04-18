package com.manojlearnings.TLCRoomBooking.Forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.manojlearnings.TLCRoomBooking.Entity.Room;
import com.manojlearnings.TLCRoomBooking.Entity.User;



public class BookingForm {

	private int roomId;
	private User customer;
	private Room rooms;
    public Room getRooms() {
		return rooms;
	}
	public void setRooms(Room rooms) {
		this.rooms = rooms;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	private int noOfPersons;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    
    private double totalAmount;
	public BookingForm(int roomId, int noOfPersons, Date checkInDate, Date checkOutDate) {
		super();
		this.roomId = roomId;
		this.noOfPersons = noOfPersons;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BookingForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getNoOfPersons() {
		return noOfPersons;
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
