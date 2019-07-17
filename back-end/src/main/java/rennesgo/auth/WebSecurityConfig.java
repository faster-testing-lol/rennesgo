package rennesgo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// Required to add new users
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth
			// Allows the creation of new users
			.userDetailsService(userDetailsService).passwordEncoder(encoder());
//			.and()
			// Predefined users
//			.inMemoryAuthentication()
//			.withUser("admin")
//			.password(encoder().encode("admin"))
//			.roles("ADMIN")
//			.and()
//			.withUser("user")
//			.password(encoder().encode("user"))
//			.roles("USER");
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(new RestAuthenticationEntryPoint())
			.and()
			.authorizeRequests()
			.antMatchers("/go/prefs/**", "/go/logout", "/go/user/del").authenticated()
			.antMatchers("/go/login", "/go/user/new**").permitAll()
			.and()
			.formLogin()
			.loginProcessingUrl("/go/login")
			.successHandler(new MySavedRequestAwareAuthenticationSuccessHandler())
			.failureHandler(new SimpleUrlAuthenticationFailureHandler())
			.and()
			.logout()
			.logoutUrl("/go/logout")
			.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
	}
}
