import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class PgTest {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://aws-0-us-east-2.pooler.supabase.com:6543/postgres?sslmode=require&preferQueryMode=simple";
        Properties p = new Properties();
        p.setProperty("user", "postgres.rgdhxtfhmbtxyedewpor");
        p.setProperty("password", "Frauisacat");
        try (Connection c = DriverManager.getConnection(url, p)) {
            System.out.println("OK: connected");
        }
    }
}

