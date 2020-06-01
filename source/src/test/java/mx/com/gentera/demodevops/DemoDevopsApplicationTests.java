package mx.com.gentera.demodevops;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoDevopsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void prueba1() {
		int var1 = 2;
		int var2 = 3;
		
		assert(var1 != var2);
	}

}
