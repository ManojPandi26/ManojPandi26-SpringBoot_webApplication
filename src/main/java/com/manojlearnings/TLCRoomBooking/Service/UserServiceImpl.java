package com.manojlearnings.TLCRoomBooking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manojlearnings.TLCRoomBooking.DTO.UserDto;
import com.manojlearnings.TLCRoomBooking.Entity.Reservation;
import com.manojlearnings.TLCRoomBooking.Entity.User;
import com.manojlearnings.TLCRoomBooking.Repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	private UserRepository userRepository;
	
	

	public UserServiceImpl(UserRepository userRepository) {
	
		this.userRepository = userRepository;
	}

	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),userDto.getFullname(),userDto.getEmail());
		return userRepository.save(user);
	}
	
	@Override
	public User save(User user) {
	
		return userRepository.save(user);
	}
	
	@Override
	public List<User> getRecentReservations() {
        return userRepository.findTop2ByOrderByIdDesc();
    } 
	
	
}
