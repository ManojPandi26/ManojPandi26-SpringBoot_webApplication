package com.manojlearnings.TLCRoomBooking.Service;

import java.util.List;

import com.manojlearnings.TLCRoomBooking.DTO.UserDto;
import com.manojlearnings.TLCRoomBooking.Entity.User;

public interface UserService {

	User findByUsername(String username);
	User save (UserDto userDto);
	User save(User user);
	User findByEmail(String email);
	List<User> getRecentReservations();
}
