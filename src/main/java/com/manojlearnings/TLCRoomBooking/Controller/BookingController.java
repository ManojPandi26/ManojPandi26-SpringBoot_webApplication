package com.manojlearnings.TLCRoomBooking.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manojlearnings.TLCRoomBooking.Entity.Room;
import com.manojlearnings.TLCRoomBooking.Forms.BookingForm;
import com.manojlearnings.TLCRoomBooking.Repository.RoomRepository;
import com.manojlearnings.TLCRoomBooking.Service.ReservationService;
import jakarta.transaction.Transactional;

@Controller
public class BookingController {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ReservationService reservationService;


	@PostMapping("/confirmBooking")
	public String validatecapacity(@ModelAttribute("BookingForm") BookingForm bookingForm, Model model) {
		Room room1 = roomRepository.findById(bookingForm.getRoomId()).orElse(null);

		// checking server side validation for capacity
		if (room1 != null && bookingForm.getNoOfPersons() > room1.getCapacity()) {
			model.addAttribute("capacityerror", true);
			model.addAttribute("roomId", bookingForm.getRoomId());

			return "redirect:/bookRoom?roomId=" + bookingForm.getRoomId() + "&errorcapacity"; // Redirect with error
																								// parameter
		}
		// Calculating Total amount
		int totalAmount = reservationService.calculateTotalAmount(bookingForm.getRoomId(), bookingForm.getNoOfPersons(),
		bookingForm.getCheckInDate(), bookingForm.getCheckOutDate());
		model.addAttribute("roomId", bookingForm.getRoomId());
		model.addAttribute("noOfPersons", bookingForm.getNoOfPersons());
		model.addAttribute("numberOfDays",
				reservationService.calculateNumberOfDays(bookingForm.getCheckInDate(), bookingForm.getCheckOutDate()));
		model.addAttribute("totalAmount", totalAmount);

		bookingForm.setTotalAmount((double) totalAmount);
		model.addAttribute("bookingForm", bookingForm);
		return "ConfirmationPage";
	}

	@Transactional
	@PostMapping("/confirmation")
	public String confirmReservation(@ModelAttribute("BookingForm") BookingForm bookingForm, Principal principal,
			Model model, RedirectAttributes redirectAtt) {

		// Save the reservation
		boolean reservationcreated = reservationService.createReservation(bookingForm, principal.getName());

		if (reservationcreated) {
			redirectAtt.addFlashAttribute("message", "Reservation Created Successfully!..");
			redirectAtt.addFlashAttribute("alertClass", "success-msg");
			return "redirect:/Reservations";
		} else {
			redirectAtt.addFlashAttribute("message", "Reservation Failed. Please try again!..");
			redirectAtt.addFlashAttribute("alertClass", "error-msg");
			return "redirect:/Reservations";
		}

	}

}
