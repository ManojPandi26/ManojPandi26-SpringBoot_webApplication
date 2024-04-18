package com.manojlearnings.TLCRoomBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manojlearnings.TLCRoomBooking.Entity.ForgotPasswordToken;


@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken, Long> {

	ForgotPasswordToken findByToken(String token);
	
}
