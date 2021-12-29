package br.com.brq.projetoecommerce;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjetoEcommerceApplicationTests {
	@Test
	void soma () {
		var a = 1;
		var b = 1;
		var c = a + b;
		assertThat(c).isGreaterThanOrEqualTo(2);
	}
}
