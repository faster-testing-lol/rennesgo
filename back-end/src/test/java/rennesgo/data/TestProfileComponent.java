package rennesgo.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProfileComponent {

    private ProfileComponent pc;
    private Profile p;

    @BeforeEach
    void setUp() {
        pc = new ProfileComponent();
        p = new Profile("Name");
    }

    @Test
    void testAddProfile() {
        pc.addProfile("Name");
        assertTrue(pc.getProfiles().contains(p));
    }

    @Test
    void testFindProfileOf() {
        pc.addProfile("Name");
        assertEquals(p, pc.findProfileOf("Name").findFirst().orElse(null));
    }

    @Test
    void testDelProfile() {
        pc.addProfile("Name");
        assertFalse(pc.getProfiles().isEmpty());
        pc.delProfile("Name");
        assertTrue(pc.getProfiles().isEmpty());
    }
}
