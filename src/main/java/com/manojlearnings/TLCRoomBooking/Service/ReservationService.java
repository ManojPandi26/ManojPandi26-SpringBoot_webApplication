package com.manojlearnings.TLCRoomBooking.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.manojlearnings.TLCRoomBooking.Entity.Reservation;
import com.manojlearnings.TLCRoomBooking.Entity.Room;
import com.manojlearnings.TLCRoomBooking.Entity.User;
import com.manojlearnings.TLCRoomBooking.Forms.BookingForm;
import com.manojlearnings.TLCRoomBooking.Repository.ReservationRepository;
import com.manojlearnings.TLCRoomBooking.Repository.RoomRepository;
import com.manojlearnings.TLCRoomBooking.Repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class ReservationService {

	@Autowired
    private RoomRepository roomRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ReservationRepository reservationRepository;

	
    public boolean createReservation(BookingForm bookingForm,String username){
    	
    	Optional<Room> optionalroom=roomRepository.findById(bookingForm.getRoomId());
    	if(optionalroom.isEmpty()) {
    		return false;
    	}
    	
    	Room rooms=optionalroom.get();
    	if(rooms.isAvailable()) {
    	rooms.setAvailable(false);
    	roomRepository.save(rooms);
    	
    	//create new reservation for user logged in
    	Reservation reservations = new Reservation();
		reservations.setNoOfPersons(bookingForm.getNoOfPersons());
		reservations.setStayDays(calculateNumberOfDays(bookingForm.getCheckInDate(), bookingForm.getCheckOutDate()));
		reservations.setCheckInDate(bookingForm.getCheckInDate());
		reservations.setCheckOutDate(bookingForm.getCheckOutDate());
		reservations.setTotalAmount(bookingForm.getTotalAmount());
		Room room = roomRepository.findById(bookingForm.getRoomId()).orElse(null);
		reservations.setRoom(room);
		User user = userRepository.findByUsername(username);
		if(user==null) {
			return false;
		}
		reservations.setCustomer(user);
		
		reservationRepository.save(reservations);
    	
    	return true;
    	}
    	return false;
    }
    
    public int calculateTotalAmount(int roomId, int noOfPersons, Date checkInDate, Date checkOutDate) {
        // Find the room by roomId
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room1 = optionalRoom.get();
            double pricePerNight = room1.getPricepernight();
            
            // Calculate the number of nights between check-in and check-out dates
            long numberOfNights = ChronoUnit.DAYS.between(checkInDate.toInstant(), checkOutDate.toInstant());
            
            // Ensure that number of nights is positive
            if (numberOfNights > 0) {
                // Calculate total amount by multiplying number of nights, price per night, and number of persons
                int totalAmount = (int) (numberOfNights * pricePerNight * noOfPersons);
                return totalAmount;
            } else {
                
                return 0;
            }
        } else {
            
            return 0; // or throw an exception
        }
    }
    

    
    public long calculateNumberOfDays(Date checkInDate, Date checkOutDate) {
        return ChronoUnit.DAYS.between(checkInDate.toInstant(), checkOutDate.toInstant());
    }
    
    
    
   @Autowired
   public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
  }
    public List<Reservation> getReservationsForUser(User user) {
        return reservationRepository.findByCustomer(user);
   }
    
    
    public List<Reservation> getRecentBookings() {
        return reservationRepository.findFirst4ByOrderByReservationIDDesc();
    }
    

    @Transactional
    public boolean cancelReservation(int reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            // Update related tables if necessary
            Room room = reservation.getRoom();
            room.setAvailable(true); // Assuming you have a setter method
            roomRepository.save(room);

            // Delete the reservation
            reservationRepository.delete(reservation);
            return true; // Cancellation successful
        } else {
            return false; // Reservation not found
        }
    }

	 
}
