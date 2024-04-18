package com.manojlearnings.TLCRoomBooking.DTO;

public class RoomDto {

	private int roomID;

	private String roomType;

	private String description;

	private int capacity;

	private double pricepernight;

	private boolean isAvailable;

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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
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

	public RoomDto(String roomType, String description, int capacity, double pricepernight, boolean isAvailable) {
		super();

		this.roomType = roomType;
		this.description = description;
		this.capacity = capacity;
		this.pricepernight = pricepernight;
		this.isAvailable = isAvailable;
	}

	public RoomDto() {
		super();

	}

}
