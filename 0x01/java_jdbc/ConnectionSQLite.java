import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSQLite {
    public static void main(String[] args) {
        initConnection();
    }
    public static void initConnection(){
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlite:sqlite_database_2022.db");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
