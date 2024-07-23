package com.prajcompany.journal;

import com.prajcompany.journal.service.userservice;
import com.prajcompany.journal.repository.userrepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JournalAppApplicationTests {

	@Autowired
	userservice userservice;

	@Autowired
	userrepository userrepository;

	@Test
	public void testgetbyusrname() {
		assertNotNull(userservice.getbyusername("ved"));
	}

	@ParameterizedTest
	@CsvSource({
			"1,1,2",
			"2,10,12",
			"3,3,9"
	})

	public void test(int a, int b, int expected) {
		assertEquals(expected, a + b,"failed for"+expected);

	}

	@ParameterizedTest
	@CsvSource({
			"praj",
			"uttam",
			"ved"})

	public void testFindByUserName(String name) {
		assertNotNull(userrepository.findByUsername(name));

	}
}
