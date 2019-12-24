package com.eatza.restaurantdiscoveryserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

	@GetMapping(value = "/getMessage/{message}")
	public String getData(@PathVariable String message) {
		return new StringBuilder("Hi").append(message).toString();
	}

}
