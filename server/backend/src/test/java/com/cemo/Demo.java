package com.cemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringSecurityExampleApplication)
@SpringBootTest
public class Demo {

	@Test
	public void a() {		assertEquals(true, true);	}
}
