package com.jorge.conductor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jorge.conductor.ConductorServiceApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConductorServiceApp.class)
@WebAppConfiguration
public class ConductorServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
