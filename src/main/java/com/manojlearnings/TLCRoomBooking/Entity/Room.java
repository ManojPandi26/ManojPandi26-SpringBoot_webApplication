package com.manojlearnings.TLCRoomBooking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rooms")
public class Room {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomID")
    private int roomID;

    public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Column(name = "RoomType")
    private String roomType;

    @Column(name = "Description")
    private String description;

    @Column(name = "Capacity")
    private int capacity;
    
    @Column(name = "PricePerNight")
    private double pricepernight;

    @Column(name = "IsAvailable")
    private boolean isAvailable;

	public Room() {
		super();
		
	}

	public Room(int roomID, int capacity,String roomType, String description, double pricepernight, boolean isAvailable) {
		super();
		this.roomID = roomID;
		this.roomType = roomType;
		this.description = description;
		this.pricepernight = pricepernight;
		this.isAvailable = isAvailable;
		this.capacity=capacity;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
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

	public double getPricepernight() {
		return pricepernight;
	}

	public void setPricepernight(double pricepernight) {
		this.pricepernight = pricepernight;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	
}
