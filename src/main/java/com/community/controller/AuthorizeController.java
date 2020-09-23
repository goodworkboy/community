package com.community.controller;

import org.springframework.stereotype.Controller;

@Controller
public class AuthorizeController {
	public String callback() {
		return "index";
	}
}
