package com.waracle;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.springframework.http.HttpHeaders;

import com.waracle.dto.CakeDTO;

public abstract class BaseTestCase {
	
	protected final String END_POINT = "/cakes";
	
	protected final HttpHeaders httpHeaders = new HttpHeaders() {
		{
			set("Authorization", "Basic dXNlcjpwYXNzd29yZA==");
		}
	};
	
	protected static List<CakeDTO> samples;
	
	protected static CakeDTO sample;
		
	
	@BeforeClass
	public static void setUpOneTime() throws Exception {
		samples = Arrays.asList(
			new CakeDTO(1L, "Carrot Cake", "Carrot flavoured cake", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/carrot-cake-f1b3d0c.jpg"), 
			new CakeDTO(2L, "Lemon Cheesecake", "Lemon based cheesecake", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/lemon-cheesecake-member-recipe-68bbc86.jpg"),
			new CakeDTO(3L, "Red Velvet", "Red velvet cake is a classic chocolate layer cake with a striking reddish-brown sponge", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/red-velvet-cake-513716c.jpg")
		);
		
		sample = CakeDTO.builder().title("Red Velvet")
				.desc("Red velvet cake is a classic chocolate layer cake with a striking reddish-brown sponge")
				.image("https://images.immediate.co.uk/production/volatile/sites/30/2020/08/red-velvet-cake-513716c.jpg")
				.build();
				
	}
	
}
