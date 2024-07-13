package ru.netology.springboot;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
	private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);
	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeAll
	public static void setUp() {
		devApp.start();
		prodApp.start();
	}

	@Test
	void testDevProfile() {
		ResponseEntity<String> entityFromDevApp = restTemplate.getForEntity(
				"http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);

		String expected = "Current profile is dev";
		Assertions.assertEquals(expected, entityFromDevApp.getBody());
	}

	@Test
	void testProdProfile() {
		ResponseEntity<String> entityFromProdApp = restTemplate.getForEntity(
				"http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);

		String expected = "Current profile is production";
		Assertions.assertEquals(expected, entityFromProdApp.getBody());
	}

}
