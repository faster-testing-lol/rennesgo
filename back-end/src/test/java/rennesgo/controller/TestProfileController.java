package rennesgo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rennesgo.data.Profile;
import rennesgo.data.ProfileComponent;

import java.security.Principal;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestProfileController {
    
    private ProfileController controller;
    private ProfileComponent profiles;
    private Profile p;
    private Principal user;

    @BeforeEach
    void setUp() {
        profiles = new ProfileComponent();
        controller = new ProfileController(profiles);
        p = new Profile("Name");
        user = () -> "Name";
    }

    @Test
    void testGetProfile() {
        p = new Profile("Name");
        profiles.addProfile("Name");
        assertEquals(p, controller.getProfile(user));
    }

    @Test
    void testNewPreferredLine() {
        profiles.addProfile("Name");
        Profile pp = controller.newPreferredLine("MyLine", user);
        assertNotEquals(p, pp);
        p.addPrefLine("MyLine");
        assertEquals(p, pp);
    }

    @Test
    void testDelPreferredLine() {
        profiles.addProfile("Name");
        controller.newPreferredLine("MyLine", user);
        Profile pp = controller.delPreferredLine("MyLine", user);
        assertEquals(p, pp);
    }
}
