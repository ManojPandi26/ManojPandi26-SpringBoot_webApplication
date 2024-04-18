package com.manojlearnings.TLCRoomBooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manojlearnings.TLCRoomBooking.DTO.DashBoardDto;
import com.manojlearnings.TLCRoomBooking.Repository.ReservationRepository;
import com.manojlearnings.TLCRoomBooking.Repository.RoomRepository;
import com.manojlearnings.TLCRoomBooking.Repository.UserRepository;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public DashBoardDto getDashboardMetrics() {
        long totalUsersCount = userRepository.count();
        long totalAdminsCount = userRepository.count();
        long totalRooms = roomRepository.count();
        long bookedRoomsCount = roomRepository.countBookedRooms();
        long availableRoomsCount = roomRepository.countAvailableRooms();
        double totalRevenue = reservationRepository.sumTotalAmount();
        
        return new DashBoardDto(bookedRoomsCount, availableRoomsCount, totalRevenue, totalUsersCount, totalAdminsCount, totalRooms);
    }
	
}