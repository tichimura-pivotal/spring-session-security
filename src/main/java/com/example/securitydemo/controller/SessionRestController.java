package com.example.securitydemo.controller;

import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionRestController {

    /**
     * Simple Session controller which will return session ID backed by Spring Session API
     * @param session
     * @return session ID
     */
	
	@GetMapping("/")
	String sid(HttpSession session) {
		
		String encoded = Base64.getEncoder()
                .encodeToString(session.getId().getBytes());
		
		return "base64 encoding is " + encoded + "<br /> session-id is " + session.getId();
		
	}
}
