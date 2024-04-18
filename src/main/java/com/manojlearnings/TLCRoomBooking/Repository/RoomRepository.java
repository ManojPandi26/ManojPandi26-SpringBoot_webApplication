package com.manojlearnings.TLCRoomBooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manojlearnings.TLCRoomBooking.Entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{

	List<Room> findByIsAvailable(boolean isAvailable);
	
	 @Query("SELECT COUNT(r) FROM Room r WHERE r.isAvailable = true")
	long countAvailableRooms();
	 
	 @Query("SELECT COUNT(r) FROM Room r WHERE r.isAvailable = false")
    long countBookedRooms();
	 
	long count();
}
