package com.manojlearnings.TLCRoomBooking.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manojlearnings.TLCRoomBooking.Forms.MessageForm;

import org.springframework.util.StringUtils;


@Controller
public class ContactController {



    @GetMapping("/Contact")
    public String showContactForm(Model model) {
        model.addAttribute("MessageForm", new MessageForm());
        return "Contact";
    }

    @PostMapping("/contactusMsg")
    public String submitMessageForm(@ModelAttribute("MessageForm") MessageForm MessageForm, Model model,RedirectAttributes redirectAttribute) {
        if (isValidMessageForm(MessageForm)) {
          
        	redirectAttribute.addFlashAttribute("message", "Your message has been sent successfully!");
        	redirectAttribute.addFlashAttribute("alertClass", "success-msg");
        	return "redirect:/Contact";
        } else {
            
        	redirectAttribute.addFlashAttribute ("message", "Please fill out all fields correctly.");
        	redirectAttribute.addFlashAttribute("alertClass", "error-msg");
        	return "redirect:/Contact";
        }
        
    }

    private boolean isValidMessageForm(MessageForm MessageForm) {
        return !StringUtils.isEmpty(MessageForm.getName()) && 
               !StringUtils.isEmpty(MessageForm.getEmail()) && 
               !StringUtils.isEmpty(MessageForm.getMessage());
    }
}

