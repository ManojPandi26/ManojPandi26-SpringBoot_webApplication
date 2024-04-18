package com.manojlearnings.TLCRoomBooking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.manojlearnings.TLCRoomBooking.Entity.Room;
import com.manojlearnings.TLCRoomBooking.Forms.BookingForm;
import com.manojlearnings.TLCRoomBooking.Repository.RoomRepository;
import com.manojlearnings.TLCRoomBooking.Service.RoomService;



@Controller

public class RoomController {

	@Autowired
	private RoomService roomService;
	
	
	@Autowired
	private RoomRepository roomRepository;
	
	@GetMapping("/Rooms")
	public String showAvailablerooms(Model model) {
		//show all Rooms
		List<Room> availableRoomslist = roomService.findAvailableRooms();
	    model.addAttribute("availableRooms", availableRoomslist);
		return "Rooms";
	}

	@GetMapping("/bookRoom")
    public String bookRoom(@RequestParam("roomId") int roomId, Model model) {
		//Find By Roomid
		Room room1=roomRepository.findById(roomId).orElse(null);
		model.addAttribute("room", room1);
        model.addAttribute("roomId", roomId);
        //creating BookingForm to Transfer Objects
        BookingForm bookingForm=new BookingForm();
        bookingForm.setRoomId(roomId);
        model.addAttribute("bookingForm",bookingForm );	
        return "Booking";
}
}