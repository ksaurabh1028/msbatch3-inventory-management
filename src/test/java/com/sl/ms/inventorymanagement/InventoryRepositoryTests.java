package com.sl.ms.inventorymanagement;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryRepositoryTests {

	@Autowired
	private InventoryRepository inventoryRepo;
	
	@Test
	void contextLoads() {
		assertThat(inventoryRepo).isNotNull();
	}

}
