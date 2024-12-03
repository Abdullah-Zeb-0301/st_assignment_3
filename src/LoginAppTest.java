import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginAppTest {

    // Replace this method with your actual test logic
    public static void main(String[] args) {
        LoginAppTest test = new LoginAppTest();
        test.testAuthentication();
    }

    @Test
    public void testAuthentication() {
        // Test your database logic or mock it as needed
        String result = authenticateUser("user@example.com");
        assertNotNull(result);
        System.out.println("Test passed: " + result);
    }

    private String authenticateUser(String email) {
        String userName = null;
        String DB_URL = "jdbc:mysql://localhost:3306/softwaretesting";
        String DB_USER = "root";
        String DB_PASSWORD = "12345678";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT name FROM User WHERE Email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userName = rs.getString("Name");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }
}
