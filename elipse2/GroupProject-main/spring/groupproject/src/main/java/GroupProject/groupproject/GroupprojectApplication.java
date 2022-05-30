package GroupProject.groupproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class })
public class GroupprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupprojectApplication.class, args);
	}

}
