package yet.another.test.spring.fucking.boot;

import javax.net.ssl.SSLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
//		System.out.println(new BCryptPasswordEncoder().encode("testezao"));
		SpringApplication.run(Application.class, args);
	}

}
