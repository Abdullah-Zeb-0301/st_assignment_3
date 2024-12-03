import org.junit.Test;
import static org.junit.Assert.*;

public class LoginAppTest {
    @Test
    public void testAuthenticateUser() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("test@example.com");
        assertNull("Expected null for non-existing user", result);
    }
}
