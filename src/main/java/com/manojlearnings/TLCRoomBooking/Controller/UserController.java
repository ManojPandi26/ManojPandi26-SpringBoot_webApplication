package com.manojlearnings.TLCRoomBooking.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.manojlearnings.TLCRoomBooking.DTO.UserDto;
import com.manojlearnings.TLCRoomBooking.Entity.User;
import com.manojlearnings.TLCRoomBooking.Service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;

	private UserService userService;

	public UserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping("/home")
	public String home3(Model model, Principal principal) {
		UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail", userdetails);
		return "home3";
	}

	@GetMapping("/login")
	public String login(Model model, UserDto userDto, HttpServletResponse response) {
		model.addAttribute("user", userDto);

		// make secure...
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0"); 

		return "login";
	}

	@PostMapping("/login")
	public String loginSuccess(HttpSession session, Principal principal) {
		UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
		if (userdetails != null) {
			// Set user ID in session
			session.setAttribute("user", userdetails);

		}
		return "redirect:/home3";
	}

	@GetMapping("/register")
	public String register(Model model, UserDto userDto) {

		model.addAttribute("user", userDto);
		return "register";
	}

	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
		User userByUsername = userService.findByUsername(userDto.getUsername());
		User userByEmail = userService.findByEmail(userDto.getEmail());

		if (userByUsername != null) {
			model.addAttribute("userexist", userByUsername);
			return "register";
		}

		if (userByEmail != null) {
			model.addAttribute("emailexist", userByEmail);
			return "register";
		}

		userService.save(userDto);
		return "redirect:/register?success";
	}

}
