package be.kdg.eirene.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/", "/resources/**", "/webjars/**", "/css/**", "/js/**", "/images/*")
		    .permitAll()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/account/login").permitAll();
		return http.build();
	}
}
