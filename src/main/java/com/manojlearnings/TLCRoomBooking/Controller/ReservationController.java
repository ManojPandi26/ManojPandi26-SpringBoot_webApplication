package com.manojlearnings.TLCRoomBooking.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manojlearnings.TLCRoomBooking.Entity.Reservation;
import com.manojlearnings.TLCRoomBooking.Entity.User;
import com.manojlearnings.TLCRoomBooking.Repository.UserRepository;
import com.manojlearnings.TLCRoomBooking.Service.ReservationService;



@Controller
public class ReservationController {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/Reservations")
	public String getAllReservations(Model model,Principal principal) {
		User user=userRepo.findByUsername(principal.getName());
		
		List<Reservation> reservations = reservationService.getReservationsForUser(user);
	    
       
        model.addAttribute("reservations", reservations);
		return "Reservation";
	}
	
	
	@GetMapping("/Reservations/{reservationId}")
    public String cancelReservation(@PathVariable("reservationId") int reservationId, Model model,RedirectAttributes redirectAtt) {
        boolean isCancelled = reservationService.cancelReservation(reservationId);
        if (isCancelled) {
        	redirectAtt.addFlashAttribute("cancelmessage", "Reservation Cancelled Successfully!..");
		    redirectAtt.addFlashAttribute("alertClass", "success-msg");
        } else {
        	redirectAtt.addFlashAttribute("cancelmessage", "Cancellation Failed. Please try again!..");
		    redirectAtt.addFlashAttribute("alertClass", "error-msg");
        }
        return "redirect:/Reservations";
    }
	
}
