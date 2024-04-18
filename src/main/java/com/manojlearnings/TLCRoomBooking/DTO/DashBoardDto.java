package com.manojlearnings.TLCRoomBooking.DTO;

public class DashBoardDto {

	private long bookedRoomsCount;
	private long availableRoomsCount;
	private long totalRooms;
	private double totalRevenue;
	private long totalUsersCount;
	private long totalAdminsCount;

	public DashBoardDto(long bookedRoomsCount, long availableRoomsCount, double totalRevenue, long totalUsersCount,
			long totalAdminsCount, long totalRooms) {
		super();
		this.bookedRoomsCount = bookedRoomsCount;
		this.availableRoomsCount = availableRoomsCount;
		this.totalRevenue = totalRevenue;
		this.totalUsersCount = totalUsersCount;
		this.totalAdminsCount = totalAdminsCount;
		this.totalRooms = totalRooms;

	}

	public long getBookedRoomsCount() {
		return bookedRoomsCount;
	}

	public void setBookedRoomsCount(long bookedRoomsCount) {
		this.bookedRoomsCount = bookedRoomsCount;
	}

	public long getAvailableRoomsCount() {
		return availableRoomsCount;
	}

	public void setAvailableRoomsCount(long availableRoomsCount) {
		this.availableRoomsCount = availableRoomsCount;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public long getTotalUsersCount() {
		return totalUsersCount;
	}

	public void setTotalUsersCount(long totalUsersCount) {
		this.totalUsersCount = totalUsersCount;
	}

	public long getTotalAdminsCount() {
		return totalAdminsCount;
	}

	public void setTotalAdminsCount(long totalAdminsCount) {
		this.totalAdminsCount = totalAdminsCount;
	}

	public DashBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(long totalRooms) {
		this.totalRooms = totalRooms;
	}

}