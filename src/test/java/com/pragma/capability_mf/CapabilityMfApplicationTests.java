package com.pragma.capability_mf;

import com.pragma.capability_mf.domain.usecase.CapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = CapabilityMfApplication.class)
class CapabilityMfApplicationTests {
	@Autowired
	private CapabilityUseCase capabilityUseCase;

	@Test
	void contextLoads() {
		assertNotNull(capabilityUseCase);
	}

}
