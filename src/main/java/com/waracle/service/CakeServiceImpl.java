package com.waracle.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waracle.dto.CakeDTO;
import com.waracle.entity.Cake;
import com.waracle.repository.CakeRepository;
import com.waracle.util.MappingUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CakeServiceImpl implements CakeService {

	private final CakeRepository cakeRepository;

	@Override
	@Transactional(readOnly = true)
	public List<CakeDTO> getAllCakes() {

		return cakeRepository.findAll().stream().map(MappingUtil::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public CakeDTO createCake(CakeDTO cakeDTO) {
		Cake cake = cakeRepository.save(MappingUtil.toEntity(cakeDTO));

		return MappingUtil.toDTO(cake);
	}

}
