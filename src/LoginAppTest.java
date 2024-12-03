
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginAppTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/softwaretesting";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";

    @Test
    public void testAuthenticateUser_ValidEmail() {
        // Arrange
        String testEmail = "testuser@example.com";
        String testName = "Test User";
        insertTestUser(testEmail, testName);

        LoginApp loginApp = new LoginApp();

        // Act
        String result = loginApp.authenticateUser(testEmail);

        // Assert
        assertEquals("Test User", result);

        // Cleanup
        deleteTestUser(testEmail);
    }

    @Test
    public void testAuthenticateUser_InvalidEmail() {
        // Arrange
        String invalidEmail = "invaliduser@example.com";

        LoginApp loginApp = new LoginApp();

        // Act
        String result = loginApp.authenticateUser(invalidEmail);

        // Assert
        assertNull(result);
    }

    private void insertTestUser(String email, String name) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO User (Email, Name) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to insert test user.");
        }
    }

    private void deleteTestUser(String email) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM User WHERE Email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to delete test user.");
        }
    }
}

