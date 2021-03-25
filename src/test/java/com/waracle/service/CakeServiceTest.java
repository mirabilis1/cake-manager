package com.waracle.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.waracle.BaseTestCase;
import com.waracle.dto.CakeDTO;
import com.waracle.entity.Cake;
import com.waracle.repository.CakeRepository;

@RunWith(MockitoJUnitRunner.class)
public class CakeServiceTest extends BaseTestCase {
	@Mock
	private CakeRepository cakeRepository;

	private CakeService cakeService;

	private List<Cake> persistedEntities;

	private Cake transientEntity;

	@Before
	public void setUp() {
		cakeService = new CakeServiceImpl(cakeRepository);

		persistedEntities = Arrays.asList(
				new Cake(1L, "Carrot Cake", "Carrot flavoured cake", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/carrot-cake-f1b3d0c.jpg"), 
				new Cake(2L, "Lemon Cheesecake", "Lemon based cheesecake", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/lemon-cheesecake-member-recipe-68bbc86.jpg"),
				new Cake(3L, "Red Velvet", "Red velvet cake is a classic chocolate layer cake with a striking reddish-brown sponge", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/red-velvet-cake-513716c.jpg"));

		transientEntity = Cake.builder()
				.title("Red Velvet")
				.desc("Red velvet cake is a classic chocolate layer cake with a striking reddish-brown sponge")
				.image("https://images.immediate.co.uk/production/volatile/sites/30/2020/08/red-velvet-cake-513716c.jpg")
				.build();
	}

	@Test
	public void getAllCakes() {
		given(cakeRepository.findAll()).willReturn(persistedEntities);

		List<CakeDTO> cakesFetched = cakeService.getAllCakes();
		assertThat(cakesFetched.size()).isEqualTo(3);
		assertThat(cakesFetched.containsAll(samples)).isTrue();
	}

	@Test
	public void createCake() {
		given(cakeRepository.save(transientEntity)).willReturn(persistedEntities.get(2));

		CakeDTO cakeCreated = cakeService.createCake(sample);
		assertThat(cakeCreated.equals(samples.get(2))).isTrue();
	}
	
}
