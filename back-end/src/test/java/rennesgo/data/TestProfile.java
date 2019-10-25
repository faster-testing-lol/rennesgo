package rennesgo.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.Assert.fail;


class TestProfile {

    private Profile p;

    @BeforeEach
    void setUp() {
        p = new Profile("Name");
    }

    @Test
    void testAddPrefLine() {
        p.addPrefLine("MyLine");
        assertTrue(p.getPrefLines().contains("MyLine"));
    }

    @Test
    void testRemovePrefLine() {
        p.addPrefLine("MyLine");
        p.removePrefLine("MyLine");
        assertTrue(p.getPrefLines().isEmpty());
    }

    @Test
    void testEquals() {
        assertEquals(p, p);
        assertNotEquals(p, new Object());
    }
}
