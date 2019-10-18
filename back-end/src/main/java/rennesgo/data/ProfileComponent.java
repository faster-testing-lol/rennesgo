package rennesgo.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class ProfileComponent {
	private final Set<Profile> profiles;

	public ProfileComponent() {
		super();
		profiles = new HashSet<>();
	}

	/**
	 * Creates a new preferences profile using the given user name.
	 * @param username The user name of the profile to create.
	 * @return The created profile.
	 */
	public Profile addProfile(final String username) {
		final Profile profile = new Profile(username);
		profiles.add(profile);
		return profile;
	}

	/**
	 * Builds a stream that filter the profiles using the given username.
	 * @param username The username used to select the profiles.
	 * @return A Java stream.
	 */
	public Stream<Profile> findProfileOf(final String username) {
		return profiles
			.parallelStream()
			.filter(pref -> pref.getUsername().equals(username));
	}

	/**
	 * Deletes a stored profile using the given user name.
	 * @param username The user name used to search for the profile to remove.
	 */
	public void delProfile(final String username) {
		findProfileOf(username)
			.findFirst()
			.ifPresent(pref -> profiles.remove(pref));
	}

	/**
	 * @return An unmodifiable set of the existing profiles
	 */
	public Set<Profile> getProfiles() {
		return Collections.unmodifiableSet(profiles);
	}
}
