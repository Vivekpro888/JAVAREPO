package com.stream.app;

import com.stream.app.ServicRepo.VideoSerImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringStreamBackendApplicationTests {

	@Autowired
	VideoSerImp videoSerImp;



	@Test
	void contextLoads() {

		videoSerImp.ProccesVideo("a1511aef-1240-4e77-85df-a7dbc07d48de",null);
	}

}
