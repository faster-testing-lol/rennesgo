package rennesgo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.server.ResponseStatusException;
import rennesgo.data.Profile;
import rennesgo.data.ProfileComponent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUserController {

    private UserController controller;
    private PasswordEncoder pe;
    private UserDetailsManager udm;
    private ProfileComponent pc;
    private Profile pp;

    @BeforeEach
    void setUp() {
        pp = new Profile("Name2");
        pe = Mockito.mock(PasswordEncoder.class);
        udm = Mockito.mock(UserDetailsManager.class);
        pc = new ProfileComponent();
        controller = new UserController(pe, udm, pc);
    }

    @Test
    void testNewAccount() {
        Mockito.when(udm.userExists("Name1")).thenReturn(true);
        Mockito.when(udm.userExists("Name2")).thenReturn(false);
        Mockito.when(pe.encode("qsdf")).thenReturn("ok");
        assertThrows(ResponseStatusException.class, () -> {
            controller.newAccount("Name1", "azerty");
        });
        Profile p = controller.newAccount("Name2", "qsdf");
        assertEquals(pp, p);
        assertTrue(pc.getProfiles().contains(pp));
    }

    @Test
    void testDelAccount() {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpSession s = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession(false)).thenReturn(s);
        pc.addProfile("Name2");
        controller.delAccount(pp, req);
        assertTrue(pc.getProfiles().isEmpty());
    }
}
