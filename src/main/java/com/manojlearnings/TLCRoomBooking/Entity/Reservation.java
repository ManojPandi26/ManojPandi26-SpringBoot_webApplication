package com.manojlearnings.TLCRoomBooking.Entity;

import java.util.Date;


import jakarta.persistence.*;

@Entity
@Table(name="Reservations")
public class Reservation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservationID")
    private int reservationID;

	@ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "RoomID", referencedColumnName = "RoomID")
    private Room room;

    @Column(name = "CheckInDate")
    private Date checkInDate;

    @Column(name = "CheckOutDate")
    private Date checkOutDate;

    @Column(name = "TotalAmount")
    private double totalAmount;
   
    @Column(name="no_of_persons")
    private int noOfPersons;
    
    @Column(name="stay_days")
    private long stayDays;

	public Reservation() {
		super();
	}
	
	

	public int getNoOfPersons() {
		return noOfPersons;
	}



	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}



	public long getStayDays() {
		return stayDays;
	}



	public void setStayDays(long stayDays) {
		this.stayDays = stayDays;
	}


	public Reservation(User customer, Room room, Date checkInDate, Date checkOutDate, double totalAmount,
			int noOfPersons, long stayDays) {
		super();
		this.customer = customer;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalAmount = totalAmount;
		this.noOfPersons = noOfPersons;
		this.stayDays = stayDays;
	}



	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
