package com.waracle.util;

import com.waracle.dto.CakeDTO;
import com.waracle.entity.Cake;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MappingUtil {

	public static Cake toEntity(CakeDTO cakeDTO) {
		return Cake.builder()
				.cakeId(cakeDTO.getCakeId())
				.title(cakeDTO.getTitle())
				.desc(cakeDTO.getDesc())
				.image(cakeDTO.getImage())
				.build();
	}

	public static CakeDTO toDTO(Cake cake) {
		return CakeDTO.builder()
				.cakeId(cake.getCakeId())
				.title(cake.getTitle())
				.desc(cake.getDesc())
				.image(cake.getImage())
				.build();
	}
}
