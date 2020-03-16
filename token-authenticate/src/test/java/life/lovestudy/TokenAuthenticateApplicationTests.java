package life.lovestudy;

import life.lovestudy.utils.VerifyCodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TokenAuthenticateApplicationTests {
	
	@Test
	void contextLoads() {
		String code1 = VerifyCodeUtil.getRandomString(3);
		String code2 = VerifyCodeUtil.getRandomString(5);
		String code3 = VerifyCodeUtil.getRandomString(7);
		System.out.println(code1);
		System.out.println(code2);
		System.out.println(code3);
	}
	
}
