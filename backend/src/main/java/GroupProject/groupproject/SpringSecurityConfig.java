package GroupProject.groupproject;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	 
	@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, 'true' as enabled from AUTH_USERS where username=?")
            .authoritiesByUsernameQuery("select username, role from AUTH_USERS where username=?")
        ;
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.csrf().
         disable()
         .authorizeRequests()
         .antMatchers(HttpMethod.OPTIONS, "/**")
         .permitAll()
         .anyRequest()
         .authenticated()
         .and()
         .httpBasic();
    }
}