package com.manojlearnings.TLCRoomBooking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.manojlearnings.TLCRoomBooking.DTO.RoomDto;
import com.manojlearnings.TLCRoomBooking.Entity.Room;
import com.manojlearnings.TLCRoomBooking.Repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
    private RoomRepository roomRepository;
	
	

	public List<Room> findAvailableRooms() {
        return roomRepository.findAll();
    }
	
	
	public Optional<Room> getRoomById(int roomId) {
	        return roomRepository.findById(roomId);
	    }
	
	public boolean addRoom(Room room) {
        room.setAvailable(true); 
        roomRepository.save(room);
        return true;
	}
	
	
	// Method to update room details
    public boolean updateRoomDetails(RoomDto roomDto) {
        // Retrieve the room by its ID from the database
        Optional<Room> optionalRoom = roomRepository.findById(roomDto.getRoomID());
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            // Update the room details
            room.setRoomType(roomDto.getRoomType());
            room.setDescription(roomDto.getDescription());
            room.setCapacity(roomDto.getCapacity());
            room.setPricepernight(roomDto.getPricepernight());
            room.setAvailable(roomDto.isAvailable());
            // Save the updated room
            roomRepository.save(room);
            return true; // Room details updated successfully
        } else {
            return false; // Room not found with the provided ID
        }
    }
	
    
    public boolean deleteRoom(int roomId) {
        // Check if the room exists
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            // Delete the room
            roomRepository.delete(room);
            return true;
        } else {
            return false;
        }
    }
    
	
}
