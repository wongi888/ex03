package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {

	@Setter(onMethod = @__({ @Autowired }))
	private MemberService service;
	
	@GetMapping("/customLogin")
	public String loginInput(String error, String logout, Model model ) {
		
		log.info("error: "+ error);
		log.info("logout: "+ logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
		
		return "/member/customLogin";
	}
	
	@GetMapping("/customLogout")
	public void logout() {
		
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
		
	}	
	

}
