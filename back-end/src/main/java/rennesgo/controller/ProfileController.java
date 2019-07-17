package rennesgo.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rennesgo.data.Profile;
import rennesgo.data.ProfileComponent;

@RestController
@RequestMapping("go/profile")
public class ProfileController {
	private final ProfileComponent profiles;

	public ProfileController(final ProfileComponent profiles) {
		super();
		this.profiles = profiles;
	}

	@GetMapping("/get")
	public Profile getProfile(final Principal principal) {
		return profiles.findProfileOf(principal.getName())
			.findFirst()
			.orElse(null);
	}

	@PutMapping("/lines/new/{idLine}")
	public Profile newPreferredLine(@PathVariable final String idLine, final Principal principal) {
		return profiles.findProfileOf(principal.getName())
			.peek(pref -> pref.addPrefLine(idLine))
			.findFirst()
			.orElse(null);
	}

	@PutMapping("/lines/del/{idLine}")
	public Profile delPreferredLine(@PathVariable final String idLine, final Principal principal) {
		return profiles.findProfileOf(principal.getName())
			.peek(pref -> pref.removePrefLine(idLine))
			.findFirst()
			.orElse(null);
	}

	@GetMapping("/username")
	public String whoiam(final Principal principal) {
		return principal.getName();
	}
}
