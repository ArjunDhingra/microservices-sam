package com.eatza.restaurantsearch.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = SwaggerConfiguration.class)
public class SwaggerDocketConfigurationTest {

	@InjectMocks
	private SwaggerConfiguration swaggerConfig;

	@Test
	public void docketTest() {
		assertNotNull(swaggerConfig.api());
	}

	@Test
	public void uiconfigurationTest() {
		assertNotNull(swaggerConfig.uiConfig());
	}

}
