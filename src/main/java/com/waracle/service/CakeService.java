package com.waracle.service;

import java.util.List;

import com.waracle.dto.CakeDTO;

public interface CakeService {

	List<CakeDTO> getAllCakes();

	CakeDTO createCake(CakeDTO cakeDTO);

}
