package com.manojlearnings.TLCRoomBooking.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.manojlearnings.TLCRoomBooking.Entity.ForgotPasswordToken;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ForgotPasswordService {

	@Autowired
	JavaMailSender javaMailSender;
	
    private final int MINUTES = 10;
	
	public String generateToken() {
		return UUID.randomUUID().toString();
	}
	
	public LocalDateTime expireTimeRange() {
		return LocalDateTime.now().plusMinutes(MINUTES);
	}
	
	public void sendEmail(String to, String subject, String emailLink) throws MessagingException, UnsupportedEncodingException {

	    MimeMessage message = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);

	    String emailContent = "<p>Dear User,</p>"
	            + "<p>We received a request to reset your password for your TLC Hotels account.</p>"
	            + "<p>If you made this request, please click the button below to reset your password:</p>"
	            + "<p><a href=\"" + emailLink + "\">Reset My Password</a></p>"
	            + "<p>If you did not request a password reset, you can safely ignore this email.</p>"
	            + "<p>Thank you,</p>"
	            + "<p>The TLC Hotels Support Team</p>";

	    helper.setText(emailContent, true);
	    helper.setFrom("badthug68@gmail.com", "TLC Hotels Support");
	    helper.setSubject(subject);
	    helper.setTo(to);
	    javaMailSender.send(message);
	}

	
	public boolean isExpired (ForgotPasswordToken forgotPasswordToken) {
		return LocalDateTime.now().isAfter(forgotPasswordToken.getExpireTime());
	}
	
	public String checkValidity (ForgotPasswordToken forgotPasswordToken, Model model) {
		
		if (forgotPasswordToken == null) {
			model.addAttribute("error", "Invalid Link");
			return "error-page";
		}
		
		else if (forgotPasswordToken.isUsed()) {
			model.addAttribute("error", "Link Already Used!");
			return "error-page";
		}
		
		else if (isExpired(forgotPasswordToken)) {
			model.addAttribute("error", "Link is expired");
			return "error-page";
		}
		else {
			return "reset-password";
		}
		
		
	}
	
}
