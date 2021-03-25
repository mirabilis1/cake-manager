package com.waracle.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.dto.CakeDTO;
import com.waracle.service.CakeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CakeController {

	private final CakeService cakeService;
	private final ObjectMapper mapper;

	@GetMapping(value = "/cakes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CakeDTO>> getAllCakes() {
		List<CakeDTO> list = cakeService.getAllCakes();
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PostMapping(value = "/cakes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CakeDTO> createCake(@RequestBody CakeDTO cakeDTO) {

		return new ResponseEntity<>(cakeService.createCake(cakeDTO), HttpStatus.CREATED);
	}

}
