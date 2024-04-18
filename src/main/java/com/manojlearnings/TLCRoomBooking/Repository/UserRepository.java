package com.manojlearnings.TLCRoomBooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manojlearnings.TLCRoomBooking.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername (String username);
	User findByEmail(String email);
	User findById(long userid);
	long count();
	List<User> findTop2ByOrderByIdDesc();;
}
