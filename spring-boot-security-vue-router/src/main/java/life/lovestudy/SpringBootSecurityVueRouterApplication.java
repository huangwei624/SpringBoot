package life.lovestudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("life.lovestudy.mapper")
@SpringBootApplication
public class SpringBootSecurityVueRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityVueRouterApplication.class, args);
	}

}
