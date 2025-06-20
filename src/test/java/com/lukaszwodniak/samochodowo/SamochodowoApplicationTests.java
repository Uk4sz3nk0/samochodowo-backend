package com.lukaszwodniak.samochodowo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class }) // Only if not using database in tests
class SamochodowoApplicationTests {

	@Test
	void contextLoads() {
	}

}
