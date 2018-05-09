package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*")
public class SampleController {

	
	@GetMapping("/adminPage")
	public void viewAdmin() {
		
	}
	
	@GetMapping("/puserPage")
	public void viewPuser() {
		
	}
	
	@GetMapping("/userPage")
	public void viewUser() {
		
	}
	
	@GetMapping("/guestPage")
	public void viewGuest() {
		
	}	
}
