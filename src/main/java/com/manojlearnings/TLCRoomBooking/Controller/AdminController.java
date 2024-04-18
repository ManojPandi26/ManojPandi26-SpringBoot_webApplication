package com.manojlearnings.TLCRoomBooking.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manojlearnings.TLCRoomBooking.DTO.DashBoardDto;
import com.manojlearnings.TLCRoomBooking.DTO.RoomDto;
import com.manojlearnings.TLCRoomBooking.Entity.Reservation;
import com.manojlearnings.TLCRoomBooking.Entity.Room;
import com.manojlearnings.TLCRoomBooking.Entity.User;
import com.manojlearnings.TLCRoomBooking.Service.DashboardService;
import com.manojlearnings.TLCRoomBooking.Service.ReservationService;
import com.manojlearnings.TLCRoomBooking.Service.RoomService;
import com.manojlearnings.TLCRoomBooking.Service.UserService;
import com.manojlearnings.TLCRoomBooking.Service.UserServiceImpl;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AdminController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private ReservationService reservationService;
	
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping("/admin")
	public String getMethodName(Model model) {
		DashBoardDto dashboardMetrics = dashboardService.getDashboardMetrics();
		model.addAttribute("dashboardMetrics", dashboardMetrics);
		
		
		// Find the recent bookings (last 3)
        List<Reservation> recentBookings = reservationService.getRecentBookings();
        
        // Add the recent bookings to the model
        model.addAttribute("recentBookings", recentBookings);
		
        //Find the Recent Reservations(last 2)
        List<User>recentReservation=userService.getRecentReservations();
        
        model.addAttribute("recentReservation", recentReservation);
        
		return "Admin-dashboard";
	}
	
	
	
	
	
	@GetMapping("/add")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "AddRoom";
    }

    @PostMapping("/add")
    public String addRoom(@ModelAttribute("room") Room room,RedirectAttributes redirectAtt) {
    	
    	//Add Room
    	if(roomService.addRoom(room)) {
			 redirectAtt.addFlashAttribute("message", "Room Added Successfully!..");
			    redirectAtt.addFlashAttribute("alertClass", "success-msg");
		}
		else {
			 redirectAtt.addFlashAttribute("message", "Can't Add Room. Please try again!..");
			    redirectAtt.addFlashAttribute("alertClass", "error-msg");
		
		}
    	return "redirect:/add";
    }
    
    @GetMapping("/updaterooms")
    public String getAllRooms(Model model) {
    	//Retrieve All Rooms
        List<Room> rooms = roomService.findAvailableRooms();
        model.addAttribute("rooms", rooms);
        return "UpdateRooms";
    }

    @GetMapping("/update-room/{roomId}")
    public String showUpdateRoomForm(@PathVariable int roomId, Model model) {
        // Retrieve the room by its ID
        Optional<Room> optionalRoom = roomService.getRoomById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            // Convert the Room entity to RoomDto
            RoomDto roomDto = convertToDto(room);
            model.addAttribute("roomDto", roomDto);
            return "UpdateRoomForm";
        } else {
            // Room not found with the provided ID
            // Redirect to an error page or display an error message
            return "error-page";
        }
    }

    
    @PostMapping("/update-room")
    public String updateRoom(@ModelAttribute RoomDto roomDto, RedirectAttributes redirectAttributes) {
        //Updating the Rooms Details
    	if (roomService.updateRoomDetails(roomDto)) {
            redirectAttributes.addFlashAttribute("message", "Room details updated successfully!");
            redirectAttributes.addFlashAttribute("alertClass", "success-msg");
        } else {
            redirectAttributes.addFlashAttribute("message", "Room not found with the provided ID!");
            redirectAttributes.addFlashAttribute("alertClass", "error-msg");
        }
        // Redirect back to the rooms page
        return "redirect:/updaterooms"; 
    }
    
    
    
    @GetMapping("/delete-room/{roomId}")
    public String deleteRoom(@PathVariable("roomId") int roomId,RedirectAttributes redirectAttributes) {
    	//Delete Rooms
    	if (roomService.deleteRoom(roomId)) {
            redirectAttributes.addFlashAttribute("message", "Room Deleted successfully!");
            redirectAttributes.addFlashAttribute("alertClass", "success-msg");
        } else {
            redirectAttributes.addFlashAttribute("message", "Room not found with the provided ID!");
            redirectAttributes.addFlashAttribute("alertClass", "error-msg");
        }
        return "redirect:/updaterooms"; // Redirect back to the rooms page // Redirect to the Rooms page after deletion
    }

 
    // Utility method to convert Room entity to RoomDto
    private RoomDto convertToDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomID(room.getRoomID());
        roomDto.setRoomType(room.getRoomType());
        roomDto.setDescription(room.getDescription());
        roomDto.setCapacity(room.getCapacity());
        roomDto.setPricepernight(room.getPricepernight());
        roomDto.setAvailable(room.isAvailable());
        return roomDto;
    }
}

