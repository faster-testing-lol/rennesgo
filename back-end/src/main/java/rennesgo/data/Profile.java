package rennesgo.data;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Profile implements Principal {
	/** The preferred bus lines of the user. */
	private final Set<String> prefLines;
	/** The username of the user profile. */
	private final String username;

	public Profile(final String username) {
		super();
		this.username = username;
		prefLines = new HashSet<>();
	}

	public void addPrefLine(final String line) {
		prefLines.add(line);
	}

	public void removePrefLine(final String line) {
		prefLines.remove(line);
	}

	public String getName() {
		return username;
	}

	public Set<String> getPrefLines() {
		return Collections.unmodifiableSet(prefLines);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Profile profile = (Profile) o;
		return Objects.equals(prefLines, profile.prefLines) &&
				Objects.equals(username, profile.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(prefLines, username);
	}
}
