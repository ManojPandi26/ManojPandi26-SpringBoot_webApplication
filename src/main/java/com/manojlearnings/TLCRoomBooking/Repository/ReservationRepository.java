package com.manojlearnings.TLCRoomBooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manojlearnings.TLCRoomBooking.Entity.Reservation;
import com.manojlearnings.TLCRoomBooking.Entity.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

	List<Reservation> findByCustomer(User user);

	@Query("SELECT COALESCE(SUM(r.totalAmount), 0) FROM Reservation r")
    double sumTotalAmount();
	
	List<Reservation> findFirst4ByOrderByReservationIDDesc();
}
