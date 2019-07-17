package rennesgo.controller;

import java.security.Principal;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rennesgo.data.Profile;
import rennesgo.data.ProfileComponent;

@RestController
@RequestMapping("go/user")
public class UserController {
	private final PasswordEncoder passwordEncoder;

	private final UserDetailsManager userDetailsManager;

	private final ProfileComponent profiles;

	public UserController(final PasswordEncoder passwordEncoder, final UserDetailsManager userDetailsManager, final ProfileComponent profiles) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDetailsManager = userDetailsManager;
		this.profiles = profiles;
	}

	@PostMapping("/new/{login}/{pwd}")
	public Profile newAccount(@PathVariable final String login, @PathVariable final String pwd) {
		if(userDetailsManager.userExists(login)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not possible");
		}

		final UserDetails user = new User(login, passwordEncoder.encode(pwd),
			Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
		userDetailsManager.createUser(user);
		return profiles.addProfile(login);
	}

	@DeleteMapping("/del")
	public void delAccount(final Principal principal, final HttpServletRequest request) {
		userDetailsManager.deleteUser(principal.getName());
		profiles.delProfile(principal.getName());
		// Logging out the current user
		SecurityContextHolder.clearContext();
		final HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
	}
}
