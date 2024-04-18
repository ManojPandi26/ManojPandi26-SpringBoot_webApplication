package com.manojlearnings.TLCRoomBooking.Controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manojlearnings.TLCRoomBooking.Entity.ForgotPasswordToken;
import com.manojlearnings.TLCRoomBooking.Entity.User;
import com.manojlearnings.TLCRoomBooking.Repository.ForgotPasswordRepository;
import com.manojlearnings.TLCRoomBooking.Service.ForgotPasswordService;
import com.manojlearnings.TLCRoomBooking.Service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotPasswordController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@Autowired
	ForgotPasswordRepository forgotPasswordRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/password-request")
	public String passwordRequest() {
		//Return view
		return "password-request";
	}
	
	@PostMapping("/password-request")
	public String savePasswordRequest(@RequestParam("email") String email, Model model) {
		//Validate by Email
		User user = userService.findByEmail(email);
		if (user == null) {
			model.addAttribute("error", "This Email is not registered");
			return "password-request";
		}
		
		ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
		forgotPasswordToken.setExpireTime(forgotPasswordService.expireTimeRange());
		forgotPasswordToken.setToken(forgotPasswordService.generateToken());
		forgotPasswordToken.setUser(user);
		forgotPasswordToken.setUsed(false);
		
		forgotPasswordRepository.save(forgotPasswordToken);
		
		String emailLink = "http://localhost:8080/TLCHotels/reset-password?token=" + forgotPasswordToken.getToken();
		
		try {
			forgotPasswordService.sendEmail(user.getEmail(), "Password Reset Link", emailLink);
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error While Sending email");
			return "password-request";
		}
		
		
		return "redirect:/password-request?success";
	}
	
	@GetMapping("/reset-password")
	public String resetPassword(@Param(value="token") String token, Model model, HttpSession session) {
		
		session.setAttribute("token", token);
		ForgotPasswordToken forgotPasswordToken = forgotPasswordRepository.findByToken(token);
		return forgotPasswordService.checkValidity(forgotPasswordToken, model);
		
	}
	
	@PostMapping("/reset-password")
	public String saveResetPassword(HttpServletRequest request, HttpSession session, Model model) {
		//Getting password and token from Session Attributes
		String password = request.getParameter("password");
		String token = (String)session.getAttribute("token");
		
		//Find the respective Token in database
		ForgotPasswordToken forgotPasswordToken = forgotPasswordRepository.findByToken(token);
		User user = forgotPasswordToken.getUser();
		user.setPassword(passwordEncoder.encode(password));
		forgotPasswordToken.setUsed(true);
		userService.save(user);
		forgotPasswordRepository.save(forgotPasswordToken);
		
		model.addAttribute("message", "You have successfuly reset your password");
		
		return "reset-password";
	}
	
}

