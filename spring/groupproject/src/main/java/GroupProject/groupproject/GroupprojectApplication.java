package GroupProject.groupproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class })
@CrossOrigin(origins="*")
public class GroupprojectApplication {

	/*disabling CORS*/
	
	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("*");
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GroupprojectApplication.class, args);
	}

}
